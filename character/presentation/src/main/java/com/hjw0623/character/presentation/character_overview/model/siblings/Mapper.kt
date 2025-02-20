package com.hjw0623.character.presentation.character_overview.model.siblings

import com.hjw0623.character.domain.model.siblings.Sibling
import com.hjw0623.character.presentation.character_overview.util.getClassImg

fun Sibling.toSiblingUi(): com.hjw0623.character.presentation.character_overview.model.siblings.SiblingUi {
    val classIcon = getClassImg(this.characterClassName)
    return com.hjw0623.character.presentation.character_overview.model.siblings.SiblingUi(
        classIcon = classIcon,
        serverName = this.serverName,
        characterName = this.characterName,
        characterClassName = this.characterClassName,
        itemAvgLevel = this.itemAvgLevel,
    )
}
