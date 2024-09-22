
import com.eps.pakistan.network.common.HEADER_PREFIX_AUTHORIZATION
import com.eps.pakistan.network.common.HEADER_TITLE_AUTHORIZATION
import com.eps.pakistan.network.common.api.response.ApiResponse
import com.eps.pakistan.network.common.api.response.EpsApiResponse
import com.eps.pakistan.network.common.api.response.EpsApiResponseNotSigned
import com.eps.pakistan.network.common.api.response.toApiResponseError
import com.eps.pakistan.network.common.storage.NetworkStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.isSuccess

internal suspend inline fun <reified T> HttpClient.getRequestNotAuth(
    url: String,
    queryParams: Map<String, String> = mapOf(),
): EpsApiResponse<T> {
    return try {
        val response = get {
            url(url)
            queryParams.forEach { queryParam ->
                parameter(queryParam.key, queryParam.value)
            }
        }
        if (response.status.isSuccess()) {
            ApiResponse.Success(response.body())
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}

internal suspend inline fun <reified T> HttpClient.getRequestAuth(
    url: String,
    queryParams: Map<String, String> = mapOf(),
): EpsApiResponse<T> {
    return try {
        val response = get {
            url(url)
            header(HEADER_TITLE_AUTHORIZATION, "$HEADER_PREFIX_AUTHORIZATION ${NetworkStorage.accessToken}")
            queryParams.forEach { queryParam ->
                parameter(queryParam.key, queryParam.value)
            }
        }
        if (response.status.isSuccess()) {
            ApiResponse.Success(response.body())
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}

internal suspend inline fun <reified T> HttpClient.getRequestNotSigned(
    url: String,
    queryParams: Map<String, String> = mapOf(),
): EpsApiResponseNotSigned<T> {
    return try {
        val response = get {
            url(url)
            queryParams.forEach { queryParam ->
                parameter(queryParam.key, queryParam.value)
            }
        }
        if (response.status.isSuccess()) {
            ApiResponse.Success(response.body())
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}
