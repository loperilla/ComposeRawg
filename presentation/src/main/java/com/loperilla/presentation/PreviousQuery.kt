package com.loperilla.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loperilla.rawg.coreui.text.TextBody
import com.loperilla.rawg.model.game.Query

@Composable
fun PreviousQuery(
    queryList: List<Query>,
    onSelectQuery: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(
            count = queryList.size,
        ) {
            TextBody(
                textValue = queryList[it].request,
                modifier = Modifier
                    .clickable {
                        onSelectQuery(queryList[it].request)
                    }
            )
        }
    }
}
