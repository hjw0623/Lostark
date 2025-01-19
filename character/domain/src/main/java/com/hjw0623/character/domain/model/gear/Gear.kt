package com.hjw0623.character.domain.model.gear

data class Gear(
    val type: String,         // 아이템 종류 (무기, 투구 등)
    val name: String,         // 아이템 이름
    val icon: String,         // 아이템 아이콘 URL
    val grade: String,        // 아이템 등급 (고대, 전설 등)
    val tooltip: String    // 툴팁 정보
)
