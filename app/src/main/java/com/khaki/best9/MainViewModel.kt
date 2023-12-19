package com.khaki.best9

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.khaki.best9.repository.AlbumSearchRepository
import com.khaki.best9.repositoryImpl.DummyAlbumSearchRepositoryImpl
import com.khaki.best9.ui.model.AlbumUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class MainViewModel(
    private val searchRepository: AlbumSearchRepository,
) : ViewModel() {

    private val _bottomSheetUiState = MutableStateFlow(BottomSheetUiState())

    fun bottomSheetUiState() = _bottomSheetUiState.asStateFlow()

    fun searchAlbums(query: String) {
        viewModelScope.launch {
            searchRepository.searchAlbums(query)
                .onEach { albums ->
                    val uiModels = albums.map {
                        AlbumUiModel(
                            id = it.id,
                            title = it.title,
                            artist = it.artist,
                            albumArtUrl = it.albumArtUrl,
                            releaseMonth = it.releaseMonth.format(DateTimeFormatter.ISO_DATE),
                        )
                    }
                    _bottomSheetUiState.update {
                        it.copy(
                            searchResults = uiModels,
                            searchQuery = query,
                        )
                    }
                }
                .launchIn(viewModelScope)
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val searchRepository = DummyAlbumSearchRepositoryImpl()

                return MainViewModel(
                    searchRepository = searchRepository,
                ) as T
            }
        }
    }

}
