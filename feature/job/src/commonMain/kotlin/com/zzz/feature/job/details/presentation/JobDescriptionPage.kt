package com.zzz.feature.job.details.presentation

//import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.presentation.components.VerticalSpace
import com.zzz.data.remote.domain.model.EmploymentType
import com.zzz.data.remote.domain.model.Job
import com.zzz.data.remote.domain.model.JobStatus
import com.zzz.feature.job.details.presentation.components.JobHeader
//import io.ktor.util.valuesOf
import org.koin.compose.viewmodel.koinViewModel

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

@Composable
fun JobDescriptionRoot(
    modifier: Modifier = Modifier,
    jobId: String? = null,
    onBack : ()->Unit,
) {
    val viewModel : JobDescriptionViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val events = viewModel.events

    val uriHandler = LocalUriHandler.current


    LaunchedEffect(Unit){
        jobId?.let {
            viewModel.getJobById(it)
        }
    }
    LaunchedEffect(Unit){
        events.collect {event->
            when(event){
                is JobDetailEvents.OpenJobLink -> {
                    uriHandler.openUri(event.url)
                }
            }
        }
    }

    JobDescriptionPage(
        state = state,
        onBack = onBack,
        onApply = {
            viewModel.openJobLink()
        },
        onDidYouApply = {
            viewModel.onDidYouApply(it)
        }
    )

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JobDescriptionPage(
    jobPost: Job = dummyJob,
    state : JobDetailState,
    onBack : ()->Unit,
    onApply : ()->Unit,
    onDidYouApply : (applied : Boolean)->Unit,
) {
//    BackHandler {
//        onBack()
//    }
    val job = remember(state.job) {
        state.job
    }



    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                onApply()
                            },
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
                    if(state.showDidYouApply){
                        VerticalSpace()
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            TextButton(
                                onClick = {
                                    onDidYouApply(true)
                                }
                            ){
                                Text(
                                    "Yes"
                                )
                            }
                            TextButton(
                                onClick = {
                                    onDidYouApply(false)
                                }
                            ){
                                Text(
                                    "No"
                                )
                            }
                        }
                    }
                }

            }
        }
    ) { paddingValues ->

        if(job==null){
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                JobHeader(job, onBack)

                SectionContainer("Eligible Courses"){
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        job.eligibleCourses.forEach { course ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp, bottom = 8.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(MaterialTheme.colorScheme.background)
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
                            text = job.eligibilityCriteria,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Skills required:",
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = job.requiredSkills.joinToString(", "),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                SectionContainer(
                    "Job Description"
                ){
                    Text(
                        text = job.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                SectionContainer(
                    "Selection Process"
                ){
                    Text(
                        text = job.selectionProcess,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

            }
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
            .padding(16.dp)
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
