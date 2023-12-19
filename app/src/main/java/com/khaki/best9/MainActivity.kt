package com.khaki.best9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.khaki.best9.compose.MainScreen
import com.khaki.best9.ui.theme.Best9Theme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val bottomSheetUiState by viewModel.bottomSheetUiState().collectAsState()

            Best9Theme {
                MainScreen(
                    uiState = MainScreenState(),
                    bottomSheetUiState = bottomSheetUiState,
                    onClickSearchButton = {
                        viewModel.searchAlbums(it)
                    },
                    onClickSearchItem = {

                    }
                )
            }
        }
    }
}
