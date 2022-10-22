package com.kneelawk.flashkardsandroid

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Flashcard(data: FlashcardData, front: Boolean) {
    if (front) {
        FlashcardFace(text = data.front)
    } else {
        FlashcardFace(text = data.back)
    }
}

private val PreviewData = FlashcardData("Chromate".toAnnotatedString(), buildAnnotatedString {
    append("CrO")
    withStyle(Subscript) {
        append("4")
    }
    withStyle(Superscript) {
        append("2-")
    }
})

@Composable
@Preview(showBackground = true)
fun FlashcardPreviewFront() {
    Flashcard(data = PreviewData, front = true)
}

@Composable
@Preview(showBackground = true)
fun FlashcardPreviewBack() {
    Flashcard(data = PreviewData, front = false)
}
