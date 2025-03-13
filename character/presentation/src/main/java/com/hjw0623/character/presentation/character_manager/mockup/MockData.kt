package com.hjw0623.character.presentation.character_manager.mockup

import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi


fun mockCharacterProgressContent(): CharacterProgressUi{
    return CharacterProgressUi(
        icon = "https://cdn-lostark.game.onstove.com/2018/obt/assets/images/common/thumb/lance_master.png",
        server = "카단",
        name = "테스트 캐릭터",
        className = "창술사",
        avgItemLevel = "1,644.17",
        totalGold = 20000,
        earnedGold = 5500,
        raids = emptyList(),
        isExpanded = false,
    )
}