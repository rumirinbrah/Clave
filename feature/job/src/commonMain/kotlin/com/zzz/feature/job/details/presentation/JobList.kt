package com.zzz.feature.job.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zzz.data.remote.domain.model.Job
import kotlinx.coroutines.launch

val jobList = listOf(dummyJob)

@Composable
fun JobListRoot(
    jobs: List<Job> = jobList,
    onApplyClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { jobTabs.size }
    )
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        SearchBar()

        TabRow(selectedTabIndex = pagerState.currentPage) {
            jobTabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tab.title,
                            fontWeight = if (pagerState.currentPage == index)
                                FontWeight.SemiBold
                            else
                                FontWeight.Normal
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(12.dp)
        ) { page ->

            when (jobTabs[page].type) {

                JobTabType.JOBS -> {
                    JobListPage(jobs, onApplyClick)
                }

                JobTabType.APPLICATIONS -> {

                }

                JobTabType.OFFERS -> {

                }
            }
        }
    }
}

@Composable
fun JobListPage(
    jobs: List<Job>,
    onApplyClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(jobs.size) {
            val job = jobs[it]
            SingleJobItem(job, onApplyClick)
        }
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(
                Color(0xFFEAF2FF),
                RoundedCornerShape(12.dp)
            )
            .border(1.dp, Color(0xFF3B82F6), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(Icons.Default.Search, contentDescription = null)

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Search by department",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

    }
}
