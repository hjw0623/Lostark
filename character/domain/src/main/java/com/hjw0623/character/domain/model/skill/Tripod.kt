package com.hjw0623.character.domain.model.skill

data class Tripod(
   val tier: Int,
   val slot: Int,
   val name: String,
   val icon: String,
   val level: Int,
   val isSelected: Boolean,
   val tooltip: String
)
