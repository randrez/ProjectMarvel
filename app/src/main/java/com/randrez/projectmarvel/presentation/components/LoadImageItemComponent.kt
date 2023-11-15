import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter.*
import coil.compose.rememberImagePainter
import coil.request.CachePolicy

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageItemComponent(
    image: String,
    color: Color,
    background: Color
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()
    val painter = rememberImagePainter(data = image, imageLoader = imageLoader) {
        crossfade(true).memoryCachePolicy(CachePolicy.ENABLED)
    }

    when (painter.state) {
        is State.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(background)
            ) {
                CircularProgressIndicator(
                    color = Color.White, modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is State.Error -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(background)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    tint = Color.White
                )
            }
        }

        else -> {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                colorFilter = ColorFilter.tint(
                    color = color.copy(alpha = 0.8f),
                    blendMode = BlendMode.Darken
                )
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageInDetail(image: String) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()
    val painter = rememberImagePainter(data = image, imageLoader = imageLoader) {
        crossfade(true).memoryCachePolicy(CachePolicy.ENABLED)
    }
    when (painter.state) {
        is State.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                CircularProgressIndicator(
                    color = Color.White, modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is State.Error -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .size(800.dp)
                        .align(Alignment.Center),
                    tint = Color.White
                )
            }
        }

        else -> {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }
}