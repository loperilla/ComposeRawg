package com.loperilla.rawg.coreui.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.loperilla.rawg.coreui.preview.PIXEL_33_NIGHT
import com.loperilla.rawg.coreui.ui.theme.RawgTheme

@Composable
fun TextTitle(
    textValue: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = textValue,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun TextMedium(
    textValue: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = textValue,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun TextBody(
    textValue: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = textValue,
        style = MaterialTheme.typography.bodySmall,
    )
}

@PIXEL_33_NIGHT
@Composable
fun TextTitlePreview() {
    RawgTheme {
        Surface {
            Column {
                TextTitle(textValue = "Hola")
                TextMedium(textValue = "Hola")
                TextBody(textValue = "Hola")
            }
        }
    }
}