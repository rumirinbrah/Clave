package com.zzz.data.remote.domain.student.social

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.education.dto.SaveEducationRequest
import com.zzz.data.remote.domain.student.social.dto.SaveSocialLinksRequest

interface SocialLinksSource {

    suspend fun saveSocials(
        request : SaveSocialLinksRequest
    ) : Result<Unit , NetworkError>

    suspend fun updateSocials(
        request : SaveSocialLinksRequest
    ) : Result<Unit , NetworkError>

}