package com.randrez.projectmarvel.presentation.character

import LoadImageItemComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.randrez.projectmarvel.domain.models.character.Character
import com.randrez.projectmarvel.presentation.components.CustomCard

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemCharacter(
    modifier: Modifier, character: Character, onSelectCharacter: (Character) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) character.background else Color.White.copy(alpha = 1f)
    val shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)

    CustomCard(shape = shape,
        modifier = modifier.padding(5.dp),
        backgroundColor = character.background,
        interactionSource = interactionSource,
        elevation = 0.dp,
        onSelectCard = { onSelectCharacter(character) }) {
        Column {
            LoadImageItemComponent(
                image = character.image, color = color, background = character.background
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(character.background),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = character.name.uppercase(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

