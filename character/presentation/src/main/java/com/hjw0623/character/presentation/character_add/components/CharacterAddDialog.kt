package com.hjw0623.character.presentation.character_add.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hjw0623.character.presentation.character_add.model.CharacterAddUi

@Composable
fun CharacterAddDialog(
    character: CharacterAddUi,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "캐릭터 추가") },
        text = { Text(text = "\"${character.characterName}\"(을)를 추가하시겠습니까?") },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onConfirm) {
                Text("예")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("아니오")
            }
        }
    )
}
