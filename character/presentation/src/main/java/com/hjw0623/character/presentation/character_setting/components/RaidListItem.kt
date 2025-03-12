package com.hjw0623.character.presentation.character_setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.domain.model.raid.RaidInfo
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun RaidListItem(
    raid: RaidInfo,
    isSelected: Boolean,
    onRaidClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRaidClick() }
            .padding(8.dp)
            .background(if (isSelected) LostArkGray.copy(alpha = 0.3f) else Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${raid.raidName} (${raid.difficulty})",
            style = Typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = Color.Green
            )
        }
    }
}

@Preview
@Composable
private fun RaidLisItemPreview() {
    LostarkTheme {
        RaidListItem(
            raid = RaidInfo(
                raidName = "테스트 레이드",
                difficulty = "노말",
                itemLevel = 1640,
                gateCount = 3,
                gateRewards = listOf(1100, 1200, 1300)
            ),
            isSelected = true,
            onRaidClick = {  },
        )
    }
}