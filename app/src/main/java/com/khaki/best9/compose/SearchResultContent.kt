package com.khaki.best9.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.khaki.best9.R
import com.khaki.best9.ui.model.AlbumUiModel
import com.khaki.best9.ui.model.AlbumUiModelPreviewProvider

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    searchResult: List<AlbumUiModel>,
    onClickResultItem: (Long) -> Unit = {},
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_loading))

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = 200.dp,
            ),
    ) {

        when {
            isLoading -> {
                LottieAnimation(
                    modifier = Modifier
                        .align(Alignment.Center),
                    iterations = LottieConstants.IterateForever,
                    composition = composition,
                )
            }

            searchResult.isEmpty() -> {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "検索結果がありません",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            else -> {
                LazyColumn {

                    item {
                        Divider()
                    }

                    itemsIndexed(searchResult) { index, album ->
                        SearchResultItem(
                            modifier = Modifier,
                            albumUiModel = album,
                            onClick = {
                                onClickResultItem(album.id)
                            },
                        )

                        if (index < searchResult.size) {
                            Divider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "検索結果",
    showBackground = true,
)
@Composable
fun SearchResultContentPreview_Content() {
    MaterialTheme {
        SearchResultContent(
            searchResult = AlbumUiModelPreviewProvider().values.toList(),
        )
    }
}

@Preview(
    name = "検索結果",
    showBackground = true,
)
@Composable
fun SearchResultContentPreview_Loading() {
    MaterialTheme {
        SearchResultContent(
            isLoading = true,
            searchResult = emptyList(),
        )
    }
}

@Preview(
    name = "検索結果",
    showBackground = true,
)
@Composable
fun SearchResultContentPreview_Empty() {
    MaterialTheme {
        SearchResultContent(
            searchResult = emptyList(),
        )
    }
}
