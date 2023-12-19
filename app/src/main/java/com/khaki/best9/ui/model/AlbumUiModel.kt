package com.khaki.best9.ui.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Stable
data class AlbumUiModel(
    val id: Long,
    val title: String,
    val artist: String,
    val albumArtUrl: String,
    val releaseMonth: String,
)

class AlbumUiModelPreviewProvider : PreviewParameterProvider<AlbumUiModel> {
    override val values: Sequence<AlbumUiModel>
        get() = sequenceOf(
            AlbumUiModel(
                id = 1,
                title = "title",
                artist = "artist",
                albumArtUrl = "https://via.placeholder.com/150",
                releaseMonth = "2021-01",
            ),
            AlbumUiModel(
                id = 2,
                title = "So Long Title Album Name, Too Long Album Name",
                artist = "artist",
                albumArtUrl = "https://via.placeholder.com/150",
                releaseMonth = "2021-02",
            ),
            AlbumUiModel(
                id = 3,
                title = "title",
                artist = "So Long Artist Name & So Long Artist Name & Too Long Artist Name",
                albumArtUrl = "https://via.placeholder.com/150",
                releaseMonth = "2021-03",
            ),
        )
}
