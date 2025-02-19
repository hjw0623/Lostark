package com.hjw0623.character.presentation.components.tab.collectibles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.mockup.mockCollectibleSummationContent
import com.hjw0623.character.presentation.model.collectibles.CollectibleSummationUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CollectibleDetail(
    collectibleSummation: CollectibleSummationUi,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "${collectibleSummation.title} ${collectibleSummation.current}/${collectibleSummation.total}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            collectibleSummation.collectibleDetailList.forEachIndexed { index, item ->
                CollectibleDetailListItem(
                    number = index,
                    detail = item
                )
            }
        }
    }
}

@Preview
@Composable
private fun CollectibleDetailPreview() {
    LostarkTheme {
        CollectibleDetail(
            mockCollectibleSummationContent()
        )
    }
}


