package com.hjw0623.data.model.card

import com.hjw0623.character.domain.model.card.Card
import com.hjw0623.character.domain.model.card.CardList
import com.hjw0623.character.domain.model.card.CardEffect
import com.hjw0623.character.domain.model.card.CardEffectItem


fun CardEffectItemSerializable.toDomain(): CardEffectItem {
    return CardEffectItem(
        name = this.name,
        description = this.description
    )
}

fun CardEffectSerializable.toDomain(): CardEffect {
    return CardEffect(
        index = this.index,
        cardSlots = this.cardSlots,
        items = this.items.map { it.toDomain() }
    )
}

fun CardSerializable.toDomain(): Card {
    return Card(
        slot = this.slot,
        name = this.name,
        icon = this.icon,
        awakeCount = this.awakeCount,
        awakeTotal = this.awakeTotal,
        grade = this.grade,
        tooltip = this.tooltip
    )
}

fun CardsSerializable.toDomain(): CardList {
    return CardList(
        cards = this.cards.map { it.toDomain() },
        cardEffects = this.effects.map { it.toDomain() }
    )
}