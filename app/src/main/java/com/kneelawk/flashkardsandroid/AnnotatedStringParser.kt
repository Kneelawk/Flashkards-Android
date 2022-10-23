package com.kneelawk.flashkardsandroid

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp
import java.io.IOException

object AnnotatedStringParser {
    class ParseException(msg: String, val span: IntRange? = null) : IOException(msg)

    @Throws(ParseException::class)
    fun parseAnnotatedString(input: String): AnnotatedString {
        val sb = StringBuilder()
        val spans = mutableListOf<AnnotatedString.Range<SpanStyle>>()

        val stream = tokenize(input)
        var spanStart = sb.length
        var charStyle: SpanStyle? = null
        val styleStack = ArrayDeque<SpanStyle>()
        styleStack.addLast(SpanStyle())

        fun curStyle(): SpanStyle {
            return charStyle ?: styleStack.lastOrNull()
            ?: throw ParseException("Extra closing brace encountered! Not sure where though.")
        }

        fun applyStyle() {
            spans += AnnotatedString.Range(
                styleStack.lastOrNull()
                    ?: throw ParseException("Extra closing brace encountered! Not sure where though."),
                spanStart,
                sb.length
            )
            spanStart = sb.length
        }

        fun checkCharStyle() {
            val style = charStyle
            if (style != null) {
                applyStyle()
                spans += AnnotatedString.Range(style, spanStart, ++spanStart)
                charStyle = null
            }
        }

        fun append(str: String) {
            checkCharStyle()
            sb.append(str)
        }

        fun append(ch: Char) {
            checkCharStyle()
            sb.append(ch)
        }

        while (stream.hasNextToken()) {
            when (val tok = stream.nextToken()) {
                is Text -> append(tok.text)

                is Whitespace -> if (charStyle == null) sb.append(tok.ws) // Eat whitespace when there is a char style

                is Backslash -> when (val escaped = stream.nextToken()) {
                    is Backslash -> append('\\')
                    is Underscore -> append('_')
                    is UpArrow -> append('^')
                    is OpenBrace -> append('{')
                    is CloseBrace -> append('}')
                    is Text -> when (val command = escaped.text) {
                        "color" -> {
                            stream.expectIgnoreWS<OpenBrace>()
                            val colorTok = stream.expect<Text>()
                            stream.expect<CloseBrace>()

                            val color = parseColor(colorTok)
                            charStyle = curStyle().copy(color = color)
                        }

                        else -> throw ParseException(
                            "Unknown command '$command' @${escaped.formatPosition()}",
                            escaped.toRange()
                        )
                    }

                    is Whitespace -> throw ParseException(
                        "Dangling backslash @${tok.formatPosition()}",
                        tok.toRange()
                    )
                }

                is Underscore -> charStyle =
                    curStyle().copy(baselineShift = BaselineShift.Subscript, fontSize = 14.sp)

                is UpArrow -> charStyle =
                    curStyle().copy(baselineShift = BaselineShift.Superscript, fontSize = 14.sp)

                is OpenBrace -> {
                    applyStyle()
                    styleStack.addLast(curStyle())
                    charStyle = null
                }

                is CloseBrace -> {
                    applyStyle()
                    styleStack.removeLastOrNull()
                        ?: throw ParseException("Extra closing brace encountered! Not sure where though.")

                    if (styleStack.isEmpty()) throw ParseException(
                        "Extra closing brace encountered @${tok.formatPosition()}",
                        tok.toRange()
                    )
                }
            }
        }

        if (styleStack.size > 1) throw ParseException("Missing ${styleStack.size - 1} closing braces")

        return AnnotatedString(sb.toString(), spans)
    }

    private fun parseColor(colorTok: Text): Color {
        val colorStr = colorTok.text
        return if (colorStr.startsWith("#")) {
            var colorUInt = try {
                colorStr.substring(1).toUInt(16)
            } catch (e: NumberFormatException) {
                throw ParseException(
                    "Error parsing hex color code '$colorStr' @${colorTok.formatPosition()}",
                    colorTok.toRange()
                )
            }

            if (colorStr.length <= 7) {
                colorUInt = colorUInt or 0xFF000000.toUInt()
            }

            Color(colorUInt.toInt())
        } else {
            COLOR_NAMES[colorStr]
                ?: throw ParseException(
                    "Unknown color name '$colorStr' @${colorTok.formatPosition()}. Hex-codes are supported though.",
                    colorTok.toRange()
                )
        }
    }

    private val COLOR_NAMES = mapOf(
        "red" to Color.Red,
        "green" to Color.Green,
        "blue" to Color.Blue
    )

    private val TEXT_REGEX = Regex("""[^\\_^{}\s]+""")
    private val WS_REGEX = Regex("""\s+""")

    private sealed interface Token {
        val start: Int
        val end: Int

        fun formatPosition(): String = "($start..$end)"
        fun toRange(): IntRange = start until end
    }

    private data class Text(val text: String, override val start: Int, override val end: Int) :
        Token

    private data class Whitespace(val ws: String, override val start: Int, override val end: Int) : Token
    private data class Backslash(override val start: Int, override val end: Int) : Token
    private data class Underscore(override val start: Int, override val end: Int) : Token
    private data class UpArrow(override val start: Int, override val end: Int) : Token
    private data class OpenBrace(override val start: Int, override val end: Int) : Token
    private data class CloseBrace(override val start: Int, override val end: Int) : Token

    private fun tokenize(input: String): TokenStream {
        val tokens = mutableListOf<Token>()

        var index = 0
        while (index < input.length) {
            val textMatch = TEXT_REGEX.matchAt(input, index)
            val wsMatch = WS_REGEX.matchAt(input, index)
            val ch = input[index]

            if (textMatch != null) {
                val value = textMatch.value
                tokens += Text(value, index, index + value.length)
                index += value.length
            } else if (wsMatch != null) {
                val value = wsMatch.value
                tokens += Whitespace(value, index, index + value.length)
                index += value.length
            } else if (ch == '\\') {
                tokens += Backslash(index, index + 1)
                index++
            } else if (ch == '_') {
                tokens += Underscore(index, index + 1)
                index++
            } else if (ch == '^') {
                tokens += UpArrow(index, index + 1)
                index++
            } else if (ch == '{') {
                tokens += OpenBrace(index, index + 1)
                index++
            } else if (ch == '}') {
                tokens += CloseBrace(index, index + 1)
                index++
            } else {
                throw ParseException(
                    "Expected a string, '\\', '_', '^', '{', or '}'",
                    index until index + 1
                )
            }
        }

        return TokenStream(tokens)
    }

    private class TokenStream(val tokens: List<Token>) {
        var index = 0

        fun hasNextToken(): Boolean = index < tokens.size

        fun nextToken(): Token {
            return tokens.getOrNull(index++)
                ?: throw ParseException("Expected another token but found the end of stream")
        }

        inline fun <reified T : Token> expectIgnoreWS(): T {
            var tok = nextToken()
            if (tok is Whitespace) {
                tok = nextToken()
            }

            return tok as? T ?: throw ParseException(
                "Expected ${T::class.simpleName} but found ${tok::class.simpleName} @${tok.formatPosition()}",
                tok.toRange()
            )
        }

        inline fun <reified T : Token> expect(): T {
            val tok = nextToken()
            return tok as? T ?: throw ParseException(
                "Expected ${T::class.simpleName} but found ${tok::class.simpleName} @${tok.formatPosition()}",
                tok.toRange()
            )
        }
    }
}

fun String.parseAnnotatedString(): AnnotatedString =
    AnnotatedStringParser.parseAnnotatedString(this)
