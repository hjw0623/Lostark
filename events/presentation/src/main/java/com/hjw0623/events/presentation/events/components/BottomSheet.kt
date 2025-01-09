package com.hjw0623.events.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkYellow
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.events.presentation.events.model.IslandUi

@Composable
 fun IslandBottomSheet(
    islandUi: IslandUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = islandUi.imgUrl,
                contentDescription = "island image",
                modifier = Modifier.size(60.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Box(
                    modifier = Modifier
                        .background(
                            when {
                                islandUi.mainRewardItem.contains("실링") -> LostArkGray
                                islandUi.mainRewardItem.contains("주화") -> LostArkOrange
                                islandUi.mainRewardItem.contains("골드") -> LostArkYellow
                                islandUi.mainRewardItem.contains("카드") -> LostArkPurple
                                else -> LostArkGray
                            },
                            RoundedCornerShape(8.dp)
                        )

                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ) {
                    Text(
                        text = when {
                            islandUi.mainRewardItem.contains("실링") -> "실링"
                            islandUi.mainRewardItem.contains("주화") -> "주화"
                            islandUi.mainRewardItem.contains("골드") -> "골드"
                            islandUi.mainRewardItem.contains("카드") -> "카드"
                            else -> "없음"
                        },
                        fontSize = 10.sp,
                        style = Typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.End,
                    )
                }
                Text(
                    text = islandUi.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = Typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            items(islandUi.additionalItem) { item ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = item.icon,
                        contentDescription = "item image",
                        modifier = Modifier.size(40.dp)

                    )
                    Text(
                        text = item.name,
                        style = Typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun IslandBottomSheetPreview() {
    IslandBottomSheet(
        islandUi = previewIsland
    )
}