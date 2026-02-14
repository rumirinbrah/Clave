package com.zzz.data.remote.domain.student.project.dto

/**
 * @author zyzz
*/
data class ProjectResponse(
    val id: String,
    val title: String,
    val description: String,
    val keySkills: List<String>,
    val startDate: Long,
    val endDate: Long?,
    val githubLink: String?,
    val projectLink: String?,
)