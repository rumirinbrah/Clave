package com.zzz.data.remote.domain.student.education.dto

import com.zzz.data.remote.domain.model.AggregateType

data class EducationResponse(
    val id: String ,
    val degree: String ,
    val stream: String? ,
    val instituteName: String ,
    val educationBoard: String ,
    val joiningYear: Int ,
    val completionYear: Int ,
    val aggregateType: AggregateType ,
    val aggregate: Double ,
)