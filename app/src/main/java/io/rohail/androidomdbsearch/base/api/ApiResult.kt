package io.rohail.androidomdbsearch.base.api


/**
 * Result is usually created by the Repository classes where they return
 * `LiveData or Flow <Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val message: String, val error: ApiError) : ApiResult<T>()
}