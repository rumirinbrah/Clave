package com.zzz.feature.job.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.CircularIconButton
import com.zzz.data.remote.data.notification.NotificationResponse
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.baseline_arrow_back_24


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPage(
    onBack: () -> Unit,
    viewModel: NotificationViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    val notifications = state.notifications
    val isLoading = state.isLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Notifications",
                        style = MaterialTheme.typography.titleMedium ,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground)
                },
                navigationIcon = {
                    CircularIconButton(
                        icon = Res.drawable.baseline_arrow_back_24,
                        contentDescription = "back",
                        onClick = {
                            onBack()
                        },
                        iconSize = 25.dp,
                        contentPadding = 16.dp
                    )
                }
            )
        }
    ) { padding ->

        when {

            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            notifications.isEmpty() -> {
                EmptyNotificationScreen(
                    modifier = Modifier.padding(padding)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        notifications,
                        key = { it.id }
                    ) {
                        NotificationItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyNotificationScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "No Notifications Yet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "You'll receive placement updates and reminders here.",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}