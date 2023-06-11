package com.loperilla.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.loperilla.rawg.coreui.preview.PIXEL_33_NIGHT
import com.loperilla.rawg.coreui.text.TextMedium
import com.loperilla.rawg.coreui.ui.LoadingAnimation
import com.loperilla.rawg.coreui.ui.theme.FlushOrange
import com.loperilla.rawg.coreui.ui.theme.RawgTheme
import com.loperilla.rawg.model.game.Game

@Composable
fun GameItem(
    game: Game,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(game.image)
                .crossfade(true)
                .memoryCacheKey("${game.name}_image")
                .networkCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.DISABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            loading = {
                LoadingAnimation()
            },
            error = {
                Box(
                    modifier = Modifier
                        .background(FlushOrange)
                )
            },
            contentDescription = "${game.name}_image"
        )
        TextMedium(
            textValue = game.name
        )
    }


    /*
    when (val state = painter.state) {
        is AsyncImagePainter.State.Loading -> {
            LoadingAnimation()
        }

        is AsyncImagePainter.State.Success -> {
            Log.e("GameItem", "$state")
            Image(
                painter = painter,
                // filterQuality = FilterQuality.Medium,
                contentDescription = "${game.name}_image",
                contentScale = ContentScale.FillBounds,
            )
        }

        else -> {}
    }*/
}

@PIXEL_33_NIGHT
@Composable
fun GameItemPreview() {
    RawgTheme {
        Surface {
            GameItem(
                game = Game(
                    1,
                    "FallGuys",
                    "https://media.rawg.io/media/games/5eb/5eb49eb2fa0738fdb5bacea557b1bc57.jpg"
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )
        }
    }
}