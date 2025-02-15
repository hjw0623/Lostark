package com.hjw0623.character.presentation.components.tab.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.components.GradientBackgroundItem
import com.hjw0623.character.presentation.mockup.mockAvatarContent
import com.hjw0623.character.presentation.model.avatar.AvatarUi
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostArkLegend
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun AvatarListItem(
    avatar: AvatarUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        GradientBackgroundItem(
            icon = avatar.icon,
            color1 = when (avatar.grade) {
                "전설" -> 0xFF361F03
                "영웅" -> 0xFF261331
                else -> 0xFFFAFAFA
            },
            color2 = when (avatar.grade) {
                "전설" -> 0xFF9E5F04
                "영웅" -> 0xFF480D5D
                else -> 0xFFFAFAFA
            },
            bottomStart = 8,
            bottomEnd = 8,
            modifier = Modifier.size(70.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = avatar.name,
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = when(avatar.grade) {
                        "전설" -> LostArkLegend
                        "영웅" -> LostArkPurple
                        else -> LostArkBlue
                    },
                ),
                maxLines = 1,
            )
            Text(
                text = avatar.type,
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LostArkGray,
                ),
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun AvatarListItemPreview() {
    LostarkTheme {
        AvatarListItem(
            mockAvatarContent()
        )
    }
}
