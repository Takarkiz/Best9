package com.khaki.best9.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khaki.best9.BottomSheetUiState
import com.khaki.best9.MainScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainScreenState,
    bottomSheetUiState: BottomSheetUiState,
    onClickSearchButton: (String) -> Unit = {},
    onClickSearchItem: (Long) -> Unit = {},
) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "年間ベスト")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("探す") },
                icon = { Icon(Icons.Filled.Search, contentDescription = "探す") },
                onClick = {
                    showBottomSheet = true
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->

        MainContent(
            modifier = Modifier
                .padding(paddingValues),
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                windowInsets = WindowInsets.ime,
            ) {
                SearchAlbumContent(
                    searchResults = bottomSheetUiState.searchResults,
                    isLoading = bottomSheetUiState.isLoading,
                    onClickResultItem = { id ->
                        onClickSearchItem(id)
                    },
                    onClickSearchAction = { query ->
                        onClickSearchButton(query)
                    }
                )
            }
        }
    }
}

@Preview(
    name = "MainScreen Preview",
    showBackground = true,
)
@Composable
fun MainScreenPreview() {
    MainScreen(
        uiState = MainScreenState(),
        bottomSheetUiState = BottomSheetUiState(),
    )
}
