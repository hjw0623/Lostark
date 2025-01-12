@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.hjw0623.character.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import kotlinx.coroutines.launch


@Composable
fun CharacterDetailTab() {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("장비", "아크패시브", "스킬", "아바타", "보유 캐릭터", "수집형포인트")
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0
    )
    val tabIndex = pagerState.currentPage

    Column(modifier = Modifier.fillMaxSize()) {
        // 스크롤 가능한 TabRow with Custom Colors
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.primaryContainer // TabRow 배경색을 흰색으로 설정
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
                        maxLines = 1, // 한 줄로 제한
                        overflow = TextOverflow.Ellipsis, // 길 경우 줄임표 처리
                        color = if (tabIndex == index) LostArkBlack else LostArkGray, // 선택 상태에 따른 글자 색상 변경
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Pager 내용 렌더링
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = true
        ) { page ->
            when (page) {
                0 -> Text("장비 내용", Modifier.padding(16.dp))
                1 -> Text("아크패시브 내용", Modifier.padding(16.dp))
                2 -> Text("스킬 내용", Modifier.padding(16.dp))
                3 -> Text("아바타 내용", Modifier.padding(16.dp))
                4 -> Text("보유 캐릭터 내용", Modifier.padding(16.dp))
                5 -> Text("수집형포인트 내용", Modifier.padding(16.dp))
                else -> Text("알 수 없는 페이지")
            }
        }
    }
}



@Preview
@Composable
private fun CharacterDetailTabPreview() {
    LostarkTheme {
        CharacterDetailTab()
    }
}


