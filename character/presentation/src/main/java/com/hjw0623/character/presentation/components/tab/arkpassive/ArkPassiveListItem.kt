package com.hjw0623.character.presentation.components.tab.arkpassive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockArkPassiveEffectContent
import com.hjw0623.character.presentation.model.arkpassive.Effect
import com.hjw0623.core.presentation.designsystem.LostArkEnlightenment
import com.hjw0623.core.presentation.designsystem.LostArkEvolution
import com.hjw0623.core.presentation.designsystem.LostArkLeap
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun ArkPassiveListItem(
    effectList: List<Effect>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            effectList.forEach { effect ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        AsyncImage(
                            model = effect.icon,
                            contentDescription = "효과 아이콘",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = effect.tier,
                            style = Typography.bodyMedium.copy(
                                fontSize = 10.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = effect.effect,
                            style = Typography.bodyMedium.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = when(effect.type){
                                    "진화" -> LostArkEvolution
                                    "깨달음" -> LostArkEnlightenment
                                    "도약" -> LostArkLeap
                                    else -> LostArkPurple
                                }
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Lv." + effect.level,
                            style = Typography.bodyMedium.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = when(effect.type){
                                    "진화" -> LostArkEvolution
                                    "깨달음" -> LostArkEnlightenment
                                    "도약" -> LostArkLeap
                                    else -> LostArkPurple
                                }
                            )
                        )
                    }
                    Text(
                        text = effect.description,
                        style = Typography.bodyMedium.copy(
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun ArkPassiveListItemPreview() {
    val list = listOf(
        mockArkPassiveEffectContent("진화"),
        mockArkPassiveEffectContent("진화"),
        mockArkPassiveEffectContent("깨달음"),
        mockArkPassiveEffectContent("깨달음"),
        mockArkPassiveEffectContent("도약"),
        mockArkPassiveEffectContent("도약")
    )
    LostarkTheme {
        ArkPassiveListItem(
            effectList = list
        )
    }
}