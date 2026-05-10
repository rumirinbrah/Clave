package com.zzz.feature.job.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.core.ui.theme.CardDark
import com.zzz.core.ui.theme.CardLight
import androidx.compose.foundation.BorderStroke
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.formatted

@Composable
fun SingleJobItem(
    job: Job,
    onApplyClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) {
                CardDark
            } else {
                CardLight
            }
        ),
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(
            0.5.dp,
            MaterialTheme.colorScheme.outline
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(
                verticalAlignment = Alignment.Top
            ) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent, RoundedCornerShape(8.dp))
                ) {
                    ImageComponent(
                        imageUrl = job.companyLogoUrl ?: "",
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                job.companyName, fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                job.role,
                                color = MaterialTheme.colorScheme.onBackground.copy(0.8f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }

                    VerticalSpace(4.dp)

                    InfoText("Job type", job.employmentType.formatted())
                    InfoText("Location", job.location)
                    InfoText("CTC", job.ctc)

                    VerticalSpace(4.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Deadline: ${job.formattedDate}",
                            color = Color(0xFFFF6B6B),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = { onApplyClick(job.id) },
                            enabled = !job.isApplied,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (job.isApplied) Color.LightGray else
                                    MaterialTheme.colorScheme.primary
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 0.dp
                            )
                        ) {
                            Text(text = "Apply Now", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun InfoText(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            ) {
                append("$label: ")
            }

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(value)
            }
        },
        modifier = Modifier.padding(bottom = 1.dp)
    )
}