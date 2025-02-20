package com.hjw0623.character.presentation.character_overview.components.tab.skill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.character.presentation.character_overview.mockup.mockGemContent
import com.hjw0623.character.presentation.character_overview.mockup.mockSkillContent
import com.hjw0623.character.presentation.model.gem.GemsUi
import com.hjw0623.character.presentation.model.skill.SkillUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun SkillScreen(
    skillList: List<SkillUi>,
    gemsList: List<GemsUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        skillList.forEach {
            SkillListItem(
                skill = it,
                gem = gemsList
            )
        }
    }
}

@Preview
@Composable
private fun SkillScreenPreview() {
    val skillList = listOf(
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent(),
        mockSkillContent()
    )
    val gemsList = listOf(
        mockGemContent(),
        mockGemContent(),
        mockGemContent(),
        mockGemContent(),
    )
    LostarkTheme {
        SkillScreen(
            skillList = skillList,
            gemsList = gemsList
        )
    }
}
