package com.zzz.feature.job.community.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.feature.job.community.presentation.components.ForumCard
import com.zzz.feature.job.community.presentation.components.GroupCard

data class GroupUi(
    val title: String,
    val members: Int
)

data class ForumPostUi(
    val author: String,
    val question: String,
    val time: String,
    val comments: Int
)

val sampleGroups = listOf(
    GroupUi(
        title = "2026 AISSMS COE",
        members = 467
    ),
    GroupUi(
        title = "CSE 2026 Batch",
        members = 126
    ),
    GroupUi(
        title = "IT Department 2026",
        members = 98
    ),
    GroupUi(
        title = "Placement Preparation 2026",
        members = 312
    )
)

val samplePosts = listOf(
    ForumPostUi(
        author = "Riya Shah",
        question = "What will be the impact of artificial intelligence on the future job market?",
        time = "Yesterday",
        comments = 10
    ),
    ForumPostUi(
        author = "Aman Kulkarni",
        question = "How was the Cognizant aptitude round? Any important topics to focus on?",
        time = "2 days ago",
        comments = 3
    ),
    ForumPostUi(
        author = "Sneha Patil",
        question = "How did you prepare for soft-skills, group discussions (GD), and interview techniques?",
        time = "4 days ago",
        comments = 4
    ),
    ForumPostUi(
        author = "Rahul Deshmukh",
        question = "Is DSA enough for product-based companies or should we also focus heavily on core subjects?",
        time = "1 week ago",
        comments = 12
    )
)

@Composable
fun CommunityScreen(
    groups: List<GroupUi> = sampleGroups,
    forumPosts: List<ForumPostUi> = samplePosts
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Groups Section
        item {
            SectionTitle("Groups")
        }

        items(groups) { group ->
            GroupCard(
                title = group.title,
                members = "${group.members} members"
            )
        }

        // Forum Section
        item {
            SectionTitle("Forum")
        }

        items(forumPosts) { post ->
            ForumCard(
                name = post.author,
                question = post.question,
                time = post.time,
                comments = "${post.comments} comments"
            )
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}
