package com.kneelawk.flashkardsandroid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val EMPTY_FLASHCARD =
    FlashcardData("No Cards".toAnnotatedString(), "No Cards".toAnnotatedString())

@Composable
fun FlashcardViewer(cards: List<FlashcardData>, defaultFront: Boolean) {
    var index by remember { mutableStateOf(0) }
    var front by remember(defaultFront) { mutableStateOf(defaultFront) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val card = if (cards.isEmpty()) EMPTY_FLASHCARD else cards[index]
        val indexStr = if (cards.isEmpty()) "0 / 0" else "${index + 1} / ${cards.size}"

        Flashcard(data = card, front = front)

        Text(indexStr)

        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button(
                onClick = {
                    front = defaultFront
                    if (index > 0) {
                        index--
                    } else {
                        index = cards.size - 1
                    }
                },
                enabled = cards.isNotEmpty()
            ) {
                Text("<")
            }

            Button(
                onClick = { front = !front },
                enabled = cards.isNotEmpty()
            ) {
                Text("Show ${if (front == defaultFront) "back" else "front"}")
            }

            Button(
                onClick = {
                    front = defaultFront
                    if (index < cards.size - 1) {
                        index++
                    } else {
                        index = 0
                    }
                },
                enabled = cards.isNotEmpty()
            ) {
                Text(">")
            }
        }
    }
}

private val PreviewData = listOf(
    FlashcardData(
        "Phosphate".toAnnotatedString(),
        buildAnnotatedString {
            append("PO")
            append("4", Subscript)
            append("3-", Superscript)
        }
    ),
    FlashcardData(
        "Hydrogen Phosphate".toAnnotatedString(),
        buildAnnotatedString {
            append("HPO")
            append("4", Subscript)
            append("2-", Superscript)
        }
    ),
    FlashcardData(
        "Dihydrogen Phosphate".toAnnotatedString(),
        buildAnnotatedString {
            append("H")
            append("2", Subscript)
            append("PO")
            append("4", Subscript)
            append("-", Superscript)
        }
    )
)

@Composable
@Preview(showBackground = true)
fun FlashcardViewerPreview() {
    FlashcardViewer(cards = PreviewData, defaultFront = true)
}

@Composable
@Preview(showBackground = true)
fun FlashcardViewerPreviewEmpty() {
    FlashcardViewer(cards = emptyList(), defaultFront = true)
}
