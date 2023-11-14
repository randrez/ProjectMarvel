import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageComponent(image: String, height: Dp = 400.dp, color: Color) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .build()

    val painter = rememberImagePainter(data = image, imageLoader = imageLoader) {
        crossfade(true)
    }
    if (painter.state is ImagePainter.State.Loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }
    /*  if (painter.state is ImagePainter.State.Empty) {
          Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier
                  .fillMaxWidth()
                  .height(height)
          ) {
              Icon(
                  imageVector = Icons.Outlined.Wallpaper,
                  contentDescription = null,
                  modifier = Modifier
                      .size(80.dp)
                      .align(Alignment.Center),
                  tint = MaterialTheme.colorScheme.onBackground
              )
          }
      }*/
    if (painter.state is ImagePainter.State.Error) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        colorFilter = ColorFilter.tint(color, blendMode = BlendMode.Darken)
    )
}