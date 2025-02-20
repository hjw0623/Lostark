package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.character_overview.mockup.mockCardContent
import com.hjw0623.character.presentation.character_overview.mockup.mockCardEffectContent
import com.hjw0623.character.presentation.character_overview.model.card.CardEffectUi
import com.hjw0623.character.presentation.character_overview.model.card.CardUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkGray
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkLegend
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun CardList(
    cardList: List<CardUi>,
    cardEffectList: List<CardEffectUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "카드",
            style = Typography.bodyMedium.copy(
                color = LostArkBlack,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(5.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            cardList.forEach { card ->
                CardListItem(card)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black)
                    .align(Alignment.Center)
            )
            Row(modifier = Modifier
                .align(Alignment.Center)
                .background(color = LostArkWhite)
                .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cardEffectList.forEach { cardEffectUi ->
                    Text(
                        text = cardEffectUi.effectName + cardEffectUi.effectGrade+"각" ,
                        style = Typography.bodyMedium.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}



@Composable
fun CardListItem(
    card: CardUi
) {
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(100.dp)
    ) {
        AsyncImage(
            model = card.cardIcon,
            contentDescription = "카드 이미지",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = card.cardLevel.toString(),
            color = LostArkWhite,
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(
                    color = when (card.cardGrade) {
                        "전설" -> LostArkLegend
                        "영웅" -> LostArkPurple
                        "희귀" -> LostArkBlue
                        "고급" -> LostArkGreen
                        else -> LostArkDarkGray
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)) 
                    )
                )
        )

        Text(
            text = card.cardName,
            style = Typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = LostArkWhite,
                fontSize = 10.sp
            ),
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)

        )
    }
}





@Preview
@Composable
private fun CardListPreview() {
    val cardList = listOf(
        mockCardContent(),
        mockCardContent(),
        mockCardContent(),
        mockCardContent(),
        mockCardContent()
    )
    val cardEffectList = listOf(
        mockCardEffectContent(),
        mockCardEffectContent(),
    )
    LostarkTheme {
        CardList(
            cardList = cardList,
            cardEffectList = cardEffectList
        )
    }
}