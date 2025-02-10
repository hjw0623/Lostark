package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.mockup.mockAbilityStoneContent
import com.hjw0623.character.presentation.mockup.mockBraceletContent
import com.hjw0623.character.presentation.mockup.mockCardContent
import com.hjw0623.character.presentation.mockup.mockCardEffectContent
import com.hjw0623.character.presentation.mockup.mockElixirContent
import com.hjw0623.character.presentation.mockup.mockEngravingContent
import com.hjw0623.character.presentation.mockup.mockEquipmentContent
import com.hjw0623.character.presentation.mockup.mockGemContent
import com.hjw0623.character.presentation.mockup.mockJewelryContent
import com.hjw0623.character.presentation.mockup.mockStatsContent
import com.hjw0623.character.presentation.mockup.mockTranscendenceUi
import com.hjw0623.character.presentation.model.card.CardEffectUi
import com.hjw0623.character.presentation.model.card.CardUi
import com.hjw0623.character.presentation.model.engraving.EngravingUi
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.model.gem.GemsUi
import com.hjw0623.character.presentation.model.gear.TranscendenceUi
import com.hjw0623.character.presentation.model.profile.StatsUi
import com.hjw0623.core.presentation.designsystem.LostArkLightBlue
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun GearScreen(
    gearUis: List<GearUi>,
    accessoriesUis: List<AccessoriesUi>,
    abilityStoneUi: AbilityStoneUi,
    braceletUi: BraceletUi,
    elixirUi: ElixirUi,
    transcendenceUi: TranscendenceUi,
    gemsList: List<GemsUi>,
    statsUi: StatsUi,
    engravingUiList: List<EngravingUi>,
    card: List<CardUi>,
    cardEffectList: List<CardEffectUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val order = listOf("투구", "어깨", "상의", "하의", "장갑", "무기")
                gearUis.sortedBy { order.indexOf(it.type) }
                    .forEach { item ->
                        EquipmentListItem(item)
                    }
            }
            Column(
                modifier = Modifier
                    .weight(0.5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                accessoriesUis.forEach { item ->
                    AccessoriesListItem(item)
                }
                AbilityStoneItem(abilityStoneUi)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .background(
                    color = LostArkWhite,
                    shape = RoundedCornerShape(8.dp)
                )
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1.4f)
                ) {
                    BraceletItem(braceletUi)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ElixirItem(elixirUi)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    TranscendenceItem(transcendenceUi)
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            GemsList(gemsList)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                StatsList(statsUi)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            {
                EngravingsList(engravingUiList)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        CardList(
            cardList = card,
            cardEffectList = cardEffectList,
        )
    }
}


@Preview
@Composable
fun GearListPreview() {
    val equipmentList = listOf(
        mockEquipmentContent(),
        mockEquipmentContent(),
        mockEquipmentContent(),
        mockEquipmentContent(),
        mockEquipmentContent(),
        mockEquipmentContent()
    )
    val jewelryList = listOf(
        mockJewelryContent(),
        mockJewelryContent(),
        mockJewelryContent(),
        mockJewelryContent(),
        mockJewelryContent()
    )
    val gemList = listOf(
        mockGemContent(),
        mockGemContent(),
        mockGemContent(),
        mockGemContent(),
        mockGemContent()
    )
    val engravingList = listOf(
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent()
    )
    val cardList = listOf(
        mockCardContent(),
        mockCardContent(),
        mockCardContent(),
        mockCardContent(),
        mockCardContent(),
    )
    val cardEffectList = listOf(
        mockCardEffectContent(),
        mockCardEffectContent()
    )

    LostarkTheme {
        GearScreen(
            gearUis = equipmentList,
            accessoriesUis = jewelryList,
            abilityStoneUi = mockAbilityStoneContent(),
            braceletUi = mockBraceletContent(),
            elixirUi = mockElixirContent(),
            transcendenceUi = mockTranscendenceUi(),
            gemsList = gemList,
            statsUi = mockStatsContent(),
            engravingUiList = engravingList,
            card = cardList,
            cardEffectList = cardEffectList
        )
    }
}


