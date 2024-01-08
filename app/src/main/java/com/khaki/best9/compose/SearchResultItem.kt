package com.khaki.best9.compose

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.khaki.best9.ui.model.AlbumUiModel
import com.khaki.best9.ui.model.AlbumUiModelPreviewProvider

@Composable
fun SearchResultItem(
    modifier: Modifier,
    albumUiModel: AlbumUiModel,
    onClick: () -> Unit = {},
) {

    Row(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            )
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(72.dp)
                .clip(shape = MaterialTheme.shapes.small),
            model = albumUiModel.albumArtUrl,
            placeholder = rememberVectorPainter(image = Icons.Default.Photo),
            contentDescription = "アルバムアートワーク",
        )

        Column(
            modifier = Modifier
                .weight(1f),

            ) {
            Text(
                text = albumUiModel.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Text(
                text = albumUiModel.artist,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Crossfade(
            targetState = albumUiModel.isSelected,
            label = "追加時アニメーション",
            animationSpec = tween(
                durationMillis = 500,
            )
        ) { isChecked ->

            val icon = if (isChecked) {
                Icons.Default.CheckCircleOutline
            } else {
                Icons.Default.ControlPoint
            }

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = icon,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "追加"
            )
        }


    }
}

@Preview(
    name = "検索結果アイテム",
    showBackground = true,
)
@Composable
fun SearchResultItemPreview(
    @PreviewParameter(AlbumUiModelPreviewProvider::class) item: AlbumUiModel,
) {

    MaterialTheme {
        SearchResultItem(
            modifier = Modifier,
            albumUiModel = item,
        )
    }
}
