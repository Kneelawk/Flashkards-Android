package com.kneelawk.flashkardsandroid

import android.os.Parcel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import java.io.IOException

object ParcelHelper {
    fun writeAnnotatedString(parcel: Parcel, string: AnnotatedString) {
        parcel.writeString(string.text)

        parcel.writeInt(string.spanStyles.size)
        for (range in string.spanStyles) {
            parcel.writeInt(range.start)
            parcel.writeInt(range.end)
            writeSpanStyle(parcel, range.item)
        }

        // TODO: Paragraph styles
    }

    fun readAnnotatedString(parcel: Parcel): AnnotatedString {
        val text = parcel.readString() ?: throw IOException("Unable to read string from Parcel")

        val spanStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()
        val spanStylesSize = parcel.readInt()
        for (i in 0 until spanStylesSize) {
            val start = parcel.readInt()
            val end = parcel.readInt()
            val style = readSpanStyle(parcel)

            spanStyles += AnnotatedString.Range(style, start, end)
        }

        return AnnotatedString(text, spanStyles)
    }

    fun writeSpanStyle(parcel: Parcel, style: SpanStyle) {
        val color = style.color
        if (color.isSpecified) {
            parcel.writeByte(1)
            parcel.writeLong(color.value.toLong())
        } else parcel.writeByte(0)

        writeTextUnit(parcel, style.fontSize)

        val baselineShift = style.baselineShift
        if (baselineShift != null) {
            parcel.writeByte(1)
            parcel.writeFloat(baselineShift.multiplier)
        } else parcel.writeByte(0)

        // TODO: Most SpanStyle encoding is still missing
    }

    fun readSpanStyle(parcel: Parcel): SpanStyle {
        val color = when (parcel.readByte()) {
            1.toByte() -> Color(parcel.readLong().toULong())
            else -> Color.Unspecified
        }

        val fontSize = readTextUnit(parcel)

        val baselineShift = if (parcel.readByte() == 1.toByte()) {
            BaselineShift(parcel.readFloat())
        } else null

        return SpanStyle(color = color, fontSize = fontSize, baselineShift = baselineShift)
    }

    fun writeTextUnit(parcel: Parcel, fontSize: TextUnit) {
        when (fontSize.type) {
            TextUnitType.Sp -> {
                parcel.writeByte(1)
                parcel.writeFloat(fontSize.value)
            }

            TextUnitType.Em -> {
                parcel.writeByte(2)
                parcel.writeFloat(fontSize.value)
            }

            TextUnitType.Unspecified -> {
                parcel.writeByte(0)
            }
        }
    }

    fun readTextUnit(parcel: Parcel): TextUnit {
        return when (parcel.readByte()) {
            1.toByte() -> parcel.readFloat().sp
            2.toByte() -> parcel.readFloat().em
            else -> TextUnit.Unspecified
        }
    }
}
