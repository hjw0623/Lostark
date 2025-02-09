package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.components.GradientBackgroundItem
import com.hjw0623.character.presentation.mockup.mockJewelryContent
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.util.getEffectLevel
import com.hjw0623.character.presentation.util.shortPolishingEffect
import com.hjw0623.core.presentation.designsystem.LostArkAncient
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostArkYellow
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun AccessoriesListItem(accessoriesUi: AccessoriesUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .background(
                color = LostArkWhite,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientBackgroundItem(
                icon = accessoriesUi.iconUri,
                color1 = when(accessoriesUi.grade) {
                    "고대" -> 0xFF3D3325
                    "유물" -> 0xFF341A09
                    else -> 0xFFFAFAFA
                },
                color2 = when(accessoriesUi.grade) {
                    "고대" -> 0xFFDCC999
                    "유물" -> 0xFFA24006
                    else -> 0xFFFAFAFA
                }
            )
            Box(
                modifier = Modifier
                    .width(45.dp)
                    .height(18.dp)
                    .background(
                        when {
                            accessoriesUi.quality == 100 -> LostArkOrange
                            accessoriesUi.quality >= 90 -> LostArkPurple
                            accessoriesUi.quality >= 70 -> LostArkBlue
                            accessoriesUi.quality >= 30 -> LostArkGreen
                            accessoriesUi.quality >= 10 -> LostArkYellow
                            else -> LostArkDarkRed
                        },
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )

            ) {
                Text(
                    text = accessoriesUi.quality.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = LostArkWhite
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (accessoriesUi.polishingEffectList?.isEmpty() == true) {
                Text(
                    text = accessoriesUi.name,
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = when {
                            accessoriesUi.grade == "고대" -> LostArkAncient
                            accessoriesUi.grade == "유물" -> LostArkRelic
                            else -> LostArkBlack
                        }
                    )
                )
            } else {
                accessoriesUi.polishingEffectList?.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val text = getEffectLevel(item)
                        val annotatedText = buildAnnotatedString {
                            append(text)
                            addStyle(
                                style = SpanStyle(background =  when (text) {
                                    "상" -> LostArkOrange
                                    "중" -> LostArkPurple
                                    "하" -> LostArkBlue
                                    else -> LostArkDarkRed
                                },),
                                start = 0,
                                end = text.length
                            )
                        }
                        if (accessoriesUi.tier == 4) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = when (text) {
                                            "상" -> LostArkOrange
                                            "중" -> LostArkPurple
                                            "하" -> LostArkBlue
                                            else -> LostArkDarkRed
                                        },
                                        shape = RoundedCornerShape(3.dp)
                                    )
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = annotatedText,
                                    fontSize = 10.sp,
                                    color = LostArkWhite,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(3.dp))
                        }
                        Text(
                            text = shortPolishingEffect(item),
                            fontSize = 10.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun AccessoriesListItemPreview() {
    LostarkTheme {
        AccessoriesListItem(mockJewelryContent())
    }
}
