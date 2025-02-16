package com.hjw0623.character.presentation.model.siblings

import com.hjw0623.character.domain.model.siblings.Sibling
import com.hjw0623.character.presentation.util.getClassImg

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
