package com.randrez.projectmarvel.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(
    shape: Shape,
    modifier: Modifier,
    backgroundColor: Color,
    interactionSource: MutableInteractionSource,
    elevation: Dp,
    onSelectCard: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = shape,
        modifier = modifier
            .padding(5.dp)
            .clip(shape)
            .background(backgroundColor)
            .clickable(
                onClick = { onSelectCard() },
                interactionSource = interactionSource,
                indication = rememberRipple()
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        content()
    }
}