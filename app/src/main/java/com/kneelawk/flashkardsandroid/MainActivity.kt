package com.kneelawk.flashkardsandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kneelawk.flashkardsandroid.ui.theme.FlashkardsAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashkardsAndroidTheme {
                FlashcardSets { set ->
                    val intent = Intent(this, FlashcardConfigureActivity::class.java).apply {
                        putExtra(FLASHCARD_CONFIGURE_MESSAGE, FlashcardConfigureMessage(set.name))
                    }
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun FlashcardSets(onSelect: (FlashcardSet) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            LazyColumn(
                contentPadding = PaddingValues(5.dp)
            ) {
                items(FLASHCARDS, key = { it.name }) { set ->
                    OutlinedButton(
                        onClick = { onSelect(set) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(set.name)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun FlashcardSetsPreview() {
    FlashcardSets {}
}
