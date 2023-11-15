package com.randrez.projectmarvel.presentation.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.domain.models.character.Character
import com.randrez.projectmarvel.presentation.components.AnimationProgress
import com.randrez.projectmarvel.presentation.components.EmptyList
import com.randrez.projectmarvel.presentation.components.ErrorList
import com.randrez.projectmarvel.presentation.components.LayoutIconMarvel
import com.randrez.projectmarvel.presentation.components.TopAppBarMarvel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    state: CharactersListState,
    characters: List<Character>,
    loading: Boolean,
    onSelectCharacter: (Character) -> Unit,
    onBack: () -> Unit
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val columnCount = (screenWidthDp / 2)
    Scaffold(
        topBar = {
            if (!loading) {
                TopAppBarMarvel {
                    onBack()
                }
            }
        },
        bottomBar = {
            if (!loading) {
                LayoutIconMarvel()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (loading) {
                AnimationProgress(
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                )
            } else {
                if (characters.isEmpty()) {
                    EmptyList(
                        modifier = Modifier.align(Alignment.Center),
                        message = R.string.empty_list_characters
                    )
                }

                state.messageError?.let { message ->
                    ErrorList(modifier = Modifier.align(Alignment.Center), message = message)
                }

                state.messageErrorResource?.let { message ->
                    ErrorList(
                        modifier = Modifier.align(Alignment.Center),
                        messageResource = message
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = columnCount.dp)
                ) {
                    items(characters) { character ->
                        Row {
                            ItemCharacter(
                                modifier = Modifier.weight(1f),
                                character = character,
                                onSelectCharacter = onSelectCharacter
                            )
                        }
                    }
                }
            }
        }
    }
}