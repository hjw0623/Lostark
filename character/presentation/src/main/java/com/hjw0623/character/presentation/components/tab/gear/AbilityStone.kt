package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.RootGroupName
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockAbilityStoneContent
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostArkYellow
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun AbilityStoneItem(abilityStoneUi: AbilityStoneUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width(60.dp)
                .padding(5.dp),
        ) {
            AsyncImage(
                model = abilityStoneUi.iconUri,
                contentDescription = "어빌리티스톤 이미지",
                modifier = Modifier.size(40.dp).padding(3.dp),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(18.dp)
                    .background(
                        when {
                            abilityStoneUi.grade.contains("고대") -> LostArkYellow
                            abilityStoneUi.grade.contains("유물") -> LostArkOrange
                            else -> LostArkPurple
                        },
                        shape = RoundedCornerShape(15.dp)
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(
                            when {
                                abilityStoneUi.grade.contains("고대") -> LostArkYellow
                                abilityStoneUi.grade.contains("유물") -> LostArkOrange
                                else -> LostArkPurple
                            }
                        , shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = abilityStoneUi.grade,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = LostArkWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = abilityStoneUi.name,
                color = LostArkYellow,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                ),
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
private fun AbilityStoneItemPreview() {
    LostarkTheme {
        AbilityStoneItem(mockAbilityStoneContent())
    }
}