package com.khaki.best9.compose

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.khaki.best9.R
import com.khaki.best9.ui.model.AlbumUiModel

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    searchResult: List<AlbumUiModel>,
    onClickResultItem: (Long) -> Unit = {},
) {

    Box(
        modifier = modifier
            .heightIn(
                min = 200.dp,
            ),
    ) {

        when {
            isLoading -> {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_loading))
                LottieAnimation(
                    modifier = Modifier
                        .align(Alignment.Center),
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
