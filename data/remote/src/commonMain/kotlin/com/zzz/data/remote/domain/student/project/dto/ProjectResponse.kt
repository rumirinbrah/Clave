package com.zzz.data.remote.domain.student.project.dto

import kotlinx.serialization.Serializable

/**
 * @author zyzz
*/
@Serializable
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