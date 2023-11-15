package com.randrez.projectmarvel.presentation.characterComics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.domain.models.comic.Comic
import com.randrez.projectmarvel.presentation.components.AnimationProgress
import com.randrez.projectmarvel.presentation.components.EmptyList
import com.randrez.projectmarvel.presentation.components.ErrorList
import com.randrez.projectmarvel.presentation.components.LayoutIconMarvel
import com.randrez.projectmarvel.presentation.components.TopAppBarMarvel

@Composable
fun CharacterComicsScreen(
    state: CharacterComicsState,
    comics: List<Comic>,
    loading: Boolean,
    onSelectComic: (Comic) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarMarvel(
                icon = state.icon,
                title = state.title,
                color = state.color,
                colorIcon = Color.White
            ) {
                onBack()
            }
        },
        bottomBar = {
            if (!loading) {
                LayoutIconMarvel(iconMarvel = state.iconMarvel)
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
                if (comics.isEmpty()) {
                    EmptyList(
                        modifier = Modifier.align(Alignment.Center),
                        message = R.string.empty_list_comic
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
                    columns = GridCells.Adaptive(minSize = 200.dp)
                ) {
                    items(comics) { comic ->
                        Row {
                            ItemComic(
                                modifier = Modifier.weight(1f),
                                comic = comic,
                                background = state.color,
                                onSelectComic = onSelectComic
                            )
                        }
                    }
                }
            }
        }
    }
}