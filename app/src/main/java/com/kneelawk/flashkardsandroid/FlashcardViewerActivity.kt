package com.kneelawk.flashkardsandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kneelawk.flashkardsandroid.ui.theme.FlashkardsAndroidTheme

const val FLASHCARD_VIEWER_MESSAGE = "com.kneelawk.flashkardsandroid.FLASHCARD_VIEWER_MESSAGE"

class FlashcardViewerActivity : ComponentActivity() {

    private val cards = mutableStateOf(emptyList<FlashcardData>())
    private val defaultFront = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = intent.getParcelableExtra<FlashcardViewerMessage>(FLASHCARD_VIEWER_MESSAGE)

        if (message != null) {
            if (message.shuffle) {
                cards.value = message.cards.shuffled()
            } else {
                cards.value = message.cards
            }
            defaultFront.value = message.defaultFront
        }

        setContent {
            FlashkardsAndroidTheme {
                FlashcardViewerMain(
                    title = message?.title ?: "No Cards",
                    cards = cards,
                    defaultFront = defaultFront,
                    onBackButton = { finish() }
                )
            }
        }
    }
}

@Composable
fun FlashcardViewerMain(
    title: String,
    cards: MutableState<List<FlashcardData>>,
    defaultFront: MutableState<Boolean>,
    onBackButton: () -> Unit
) {
    FlashcardViewerMain(
        title = title,
        cards = cards.value,
        defaultFront = defaultFront.value,
        shuffle = { cards.value = cards.value.shuffled() },
        setDefaultFront = { defaultFront.value = it },
        onBackButton = onBackButton
    )
}

@Composable
fun FlashcardViewerMain(
    title: String,
    cards: List<FlashcardData>,
    defaultFront: Boolean,
    shuffle: () -> Unit,
    setDefaultFront: (Boolean) -> Unit,
    onBackButton: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackButton) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                title = {
                    Text(title)
                },
                actions = {
                    Box {
                        var menuOpen by remember { mutableStateOf(false) }

                        IconButton(onClick = { menuOpen = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }

                        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                            DropdownMenuItem(onClick = { shuffle() }) {
                                Text("Shuffle")
                            }
                            DropdownMenuItem(onClick = { setDefaultFront(!defaultFront) }) {
                                Checkbox(checked = !defaultFront, onCheckedChange = null)
                                Text("Cards Flipped", modifier = Modifier.padding(start = 4.dp))
                            }
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(Modifier.padding(10.dp)) {
                FlashcardViewer(cards = cards, defaultFront = defaultFront)
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
fun FlashcardViewerMainPreview() {
    FlashcardViewerMain(
        title = "Phosphorus Ions",
        cards = remember { mutableStateOf(PreviewData) },
        defaultFront = remember { mutableStateOf(true) },
        onBackButton = {}
    )
}
