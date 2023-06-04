package com.loperilla.presentation.creators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loperilla.rawg.model.creator.Creator

@Composable
fun CreatorList(
    creatorList: LazyPagingItems<Creator>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (creatorList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            CreatorPagingList(modifier, creatorList)
        }
    }
}

@Composable
fun CreatorPagingList(
    modifier: Modifier,
    creatorList: LazyPagingItems<Creator>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = creatorList.itemCount
            ) { index ->
                creatorList[index]?.let {
                    CreatorItem(it)
                }
            }
            item {
                if (creatorList.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }

        when (creatorList.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {

            }
        }

        when (creatorList.loadState.append) {
            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {

            }
        }
    }
}

@Composable
fun CreatorItem(
    creator: Creator
) {
    Text(text = creator.name)
}