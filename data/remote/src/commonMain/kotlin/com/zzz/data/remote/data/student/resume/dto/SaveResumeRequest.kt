package com.zzz.data.remote.data.student.resume.dto


/**
 *
 * Same for update request
 *
 * Referenced by Experience , Social Links
 *
 * @param bio Short personal summary.
 * @param gender Student’s gender information.
 * @param basedIn The location or city where the student is currently based.
 * @param skills List of skills or technologies known by the student.
 * @param achievements Summary of notable achievements, awards, or recognitions.
 * @param studentId Reference key linking to the corresponding [com.placement.clave.auth.data.entity.StudentEntity].
 *
 *
 * @author zyzz
 */
data class SaveResumeRequest(
    val bio : String ,
    val gender : String,
    val basedIn : String,
    val skills : List<String>,
    val achievements : String ,
    val studentId : String,
)

