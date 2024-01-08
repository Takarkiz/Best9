package com.khaki.best9.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MainContent(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {

    }
}
