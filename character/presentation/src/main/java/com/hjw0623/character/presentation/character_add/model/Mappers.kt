package com.hjw0623.character.presentation.character_add.model

import com.hjw0623.core.domain.character.RoomCharacter

fun CharacterAddUi.toRoomCharacter(): RoomCharacter {
    return RoomCharacter (
        server = this.serverName,
        characterName = characterName,
        className = this.characterClassName,
        avgItemLevel = this.itemAvgLevel.replace(",", "").toDouble()
    )
}