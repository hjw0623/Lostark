package com.hjw0623.character.presentation.character_overview.components.tab.skill

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.character_overview.mockup.mockGemContent
import com.hjw0623.character.presentation.character_overview.mockup.mockSkillContent
import com.hjw0623.character.presentation.character_overview.model.gem.GemsUi
import com.hjw0623.character.presentation.character_overview.model.skill.SkillUi
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkLegend
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun SkillListItem(
    skill: SkillUi,
    gem: List<GemsUi>
) {
    val gems = gem.filter {
        it.skillName == skill.name
    }.map { it.icon }
    val gemsGrade = gem.filter {
        it.skillName == skill.name
    }.map { it.grade }
    val gemsLevel = gem.filter {
        it.skillName == skill.name
    }.map { it.level }

    val gemIcons = when (gems.size) {
        0 -> listOf("", "")
        1 -> listOf(gems[0], "")
        else -> listOf(gems[0], gems[1])
    }

    val gemGrade = when (gems.size) {
        0 -> listOf("", "")
        1 -> listOf(gemsGrade[0], "")
        else -> listOf(gemsGrade[0], gemsGrade[1])
    }
    val gemLevel = when (gems.size) {
        0 -> listOf("", "")
        1 -> listOf(gemsLevel[0].toString(), "")
        else -> listOf(gemsLevel[0].toString(), gemsLevel[1].toString())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = skill.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = skill.skillLevel.toString() + "레벨",
                    style = Typography.bodyMedium.copy(
                        textAlign = TextAlign.Start,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LostArkBlue
                    )
                )
                Text(
                    text = skill.name,
                    style = Typography.bodyMedium.copy(
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            AsyncImage(
                model = skill.rune?.icon ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = when (skill.rune?.grade) {
                            "유물" -> LostArkRelic
                            "전설" -> LostArkLegend
                            "영웅" -> LostArkPurple
                            "희귀" -> LostArkBlue
                            "고급" -> LostArkGreen
                            else -> LostArkGray
                        },
                        shape = CircleShape
                    )

            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = skill.rune?.name ?: "",
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(4.dp))
            Box {
                AsyncImage(
                    model = gemIcons[0],
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = when (gemGrade[0]) {
                                "유물" -> LostArkRelic
                                "전설" -> LostArkLegend
                                "영웅" -> LostArkPurple
                                "희귀" -> LostArkBlue
                                else -> LostArkGray
                            },
                            shape = CircleShape
                        )
                        .size(40.dp)
                        .clip(CircleShape)

                )
                if (gemLevel[0] != "") {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 2.dp, y = 2.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .size(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = gemLevel[0],
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = LostArkOrange
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(4.dp))
            Box {
                AsyncImage(
                    model = gemIcons[1],
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = when (gemGrade[1]) {
                                "유물" -> LostArkRelic
                                "전설" -> LostArkLegend
                                "영웅" -> LostArkPurple
                                "희귀" -> LostArkBlue
                                else -> LostArkGray
                            },
                            shape = CircleShape
                        )
                        .size(40.dp)
                        .clip(CircleShape)
                )
                if (gemLevel[1] != "") {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 2.dp, y = 2.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .size(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = gemLevel[1],
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = LostArkOrange
                        )
                    }
                }
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (skill.firstTripod?.level != null) {

                    AsyncImage(
                        model = skill.firstTripod.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Column(
                        modifier = Modifier.padding(5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = ("Lv." + skill.firstTripod.level),
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = LostArkBlue
                            )
                        )
                        Text(
                            text = skill.firstTripod.name,
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (skill.secondTripod?.level != null) {


                    AsyncImage(
                        model = skill.secondTripod.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Lv." + skill.secondTripod.level,
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = LostArkBlue
                            )
                        )
                        Text(
                            text = skill.secondTripod.name,
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (skill.thirdTripod?.level != null) {
                    AsyncImage(
                        model = skill.thirdTripod.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Lv." + skill.thirdTripod.level,
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = LostArkBlue
                            )
                        )
                        Text(
                            text = skill.thirdTripod.name,
                            style = Typography.bodyMedium.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun SkillListItemPreview() {
    LostarkTheme {
        SkillListItem(mockSkillContent(), listOf(mockGemContent(), mockGemContent()))
    }
}
