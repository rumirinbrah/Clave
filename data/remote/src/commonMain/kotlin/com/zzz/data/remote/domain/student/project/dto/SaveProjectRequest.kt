package com.zzz.data.remote.domain.student.project.dto

/**
 * @author zyzz
*/
data class SaveProjectRequest(
    val title: String,
    val description: String,
    val keySkills: List<String> = emptyList(),
    val startDate: Long,
    val endDate: Long?,
    val githubLink: String? = null,
    val projectLink: String? = null,
    val resumeId: String
)
