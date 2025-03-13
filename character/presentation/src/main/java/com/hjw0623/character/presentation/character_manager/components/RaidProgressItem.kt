package com.hjw0623.character.presentation.character_manager.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.character_manager.model.RaidProgressUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun RaidProgressItem(
    raid: RaidProgressUi,
    onGateToggled: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "${raid.raidName} - ${raid.difficulty}",
                style = Typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = LostArkBlack
            )

            Spacer(modifier = Modifier.height(8.dp))

            raid.gateProgress.forEachIndexed { index, isChecked ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onGateToggled(index) }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { onGateToggled(index) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("관문 ${index + 1}")
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun RaidProgressItemPreview() {
    LostarkTheme {
        RaidProgressItem(
            raid = RaidProgressUi(
                characterId = "test",
                raidName = "test",
                difficulty = "test",
                gateProgress = listOf(true, false, true),
                gateRewards = listOf(111,1111,11111)
            ),
            onGateToggled = {}
        )

    }

}
