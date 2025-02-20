package com.hjw0623.character.presentation.character_overview.components.tab.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.character_overview.mockup.mockAvatarContent
import com.hjw0623.character.presentation.model.avatar.AvatarUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun AvatarScreen(
    avatarList: List<AvatarUi>
) {

    val avatarOrder = listOf(
        "무기 아바타", "무기 아바타 덧입기", "상의 아바타", "상의 아바타 덧입기",
        "하의 아바타", "하의 아바타 덧입기", "머리 아바타", "머리 아바타 덧입기",
        "얼굴1 아바타", "얼굴2 아바타", "이동 효과"
    )

    val sortedAvatarList = avatarOrder.map { type ->
        avatarList.find { it.type == type } ?: AvatarUi(
            name = "미보유",
            type = type,
            icon = "",
            grade = "",
            isInner = false
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // 왼쪽, 오른쪽으로 분리
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 왼쪽 그룹 (무기~하의)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sortedAvatarList.take(6).forEach { avatar ->
                    AvatarListItem(
                        avatar = avatar.copy(
                            name = if (avatar.name == "미보유") "미보유" else avatar.name,
                            icon = if (avatar.name == "미보유") "" else avatar.icon
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sortedAvatarList.drop(6).forEach { avatar ->
                    AvatarListItem(
                        avatar = avatar.copy(
                            name = if (avatar.name == "미보유") "미보유" else avatar.name,
                            icon = if (avatar.name == "미보유") "" else avatar.icon
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun AvatarScreenPreview() {
    val list = listOf(
        mockAvatarContent(),
        mockAvatarContent(
            type = "상의 아바타",
            isInner = true
        ),
        mockAvatarContent(
            type = "상의 아바타",
            isInner = false
        ),
        mockAvatarContent(
            type = "하의 아바타",
            isInner = true
        ),
        mockAvatarContent(
            type = "하의 아바타",
            isInner = false
        ),
        mockAvatarContent(
            type = "머리 아바타",
            isInner = true
        ),
        mockAvatarContent(
            type = "머리 아바타",
            isInner = false
        ),
        mockAvatarContent(
            type = "얼굴1 아바타",
            isInner = false
        ),
        mockAvatarContent(
            type = "얼굴2 아바타",
            isInner = false
        ),
        mockAvatarContent(
            type = "이동 효과",
            isInner = false
        ),
    )
    LostarkTheme {
        AvatarScreen(list)
    }
}
