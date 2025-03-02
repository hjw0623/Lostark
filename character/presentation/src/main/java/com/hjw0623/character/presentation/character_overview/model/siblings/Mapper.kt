package com.hjw0623.character.presentation.character_overview.model.siblings

import com.hjw0623.character.domain.model.siblings.Sibling
import com.hjw0623.character.presentation.character_add.model.CharacterAddUi
import com.hjw0623.character.presentation.character_overview.util.getClassImg

fun Sibling.toSiblingUi(): SiblingUi {
    val classIcon = getClassImg(this.characterClassName)
    return SiblingUi(
        classIcon = classIcon,
        serverName = this.serverName,
        characterName = this.characterName,
        characterClassName = this.characterClassName,
        itemAvgLevel = this.itemAvgLevel,
    )
}
fun Sibling.toCharacterAddUi(): CharacterAddUi {
    val classIcon = getClassImg(this.characterClassName)
    return CharacterAddUi(
        classIcon =  classIcon,
        serverName = this.serverName,
        characterName = this.characterName,
        characterClassName = this.characterClassName,
        itemAvgLevel = this.itemAvgLevel,
    )
}
