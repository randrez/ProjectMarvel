package com.randrez.projectmarvel.presentation.comic

import LoadImageInDetail
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.presentation.components.LayoutIconMarvel
import com.randrez.projectmarvel.presentation.components.TopAppBarMarvel
import com.randrez.projectmarvel.ui.theme.GRAY_300
import com.randrez.projectmarvel.ui.theme.GRAY_900

@Composable
fun ComicDetailScreen(
    state: ComicDetailState,
    onBack: () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    val colorStatus = androidx.compose.material3.MaterialTheme.colorScheme.scrim.toArgb()
    val colorNavigation = if (isDarkTheme) GRAY_900.toArgb() else GRAY_300.toArgb()
    val activity = LocalContext.current as? Activity
    val window = activity?.window
    window?.let {
        window.statusBarColor = state.color.toArgb()
        window.navigationBarColor = state.color.toArgb()
    }
    LaunchedEffect(key1 = state.isBackPressed) {
        window?.let {
            window.statusBarColor = colorStatus
            window.navigationBarColor = colorNavigation
        }
    }

    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBarMarvel(
                color = state.color,
                colorIcon = Color.White
            ) {
                onBack()
            }
        },
        bottomBar = {
            LayoutIconMarvel(iconMarvel = state.iconMarvel, background = state.color)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(state.color)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                LoadImageInDetail(
                    image = state.image
                        ?: "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
                )
                Spacer(modifier = Modifier.padding(5.dp))
                TextWithTitle(
                    title = R.string.title,
                    value = state.title ?: stringResource(id = R.string.no_title)
                )
                TextWithTitle(
                    title = R.string.description,
                    value = state.description ?: stringResource(id = R.string.no_description)
                )
            }
        }
    }
}

@Composable
fun TextWithTitle(@StringRes title: Int, value: String) {
    Column(modifier = Modifier.padding(2.dp)) {
        Text(
            text = stringResource(id = title),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

