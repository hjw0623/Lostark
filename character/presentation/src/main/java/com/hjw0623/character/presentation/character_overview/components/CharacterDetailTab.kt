package com.hjw0623.character.presentation.character_overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.character_overview.CharacterOverviewState
import com.hjw0623.character.presentation.character_overview.components.tab.arkpassive.ArkPassiveScreen
import com.hjw0623.character.presentation.character_overview.components.tab.avatar.AvatarScreen
import com.hjw0623.character.presentation.character_overview.components.tab.collectibles.CollectibleScreen
import com.hjw0623.character.presentation.character_overview.components.tab.gear.GearScreen
import com.hjw0623.character.presentation.character_overview.components.tab.siblings.SiblingScreen
import com.hjw0623.character.presentation.character_overview.components.tab.skill.SkillScreen
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkLightBlue
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import kotlinx.coroutines.launch

@Composable
fun CharacterDetailTab(
    modifier: Modifier = Modifier,
    state: CharacterOverviewState,
    onSiblingClick: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("장비", "아크패시브", "스킬", "아바타", "보유 캐릭터", "수집형포인트")
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0
    )
    val tabIndex = pagerState.currentPage

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.background,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(2.dp)
                        .padding(horizontal = 24.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            divider = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = LostArkLightBlue),
            userScrollEnabled = true
        ) { page ->
            when (page) {
                0 -> GearScreen(
                    gearUis = state.gearList,
                    accessoriesUis = state.accessoriesList,
                    abilityStoneUi = state.abilityStone,
                    braceletUi = state.bracelet,
                    elixirUi = state.elixir,
                    transcendenceUi = state.transcendence,
                    gemsList  = state.gemsList,
                    statsUi = state.stats,
                    engravingUiList = state.engraving,
                    card = state.card,
                    cardEffectList = state.cardEffect
                )
                1 -> ArkPassiveScreen(state.arkPassive)
                2 -> SkillScreen(state.skillList, state.gemsList)
                3 -> AvatarScreen(state.avatarList)
                4 -> SiblingScreen(
                    siblingList = state.siblingList,
                    onClick = onSiblingClick,
                )
                5 -> CollectibleScreen(state.collectibleSummationList)
                else -> Text("알 수 없는 페이지")
            }
        }
    }
}



@Preview
@Composable
private fun CharacterDetailTabPreview() {
    LostarkTheme {
        CharacterDetailTab(
            state = CharacterOverviewState(),
            onSiblingClick = { },
        )
    }
}


