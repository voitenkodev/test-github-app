package com.voitenko.testgithubapp.githubrepositories.components

import Design
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.voitenko.testgithubapp.models.Repository
import components.texts.TextBody1
import components.texts.TextBody2
import components.texts.TextHead2

@Composable
internal fun RepositoryItemContent(
    modifier: Modifier = Modifier,
    repo: Repository,
    onClick: (name: String, owner: String) -> Unit
) {
    Column(
        modifier = modifier
            .background(Design.colors.backgrounds.secondary)
            .border(
                color = Design.colors.shadow,
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick.invoke(repo.name, repo.owner) }
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextHead2(text = repo.fullName ?: "-")

        TextBody1(text = repo.description ?: "-")

        TextBody2(
            text = "Stars - ${repo.stargazersCount}",
            color = Design.colors.caption
        )
    }
}