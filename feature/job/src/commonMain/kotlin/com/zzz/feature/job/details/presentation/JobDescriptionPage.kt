package com.zzz.feature.job.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
//import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.ActionHeader
import com.zzz.core.ui.presentation.components.ImageComponent
import com.zzz.data.remote.domain.model.EmploymentType
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.JobStatus
import com.zzz.data.remote.domain.model.formatted
import com.zzz.feature.job.details.presentation.components.JobHeader
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
    onBack: () -> Unit
) {
//    BackHandler {
//        onBack()
//    }
    val viewModel = koinViewModel<JobDescriptionViewModel>()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { },
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
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            JobHeader(jobPost, onBack)

            SectionContainer("Eligible Courses"){
                FlowRow(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    jobPost.eligibleCourses.forEach { course ->
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(MaterialTheme.colorScheme.background) // 👈 NOT pure white
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = course,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }

            SectionContainer("Requirements"){
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {

                    Text(
                        text = jobPost.eligibilityCriteria,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Skills required:",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        text = jobPost.requiredSkills.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            SectionContainer(
                "Job Description"
            ){
                Text(
                    text = jobPost.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }

            SectionContainer(
                "Selection Process"
            ){
                Text(
                    text = jobPost.selectionProcess,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

@Composable
fun SectionContainer(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            .padding(16.dp) // inner padding
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        content()
    }
}
