package com.hjw0623.character.presentation.components.tab.siblings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.model.siblings.SiblingUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun SiblingScreen(
    siblingList: List<SiblingUi>
) {
    val groupedSiblings: Map<String, List<SiblingUi>> = siblingList
        .groupBy { it.serverName }
        .mapValues { (_, siblings) ->
            siblings.sortedByDescending { it.itemAvgLevel.replace(",", "").toDoubleOrNull() ?: 0.0 }
        }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedSiblings.forEach { (serverName, characters) ->
            item {
                Text(
                    text = serverName,
                    modifier = Modifier.padding(16.dp),
                    style = Typography.bodyLarge
                )
            }

            items(characters) { sibling ->
                SiblingListItem(sibling = sibling, onClick = {  })
            }
        }
    }
}


private fun mockSiblingList() = listOf(
    SiblingUi("https://example.com/image1.png", "카제로스", "택티컬맘마통", "창술사", "1644.17"),
    SiblingUi("https://example.com/image2.png", "카제로스", "이슈타르", "바드", "1523.45"),
    SiblingUi("https://example.com/image3.png", "니나브", "다크나이트", "데모닉", "1580.33"),
    SiblingUi("https://example.com/image4.png", "니나브", "빛의성기사", "홀리나이트", "1655.80"),
    SiblingUi("https://example.com/image5.png", "아브렐슈드", "섀도우헌터", "블레이드", "1622.88"),
)

@Preview
@Composable
private fun SiblingScreenPreview() {
    LostarkTheme {
        SiblingScreen(siblingList = mockSiblingList())
    }
}
