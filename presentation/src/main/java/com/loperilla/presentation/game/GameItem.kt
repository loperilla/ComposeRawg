package com.loperilla.presentation.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.loperilla.rawg.coreui.preview.PIXEL_33_NIGHT
import com.loperilla.rawg.coreui.ui.LoadingAnimation
import com.loperilla.rawg.coreui.ui.theme.RawgTheme
import com.loperilla.rawg.model.game.Game

@Composable
fun GameItem(
    game: Game
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(game.image)
            .crossfade(true)
            .memoryCacheKey("${game.name}_image")
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    )
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
        contentDescription = "${game.name}_image"
    )

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
        GameItem(
            game = Game(
                1,
                "FallGuys",
                "https://media.rawg.io/media/games/5eb/5eb49eb2fa0738fdb5bacea557b1bc57.jpg"
            )
        )
    }
}