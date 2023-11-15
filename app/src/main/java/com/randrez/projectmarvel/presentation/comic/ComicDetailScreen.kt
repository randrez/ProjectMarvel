package com.randrez.projectmarvel.presentation.comic

import LoadImageComponent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.presentation.components.LayoutIconMarvel
import com.randrez.projectmarvel.presentation.components.TopAppBarMarvel

@Composable
fun ComicDetailScreen(
    state: ComicDetailState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarMarvel(
                title = state.comic.title,
                color = state.color,
                colorIcon = Color.White
            ) {
                onBack()
            }
        },
        bottomBar = {
            LayoutIconMarvel(iconMarvel = state.iconMarvel)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
                LoadImageComponent(
                    image = state.comic.image,
                    color = state.color,
                    background = state.color
                )
                Text(
                    text = state.comic.description ?: stringResource(id = R.string.no_description),
                    color = state.color,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}