package com.hjw0623.lostark

enum class BottomNavItem(val route: String, val title: String, val icon: Int) {
    CHARACTER_MANAGER("character_manager", "홈", R.drawable.icon_home),
    CHARACTER_SEARCH("character_search", "캐릭터 검색", R.drawable.icon_search),
    EVENT_SCREEN("event_screen", "이벤트", R.drawable.icon_calendar)
}