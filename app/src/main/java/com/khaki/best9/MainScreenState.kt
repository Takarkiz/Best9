package com.khaki.best9

import androidx.compose.runtime.Stable
import com.khaki.best9.ui.model.AlbumUiModel

@Stable
data class MainScreenState(
    val selectedBest9: List<AlbumUiModel> = emptyList(),
)
