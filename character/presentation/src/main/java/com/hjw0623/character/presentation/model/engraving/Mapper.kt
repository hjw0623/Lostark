package com.hjw0623.character.presentation.model.engraving

import com.hjw0623.character.domain.model.engravings.ArkPassiveEffect
import com.hjw0623.character.presentation.util.getEngravingIconUrl

fun ArkPassiveEffect.toEngravingUi(): EngravingUi {
    val formatedName = this.name.replace(" ", "")
    val icon = getEngravingIconUrl(formatedName)
    return EngravingUi(
        icon = icon,
        level = this.level,
        name = this.name,
        grade = this.grade,
        abilityStoneLevel = this.abilityStoneLevel ?: 0
    )
}