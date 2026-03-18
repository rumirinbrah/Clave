package com.zzz.data.remote.domain.student.resume.dto

import com.zzz.data.remote.domain.student.education.dto.EducationResponse
import com.zzz.data.remote.domain.student.experience.dto.ExperienceResponse
import com.zzz.data.remote.domain.student.project.dto.ProjectResponse
import com.zzz.data.remote.domain.student.social.dto.SocialLinksResponse

/**
 * Response
 *
 * @author zyzz
 */
data class ResumeResponse(
    val id : String,
    val bio : String,
    val basedIn : String,
    val skills : List<String>,
    val achievements : String,
    val lastUpdated : Long
)

/**
 * Aggregate form
 *
 * @author zyzz
*/
data class EmbeddedResumeResponse(
    val resume : ResumeResponse ,
    val educations : List<EducationResponse> ,
    val experiences : List<ExperienceResponse> ,
    val projects : List<ProjectResponse> ,
    val socialLinks : SocialLinksResponse?
)