package com.zzz.data.remote.domain.student.social.dto

/**
 * @author zyzz
*/
data class SaveSocialLinksRequest(
    val portfolio : String? ,
    val github : String? ,
    val linkedIn : String? ,
    val others : List<String>,
    val resumeId : String
)