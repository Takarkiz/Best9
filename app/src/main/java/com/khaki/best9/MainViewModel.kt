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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class MainViewModel(
    private val searchRepository: AlbumSearchRepository,
) : ViewModel() {
    fun bottomSheetUiState() = _bottomSheetUiState.asStateFlow()

    private val _bottomSheetUiState = MutableStateFlow(BottomSheetUiState())

    private val selectedAlbums: MutableList<Long> = mutableListOf()

    fun searchAlbums(query: String) {
        _bottomSheetUiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            runCatching {
                searchRepository.searchAlbums(query)
            }.onSuccess { albums ->
                val uiModels = albums.map {
                    AlbumUiModel(
                        id = it.id,
                        title = it.title,
                        artist = it.artist,
                        albumArtUrl = it.albumArtUrl,
                        releaseMonth = it.releaseMonth.format(DateTimeFormatter.ISO_DATE),
                        isSelected = selectedAlbums.contains(it.id),
                    )
                }
                _bottomSheetUiState.update {
                    it.copy(
                        searchResults = uiModels,
                        searchQuery = query,
                        isLoading = false,
                    )
                }
            }.onFailure {
                _bottomSheetUiState.update {
                    it.copy(
                        searchResults = emptyList(),
                        searchQuery = query,
                        isLoading = false,
                    )
                }
            }
        }
    }

    fun selectSearchResult(id: Long) {
        selectedAlbums.add(id)
        _bottomSheetUiState.update {
            it.copy(
                searchResults = it.searchResults.map { album ->
                    if (album.id == id) {
                        album.copy(
                            isSelected = true,
                        )
                    } else {
                        album
                    }
                }
            )
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
