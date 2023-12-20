package com.khaki.best9.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    onClickSearchButton: (String) -> Unit = {},
) {

    var searchWord by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = searchWord,
            onValueChange = {
                searchWord = it
            },
        )

        Button(
            onClick = {
                onClickSearchButton(searchWord)
            }
        ) {
            Text(text = "検索")
        }
    }

}

@Preview(
    name = "検索フォーム",
    showBackground = true,
)
@Composable
fun SearchFormPreview() {
    SearchForm()
}
