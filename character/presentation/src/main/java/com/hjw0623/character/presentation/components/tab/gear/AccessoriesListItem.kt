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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockJewelryContent
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.util.shortPolishingEffect
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
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
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = accessoriesUi.iconUri,
                contentDescription = "장신구 이미지",
                modifier = Modifier
                    .size(40.dp),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(
                        when {
                            accessoriesUi.quality == 100 -> LostArkOrange
                            accessoriesUi.quality > 90 -> LostArkPurple
                            accessoriesUi.quality > 70 -> LostArkBlue
                            accessoriesUi.quality > 30 -> LostArkGreen
                            accessoriesUi.quality > 10 -> LostArkYellow
                            else -> LostArkDarkRed
                        },
                        shape = RoundedCornerShape(15.dp)
                    )

            ) {
                Text(
                    text = accessoriesUi.quality.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp,
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
                        color = LostArkOrange
                    )
                )
            } else {
                accessoriesUi.polishingEffectList?.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val text = "중"
                        val annotatedText = buildAnnotatedString {
                            append(text)
                            addStyle(
                                style = SpanStyle(background = LostArkPurple),
                                start = 0,
                                end = text.length
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(
                                    color = LostArkPurple,
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
