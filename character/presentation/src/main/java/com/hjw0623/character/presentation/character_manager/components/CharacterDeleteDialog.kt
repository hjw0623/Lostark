package com.hjw0623.character.presentation.character_manager.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CharacterDeleteDialog(
    characterName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
        title = {
            Text(
                text = "캐릭터 삭제",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        text = {
            Text(
                text = "\"${characterName}\"(을)를 삭제하시겠습니까?",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        "닫기",
                        fontSize = 16.sp,
                        color =  MaterialTheme.colorScheme.onBackground
                    )
                }

                TextButton(
                    onClick = onConfirm,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        "삭제하기",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CharacterDeleteDialogPreview() {
    LostarkTheme {
        CharacterDeleteDialog(
            characterName = "테스트캐릭터",
            onConfirm = { },
            onDismiss = { }
        )
    }
}