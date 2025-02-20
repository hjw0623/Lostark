package com.hjw0623.character.presentation.character_overview.components.tab.collectibles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.character_overview.mockup.mockCollectiblePointsContent
import com.hjw0623.character.presentation.character_overview.model.collectibles.CollectibleDetailUi
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CollectibleDetailListItem(
    number: Int,
    detail: CollectibleDetailUi
) {
    val index  = number + 1
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = index.toString(),
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = detail.name,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "${detail.point}/${detail.maxPoint}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        if (detail.obtained) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Acquired",
                tint = LostArkBlue
            )
        }
    }
}


@Preview
@Composable
private fun CollectibleDetailListItemPreview() {
    LostarkTheme {
        CollectibleDetailListItem(
            number = 1,
            detail = mockCollectiblePointsContent()
        )
    }
}