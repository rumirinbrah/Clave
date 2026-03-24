package com.zzz.data.remote.domain.student.social.dto

import kotlinx.serialization.Serializable

/**
 * @author zyzz
*/
@Serializable
data class SaveSocialLinksRequest(
    val portfolio : String? ,
    val github : String? ,
    val linkedIn : String? ,
    val others : List<String>,
    val resumeId : String
)