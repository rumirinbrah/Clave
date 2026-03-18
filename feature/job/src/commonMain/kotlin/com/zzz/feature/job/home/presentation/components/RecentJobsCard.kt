package com.zzz.feature.job.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.CardContainer
import com.zzz.core.ui.presentation.components.VerticalSpace

@Composable
fun RecentJobsCard(
    modifier: Modifier = Modifier
) {
    CardContainer(
        modifier.fillMaxWidth() ,
        padding = 12.dp
    ) {
        Column {
            Text(
                text = "Recently posted drives" ,
                style = MaterialTheme.typography.titleMedium ,
                fontWeight = FontWeight.Bold ,
                color = MaterialTheme.colorScheme.onBackground
            )
            VerticalSpace(10.dp)
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RecentJobItem()
                RecentJobItem()
            }
            VerticalSpace()

            JobProgressIndicator()
            VerticalSpace()
        }
    }
}

@Composable
private fun JobProgressIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = "Your placement progress" ,
            style = MaterialTheme.typography.titleMedium ,
            fontWeight = FontWeight.Bold ,
            color = MaterialTheme.colorScheme.onBackground
        )
        VerticalSpace(10.dp)
        Row(
            Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = buildString {
                    append(
                        "Applied"
                    )
                    appendLine()
                    append("8")
                } ,
                style = MaterialTheme.typography.bodyMedium ,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                modifier = Modifier.weight(1f)
            )


            Text(
                text = buildString {
                    append(
                        "Shortlisted"
                    )
                    appendLine()
                    append("8")
                } ,
                style = MaterialTheme.typography.bodyMedium ,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = buildString {
                    append(
                        "Offers"
                    )
                    appendLine()
                    append("0")
                } ,
                style = MaterialTheme.typography.bodyMedium ,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                modifier = Modifier.weight(1f)
            )
            TextButton(
                onClick = {

                } ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "View All" ,
                    style = MaterialTheme.typography.bodyMedium ,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                )
            }

        }
    }
}



