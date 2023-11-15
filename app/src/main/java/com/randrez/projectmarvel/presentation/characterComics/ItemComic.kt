package com.randrez.projectmarvel.presentation.characterComics

import LoadImageComponent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.randrez.projectmarvel.domain.models.comic.Comic
import com.randrez.projectmarvel.presentation.components.CustomCard

@Composable
fun ItemComic(modifier: Modifier, comic: Comic, background:Color ,onSelectComic: (Comic) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) background else Color.White.copy(alpha = 1f)
    val shape = RoundedCornerShape(10.dp)

    CustomCard(shape = shape,
        modifier = modifier.padding(5.dp),
        backgroundColor = background,
        interactionSource = interactionSource,
        elevation = 0.dp,
        onSelectCard = { onSelectComic(comic) }) {
        Column {
            LoadImageComponent(
                image = comic.thumbnail, color = color, background = background
            )
        }
    }
}