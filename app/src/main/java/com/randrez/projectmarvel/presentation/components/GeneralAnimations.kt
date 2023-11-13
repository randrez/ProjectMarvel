package com.randrez.projectmarvel.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.randrez.projectmarvel.R

@Composable
fun AnimationProgress(modifier: Modifier = Modifier, isPlaying:Boolean) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.marvel_logo_animation
        )
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        modifier = modifier,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever
    )
}