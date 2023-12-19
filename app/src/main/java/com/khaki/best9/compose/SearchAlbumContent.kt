package com.khaki.best9.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khaki.best9.ui.model.AlbumUiModel
import com.khaki.best9.ui.model.AlbumUiModelPreviewProvider

@Composable
fun SearchAlbumContent(
    modifier: Modifier = Modifier,
    searchResults: List<AlbumUiModel>,
    onClickResultItem: (Long) -> Unit = {},
    onClickSearchAction: (String) -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Text(
            text = "検索結果",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        if (searchResults.isEmpty()) {
            Text(
                text = "検索結果がありません",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        } else {

            LazyColumn {

                item {
                    Divider()
                }

                itemsIndexed(searchResults) { index, album ->
                    SearchResultItem(
                        modifier = Modifier,
                        albumUiModel = album,
                        onClick = {
                            onClickResultItem(album.id)
                        },
                    )

                    if (index < searchResults.size) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }

        SearchForm(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            onClickSearchButton = onClickSearchAction,
        )
    }
}

@Preview(
    name = "検索結果",
    showBackground = true,
)
@Composable
fun SearchAlbumContentPreview() {
    MaterialTheme {
        SearchAlbumContent(
            searchResults = AlbumUiModelPreviewProvider().values.toList(),
        )
    }
}

@Preview(
    name = "検索結果なし",
    showBackground = true,
)
@Composable
fun SearchAlbumContentPreview_Empty() {
    MaterialTheme {
        SearchAlbumContent(
            searchResults = emptyList()
        )
    }
}
