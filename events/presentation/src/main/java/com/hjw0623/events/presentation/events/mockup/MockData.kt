package com.hjw0623.events.presentation.events.mockup

import com.hjw0623.core.domain.islands.model.Islands
import com.hjw0623.core.domain.islands.model.Item
import com.hjw0623.core.domain.islands.model.IslandRewardItems

fun mockEventContent(
    name: String = "하모니 섬",
    rewardItem: String = "실링"
): Islands {
    return Islands(
        categoryName = "모험섬",
        contentsName = name,
        contentsIcon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/island_icon/island_icon_55.png",
        minItemLevel = 250,
        startTimes = listOf(
            "2024-12-19T11:00:00",
            "2024-12-19T13:00:00",
            "2024-12-19T19:00:00",
            "2024-12-19T21:00:00",
            "2024-12-19T23:00:00",
            "2024-12-23T11:00:00",
            "2024-12-23T13:00:00",
            "2024-12-23T19:00:00",
            "2024-12-23T21:00:00",
            "2024-12-23T23:00:00"
        ),
        location = name,
        rewardItems = listOf(
            IslandRewardItems(
                itemLevel = 0,
                items = listOf(
                    Item(
                        name = "하모니 섬의 마음",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/tokenitem/tokenitem_9.png",
                        grade = "유물",
                        startTimes = null
                    ),
                    Item(
                        name = "천상의 하모니",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/tokenitem/tokenitem_9.png",
                        grade = "일반",
                        startTimes = null
                    ),
                    Item(
                        name = "투명한 소리의 상자",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_4_227.png",
                        grade = "영웅",
                        startTimes = null
                    ),
                    Item(
                        name = "향기로운 소리의 상자",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_4_226.png",
                        grade = "영웅",
                        startTimes = null
                    ),
                    Item(
                        name = "조화로운 소리의 상자",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_4_224.png",
                        grade = "영웅",
                        startTimes = null
                    ),
                    Item(
                        name = "실링",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/etc/etc_14.png",
                        grade = "일반",
                        startTimes = null
                    ),
                    Item(
                        name = "대양의 주화 상자",
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_2_8.png",
                        grade = "영웅",
                        startTimes = listOf(
                            "2024-12-30T11:00:00",
                            "2024-12-30T13:00:00",
                            "2024-12-30T19:00:00",
                            "2024-12-30T21:00:00",
                            "2024-12-30T23:00:00"
                        )
                    ),
                    Item(
                        name = rewardItem,
                        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/money/money_3.png",
                        grade = "일반",
                        startTimes = listOf(
                            "2024-12-30T11:00:00",
                            "2024-12-30T13:00:00",
                            "2024-12-30T19:00:00",
                            "2024-12-30T21:00:00",
                            "2024-12-30T23:00:00"
                        )
                    )
                )
            ),
        )
    )
}