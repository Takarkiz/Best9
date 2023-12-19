package com.khaki.best9

import androidx.compose.runtime.Stable
import com.khaki.best9.ui.model.AlbumUiModel

@Stable
data class BottomSheetUiState(
    val searchResults: List<AlbumUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
)
