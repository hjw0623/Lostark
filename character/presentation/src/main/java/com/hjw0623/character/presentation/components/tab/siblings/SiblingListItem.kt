package com.hjw0623.character.presentation.components.tab.siblings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockSiblingContent
import com.hjw0623.character.presentation.model.siblings.SiblingUi
import com.hjw0623.core.presentation.designsystem.*

@Composable
fun SiblingListItem(
    sibling: SiblingUi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = LostArkWhite)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = sibling.classIcon,
                contentDescription = "클래스 이미지",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${sibling.itemAvgLevel} ${sibling.characterClassName}",
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = sibling.characterName,
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go",
                tint = LostArkBlack,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SiblingListItemPreview() {
    LostarkTheme {
        SiblingListItem(
            sibling = mockSiblingContent(),
            onClick = {}
        )
    }
}

