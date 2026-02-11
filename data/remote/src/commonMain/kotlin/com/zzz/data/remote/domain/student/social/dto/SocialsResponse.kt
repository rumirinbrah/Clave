package com.zzz.data.remote.domain.student.social.dto

data class SocialLinksResponse(
    val id : String,
    val portfolio : String?,
    val github : String?,
    val linkedIn : String?,
    val others : List<String>,
)