package com.loperilla.presentation.creators

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.loperilla.rawg.model.creator.Creator

@Composable
fun CreatorList(
    creatorList: List<Creator>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(creatorList.size) {
            CreatorItem(creatorList[it])
        }
    }
}

@Composable
fun CreatorItem(
    creator: Creator
) {
    Text(text = creator.name)
}