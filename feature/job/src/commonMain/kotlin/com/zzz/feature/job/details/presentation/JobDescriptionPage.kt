package com.zzz.feature.job.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
//import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.presentation.components.ActionHeader
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.data.remote.domain.model.EmploymentType
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.JobStatus
import com.zzz.data.remote.domain.model.formatted
import com.zzz.feature.job.details.presentation.components.JobInfoCard
import org.koin.compose.viewmodel.koinViewModel
import placementapp.feature.job.generated.resources.Res
import placementapp.feature.job.generated.resources.baseline_arrow_back_24

val dummyJob = Job(
    id = "job_001",
    companyName = "Google",
    companyLogoUrl = "https://img.lovepik.com/png/20231120/google-logo-vector-sandwiches-data-engineer_642913_wh1200.png",
    role = "Software Engineer",
    employmentType = EmploymentType.FULL_TIME,
    location = "Bangalore, India",
    ctc = "18-25 LPA",
    bondYears = null,
    eligibleCourses = listOf(
        "B.Tech",
        "M.Tech",
        "MCA"
    ),
    eligibilityCriteria = "Minimum 7 CGPA, no active backlogs",
    description = "Work on scalable backend systems and distributed services.",
    requiredSkills = listOf(
        "Kotlin",
        "Java",
        "Spring Boot",
        "Data Structures",
        "System Design"
    ),
    selectionProcess = "Online Assessment -> Technical Interviews -> HR Round",

    // 2026-04-30T23:59:59Z → epoch millis
    lastDateToApply = 1777593599000,

    formattedDate = "30 April 2026",

    applicationLink = "https://careers.google.com/jobs/apply",
    isApplied = false,
    isBookmarked = false,
    jobStatus = JobStatus.OPEN,

    // 2026-04-05T10:30:00Z → epoch millis
    lastUpdated = 1775385000000
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JobDescriptionPage(
    jobPost: Job = dummyJob,
    onBack : ()->Unit
) {
//    BackHandler {
//        onBack()
//    }
    val viewModel = koinViewModel<JobDescriptionViewModel>()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = "Apply Now",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
//                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ActionHeader(
                        title = "Job details",
                        onBack = {
                            onBack()
                        },
                        icon = Res.drawable.baseline_arrow_back_24
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        ImageComponent(
                            imageUrl = jobPost.companyLogoUrl ?: "",
                            contentScale = ContentScale.Fit,
                            size = 60.dp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = jobPost.companyName,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = jobPost.role,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = jobPost.location,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                JobInfoCard(
                    ctc = jobPost.ctc ,
                    jobType = jobPost.employmentType.formatted() ,
                    deadline = jobPost.formattedDate ,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 40.dp)
                        .height(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            SectionHeading("Eligible Courses")
            SectionHeading("Requirements")
            SectionHeading("Job Description")
            SectionHeading("Selection Process")

        }
    }
}

@Composable
fun ParagraphList(items: List<String>) {
    Column {
        items.forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}


@Composable
fun SectionHeading(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 24.dp,
            bottom = 8.dp
        )
    )
}


