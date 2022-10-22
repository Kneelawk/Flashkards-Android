package com.kneelawk.flashkardsandroid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Subscript = SpanStyle(baselineShift = BaselineShift.Subscript, fontSize = 14.sp)
val Superscript = SpanStyle(baselineShift = BaselineShift.Superscript, fontSize = 14.sp)

@Composable
fun FlashcardFace(text: AnnotatedString) {
    Surface(modifier = Modifier.defaultMinSize(300.dp, 200.dp), shape = MaterialTheme.shapes.medium, elevation = 2.dp) {
        Box(contentAlignment = Alignment.Center) {
            Text(text, fontSize = 20.sp)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FlashcardFacePreview() {
    FlashcardFace(text = "Perchlorate".toAnnotatedString())
}

@Composable
@Preview(showBackground = true)
fun FlashcardFacePreview2() {
    FlashcardFace(text = buildAnnotatedString {
        append("CO")
        withStyle(Subscript) {
            append("4")
        }
        withStyle(Superscript) {
            append("-")
        }
    })
}
