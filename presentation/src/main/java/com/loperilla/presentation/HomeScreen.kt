package com.loperilla.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.loperilla.rawg.model.game.Game

@Composable
fun HomeScreen(
    pagingItems: LazyPagingItems<Game>,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (pagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            GamePagingList(modifier, pagingItems)
        }
    }
}

@Composable
fun GamePagingList(
    modifier: Modifier,
    gameItems: LazyPagingItems<Game>
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (gameItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = gameItems.itemCount,
                    key = gameItems.itemKey(),
                    contentType = gameItems.itemContentType()
                ) { index ->
                    gameItems[index]?.let {
                        Text(
                            text = it.name,
                            color = Color.White
                        )
                    }

                }
                item {
                    if (gameItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}