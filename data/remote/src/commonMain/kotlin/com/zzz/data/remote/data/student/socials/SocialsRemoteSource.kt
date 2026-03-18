package com.zzz.data.remote.data.student.socials

import com.zzz.core.util.domain.Result
import com.zzz.data.remote.domain.NetworkError
import com.zzz.data.remote.domain.student.social.SocialLinksSource
import com.zzz.data.remote.domain.student.social.dto.SaveSocialLinksRequest
import com.zzz.data.remote.util.ApiRoutes
import com.zzz.data.remote.util.constructUrl
import com.zzz.data.remote.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

/**
 * @author zyzz
*/
class SocialsRemoteSource(
    private val client: HttpClient
)  : SocialLinksSource{

    override suspend fun saveSocials(request: SaveSocialLinksRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_SOCIALS }
            client.post(url){
                setBody(request)
            }
        }
    }

    override suspend fun updateSocials(request: SaveSocialLinksRequest): Result<Unit , NetworkError> {
        return safeNetworkCall<Unit> {
            val url = constructUrl { ApiRoutes.PROFILE_BASE + ApiRoutes.PROFILE_SOCIALS }
            client.put(url){
                setBody(request)
            }
        }
    }
}