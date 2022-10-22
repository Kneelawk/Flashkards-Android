package com.kneelawk.flashkardsandroid

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun String.toAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        append(this@toAnnotatedString)
    }
}

fun AnnotatedString.Builder.append(text: String, style: SpanStyle) {
    withStyle(style) {
        append(text)
    }
}

fun <E : Enum<E>> E.toInt(): Int = this.ordinal

inline fun <reified E : Enum<E>> Int.toEnum(): E {
    val values = enumValues<E>()
    return values[this.coerceIn(values.indices)]
}

fun <E : Enum<E>> E.toByte(): Byte = this.toInt().toByte()

inline fun <reified E : Enum<E>> Byte.toEnum(): E = this.toInt().toEnum()
