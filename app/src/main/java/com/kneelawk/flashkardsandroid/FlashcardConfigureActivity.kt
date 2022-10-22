package com.kneelawk.flashkardsandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.TriStateCheckbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kneelawk.flashkardsandroid.ui.theme.FlashkardsAndroidTheme

const val FLASHCARD_CONFIGURE_MESSAGE = "com.kneelawk.flashkardsandroid.FLASHCARD_CONFIGURE_MESSAGE"

class FlashcardConfigureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selector =
            intent.getParcelableExtra<FlashcardConfigureMessage>(FLASHCARD_CONFIGURE_MESSAGE)
        val set = selector?.name?.let { name -> FLASHCARDS.first { it.name == name } }

        setContent {
            FlashkardsAndroidTheme {
                FlashcardConfigure(
                    set = set,
                    onReview = { msg ->
                        val intent = Intent(this, FlashcardViewerActivity::class.java).apply {
                            putExtra(FLASHCARD_VIEWER_MESSAGE, msg)
                        }
                        startActivity(intent)
                    },
                    onBackButton = { finish() }
                )
            }
        }
    }
}

@Composable
fun FlashcardConfigure(
    set: FlashcardSet?,
    onReview: (FlashcardViewerMessage) -> Unit,
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
                    Text(set?.name ?: "Invalid")
                }
            )
        }
    ) { padding ->
        if (set != null) {
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                var selected by remember { mutableStateOf(emptySet<String>()) }

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            if (selected.isEmpty()) {
                                selected += set.subsets.map { it.name }
                            } else {
                                selected = emptySet()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TriStateCheckbox(
                            state = when {
                                selected.isEmpty() -> ToggleableState.Off
                                selected.size == set.subsets.size -> ToggleableState.On
                                else -> ToggleableState.Indeterminate
                            },
                            onClick = null
                        )
                        Text("Select All", modifier = Modifier.padding(start = 4.dp))
                    }

                    val scrollState = rememberLazyListState()

                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                                MaterialTheme.shapes.medium
                            ),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(set.subsets, key = { it.name }) {
                            OutlinedButton(
                                onClick = {
                                    if (selected.contains(it.name)) {
                                        selected -= it.name
                                    } else {
                                        selected += it.name
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Checkbox(
                                    checked = selected.contains(it.name),
                                    onCheckedChange = null
                                )
                                Text(it.name, modifier = Modifier.padding(start = 4.dp))
                                Spacer(Modifier.weight(1f))
                                Text("${it.cards.size} cards")
                            }
                        }
                    }

                    var shuffle by remember { mutableStateOf(false) }
                    OutlinedButton(
                        onClick = { shuffle = !shuffle },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(checked = shuffle, onCheckedChange = null)
                        Text("Shuffle", modifier = Modifier.padding(start = 4.dp))
                        Spacer(Modifier.weight(1f))
                    }

                    var cardsFlipped by remember { mutableStateOf(false) }
                    OutlinedButton(
                        onClick = { cardsFlipped = !cardsFlipped },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(checked = cardsFlipped, onCheckedChange = null)
                        Text("Cards Flipped", modifier = Modifier.padding(start = 4.dp))
                        Spacer(Modifier.weight(1f))
                    }

                    Button(
                        onClick = {
                            val cards = mutableListOf<FlashcardData>()
                            for (subset in set.subsets) {
                                if (selected.contains(subset.name)) {
                                    cards += subset.cards
                                }
                            }

                            onReview(
                                FlashcardViewerMessage(
                                    set.name,
                                    cards,
                                    shuffle,
                                    !cardsFlipped
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = selected.isNotEmpty()
                    ) {
                        Text("Review")
                    }
                }
            }
        }
    }
}

private val PreviewData = FlashcardSet(
    "Polyatomic Ions",
    listOf(
        FlashcardSubset(
            "Halogens",
            listOf(
                FlashcardData(
                    "Hypochlorite".toAnnotatedString(),
                    buildAnnotatedString {
                        append("ClO")
                        append("-", Superscript)
                    }
                ),
                FlashcardData(
                    "Chlorite".toAnnotatedString(),
                    buildAnnotatedString {
                        append("ClO")
                        append("2", Subscript)
                        append("-", Superscript)
                    }
                ),
                FlashcardData(
                    "Chlorate".toAnnotatedString(),
                    buildAnnotatedString {
                        append("ClO")
                        append("3", Subscript)
                        append("-", Superscript)
                    }
                ),
                FlashcardData(
                    "Perchlorate".toAnnotatedString(),
                    buildAnnotatedString {
                        append("ClO")
                        append("4", Subscript)
                        append("-", Superscript)
                    }
                )
            )
        ),
        FlashcardSubset(
            "Metallic Anions",
            listOf(
                FlashcardData(
                    "Chromate".toAnnotatedString(),
                    buildAnnotatedString {
                        append("CrO")
                        append("4", Subscript)
                        append("2-", Superscript)
                    }
                ),
                FlashcardData(
                    "Dichromate".toAnnotatedString(),
                    buildAnnotatedString {
                        append("Cr")
                        append("2", Subscript)
                        append("O")
                        append("7", Subscript)
                        append("2-", Superscript)
                    }
                ),
                FlashcardData(
                    "Permanganate".toAnnotatedString(),
                    buildAnnotatedString {
                        append("MnO")
                        append("4", Subscript)
                        append("-", Superscript)
                    }
                ),
            )
        )
    )
)

@Composable
@Preview(showBackground = true)
fun FlashcardConfigurePreview() {
    FlashcardConfigure(set = PreviewData, onReview = {}, onBackButton = {})
}
