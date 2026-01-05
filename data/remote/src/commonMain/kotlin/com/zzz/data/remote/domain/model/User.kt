package com.zzz.data.remote.domain.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * ### User entity
 *
 * @param phoneVerified Whether phone is verified with OTP
 * @param email Optional mail *HASHED*
 * @param role User role
 * @param createdAt Timestamp
 * @author zyzz
 */
@OptIn(ExperimentalTime::class)
data class User(
    val id : String,
    val phoneNumber : String,
    val phoneVerified : Boolean = false,
    val email : String?,
    val role : UserRole,
    val createdAt : Instant
)
/**
 * @author zyzz
 */
enum class UserRole(role : String){
    STUDENT("STUDENT"),
    COORDINATOR("ADMIN"),
}
