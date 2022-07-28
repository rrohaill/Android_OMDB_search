package io.rohail.androidomdbsearch.base.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>) = try {
        handleResponse(call())
    } catch (e: Throwable) {
        handleError<T>(e)
    }

    private fun <T> handleError(e: Throwable): ApiResult.Error<T> {
        return ApiResult.Error(
            message = e.message ?: "",
            error = ApiError(
                message = e.message ?: "",
                response = e.javaClass.name
            )
        )
    }

    private fun <T> handleResponse(response: Response<T>): ApiResult<T> {
        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) ApiResult.Success(body)
            else ApiResult.Error("Unknown error", ApiError(message = "Unknown Error"))
        }
        return buildApiError(response)
    }

    private fun <T> buildApiError(error: Response<T>): ApiResult<T> {
        val msg = "Network call has failed for a following reason: ${error.message()}"
        val err = error.errorBody()?.let {
            val converter: Converter<ResponseBody, ApiError> = RetrofitFactory.retrofit!!
                .responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))
            converter.convert(it)
        } ?: ApiError(
            message = "Error body is null or failed to parse it"
        )
        return ApiResult.Error(msg, err)
    }
}

