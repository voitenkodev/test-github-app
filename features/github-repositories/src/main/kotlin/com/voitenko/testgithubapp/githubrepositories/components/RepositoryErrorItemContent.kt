package com.voitenko.testgithubapp.githubrepositories.components

import Design
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.texts.TextBody2
import components.texts.TextHead2

@Composable
internal fun RepositoryErrorItemContent(
    msg: String,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Design.colors.backgrounds.secondary)
            .border(
                color = Design.colors.primary_accent,
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = retry)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            TextHead2(
                text = "Error! (Click to Retry)",
                fontWeight = FontWeight.Bold,
                color = Design.colors.content
            )
            TextBody2(
                text = msg,
                color = Design.colors.content
            )
        }
    )
}