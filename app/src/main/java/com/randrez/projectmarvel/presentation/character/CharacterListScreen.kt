package com.randrez.projectmarvel.presentation.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randrez.projectmarvel.domain.models.character.Character
import com.randrez.projectmarvel.presentation.components.AnimationProgress
import com.randrez.projectmarvel.presentation.components.TopAppBarMarvel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    characters: List<Character>,
    loading: Boolean,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarMarvel {
                onBack()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (loading) {
                AnimationProgress(
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center), isPlaying = true
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 200.dp)
                ) {
                    items(characters) { character ->
                       ItemCharacter(character = character, onSelectCharacter = {})
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterListPreview() {
    CharacterListScreen(emptyList(), true) {}
}