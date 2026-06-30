package com.zzz.feature.job.user.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.HorizontalSpace
import com.zzz.core.ui.presentation.components.VerticalSpace
import placementapp.feature.job.generated.resources.Res

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(24.dp)

        Text(
            text = "About Clave",
            style = MaterialTheme.typography.titleLarge ,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontStyle = FontStyle.Italic
        )

        VerticalSpace(24.dp)

        Text(
            text = "Campus Placement App",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(.65f)
        )

        VerticalSpace(8.dp)

        Text(
            text = "Version 1.0.0",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )

        VerticalSpace(28.dp)

        HorizontalDivider()

        VerticalSpace(28.dp)

        Text(
            text = "Helping students discover placement opportunities, manage applications and stay updated with campus recruitment drives.",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(.8f)
        )

        VerticalSpace(32.dp)

        AboutSection(
            title = "Features",
            items = listOf(
                "Browse Placement Drives",
                "Apply to Companies",
                "Track Applications",
                "Placement Notifications",
                "Student Profile Management"
            )
        )

        VerticalSpace(24.dp)


        AboutSection(
            title = "Built With",
            items = listOf(
                "Kotlin",
                "Jetpack Compose Multiplatform",
                "Spring Boot",
                "MongoDB",
                "Firebase"
            )
        )

        VerticalSpace(32.dp)


        Text(
            text = "Developed by",
            color = MaterialTheme.colorScheme.onBackground.copy(.6f)
        )

        VerticalSpace(4.dp)


        Text(
            text = "Team Clave",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        VerticalSpace(8.dp)

        Text(
            text = "© 2026",
            color = MaterialTheme.colorScheme.onBackground.copy(.55f)
        )

        VerticalSpace(32.dp)
    }
}

@Composable
fun AboutSection(
    title: String,
    items: List<String>
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        VerticalSpace(12.dp)

        items.forEach {

            Row(
                verticalAlignment = Alignment.Top
            ) {

                Text(
                    text = "•",
                    fontSize = 16.sp
                )

                HorizontalSpace(10.dp)

                Text(
                    text = it,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(.8f)
                )
            }

            VerticalSpace(10.dp)
        }
    }
}