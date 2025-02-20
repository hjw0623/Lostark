package com.hjw0623.character.presentation.character_overview.components.tab.collectibles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.character_overview.mockup.mockCollectibleSummationContent
import com.hjw0623.character.presentation.model.collectibles.CollectibleSummationUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CollectibleScreen(
    collectibleSummationList: List<CollectibleSummationUi>
) {
    var selectedItem by rememberSaveable { mutableStateOf<CollectibleSummationUi?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        collectibleSummationList.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedItem = if (selectedItem == item) null else item
                            }
                    ) {
                        CollectibleSummationListItem(item)
                    }
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        selectedItem?.let { item ->
            CollectibleDetail(
                collectibleSummation = item,
            )
        }
    }
}


@Preview
@Composable
fun PreviewCollectionScreen() {
    val list = listOf(
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
        mockCollectibleSummationContent(),
    )
    LostarkTheme {
        CollectibleScreen(
            list
        )
    }
}
