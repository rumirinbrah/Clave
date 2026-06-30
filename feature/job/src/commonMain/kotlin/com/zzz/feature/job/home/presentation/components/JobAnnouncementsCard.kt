package com.zzz.feature.job.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.CardContainer
import com.zzz.core.ui.presentation.components.CircularIconButton
import com.zzz.core.ui.presentation.components.HorizontalSpace
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.core.ui.theme.cardShinyBlue
import com.zzz.data.remote.data.student.announcements.AnnouncementResponse
import com.zzz.feature.job.home.presentation.viewmodel.AnnouncementsViewModel
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.baseline_arrow_back_24
import placementapp.feature.job.generated.resources.gear
import placementapp.feature.job.generated.resources.megaphone

@Composable
fun JobAnnouncementsCard(
    modifier: Modifier = Modifier ,
    items: List<AnnouncementResponse> ,
    viewAll: () -> Unit
) {
    CardContainer(
        modifier.fillMaxWidth() ,
        padding = 12.dp
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Announcements (${items.size})" ,
                    style = MaterialTheme.typography.titleMedium ,
                    fontWeight = FontWeight.Bold ,
                    color = MaterialTheme.colorScheme.onBackground
                )
                TextButton(
                    onClick = {
                        viewAll()
                    }
                ) {
                    Text("View All")
                }
            }

            VerticalSpace(

            )

            Column(
                Modifier.fillMaxWidth()
            ) {
                items.take(3).onEach {
                    AnnouncementItem(
                        items = it
                    )
                    VerticalSpace(10.dp)
                }
            }

            /*
            VerticalSpace(10.dp)
            CardContainer(
                Modifier.fillMaxWidth(),
                background = cardShinyBlue,
                padding = 16.dp
            ) {
                Column {
                    //-------ICON AND TITLE------
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircularIconButton(
                            icon = Res.drawable.megaphone,
                            contentDescription = "Icon",
                            onClick = {},
                            tint = Color.White,
                            background = Color.White.copy(0.25f),
                            enabled = false,
                            iconSize = 24.dp,
                            contentPadding = 10.dp,
                        )
                        //-------TITLE AND SUB T------
                        Column {
                            Text(
                                "Pre placement talk",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
//                            Text(
//                                "Today at 9 AM",
//                                style = MaterialTheme.typography.bodySmall,
//                                fontWeight = FontWeight.Medium,
//                                color = Color.White
//                            )
                        }
                    }
                    //-------DESCRIPITON------
                    VerticalSpace(15.dp)
                    Text(
                        "All interested students are requested to attend the Cognizant drive session in CITP Hall.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }

            }
            */
        }
    }
}

@Composable
fun AllAnnouncementsPage(
    modifier: Modifier = Modifier ,
    onBack: () -> Unit
) {
    val viewModel = koinViewModel<AnnouncementsViewModel>()
    val items by viewModel.items.collectAsStateWithLifecycle()
    Column(
        modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIconButton(
                icon = Res.drawable.baseline_arrow_back_24 ,
                contentDescription = "back" ,
                onClick = {
                    onBack()
                } ,
                iconSize = 25.dp ,
                contentPadding = 16.dp
            )

            Text(
                text = "Announcements (${items.size})" ,
                style = MaterialTheme.typography.titleMedium ,
                fontWeight = FontWeight.Bold ,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        HorizontalSpace(16.dp)

        Column(
            Modifier.fillMaxWidth()
        ) {
            items.onEach {
                AnnouncementItem(
                    items = it
                )
                VerticalSpace(10.dp)
            }
        }
    }
}


@Composable
fun AnnouncementItem(
    modifier: Modifier = Modifier ,
    items: AnnouncementResponse
) {
    CardContainer(
        modifier.fillMaxWidth() ,
        background = cardShinyBlue ,
        padding = 16.dp
    ) {
        Column {
            //-------ICON AND TITLE------
            Row(
                Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.Top ,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CircularIconButton(
                    icon = Res.drawable.megaphone ,
                    contentDescription = "Icon" ,
                    onClick = {} ,
                    tint = Color.White ,
                    background = Color.White.copy(0.25f) ,
                    enabled = false ,
                    iconSize = 24.dp ,
                    contentPadding = 10.dp ,
                )
                //-------TITLE AND SUB T------
                Column {
                    Text(
                        items.title ,
                        style = MaterialTheme.typography.titleMedium ,
                        fontWeight = FontWeight.Bold ,
                        color = Color.White
                    )
//                            Text(
//                                "Today at 9 AM",
//                                style = MaterialTheme.typography.bodySmall,
//                                fontWeight = FontWeight.Medium,
//                                color = Color.White
//                            )
                }
            }
            //-------DESCRIPITON------
            VerticalSpace(15.dp)
            Text(
                items.description ,
                style = MaterialTheme.typography.bodyMedium ,
                color = Color.White
            )
        }

    }
}

