package com.zzz.data.remote.domain.student.education.dto

import com.zzz.data.remote.domain.model.AggregateType

data class SaveEducationRequest(
    val degree: String ,
    val stream: String? = null ,
    val instituteName: String ,
    val educationBoard: String ,
    val joiningYear: Int ,
    val completionYear: Int ,
    val aggregateType: AggregateType , // we might have to get rid of this
    val aggregate: Double ,
    val resumeId: String
)
