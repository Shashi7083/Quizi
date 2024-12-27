package com.srdev.myapplication.quiz.common


/**
 * Jo data api se fetch hoga usme 3 condition honge load, data or error
 *
 * A generic sealed class that represents the state of a resource that is being fetched or processed.
 *
 * This is useful for handling data fetching scenarios where the resource can be in one of three states:
 * - **Loading**: Indicates that the data is being loaded (e.g., an API call is in progress).
 * - **Success**: Indicates that the data has been successfully fetched or processed.
 * - **Error**: Indicates that there was an error while fetching or processing the data.
 *
 * @param T The type of data being wrapped by this resource.
 * @param message A message providing additional information, usually used for errors.
 * @param data The actual data associated with this resource.
 */
sealed class Resource<T>(
    message: String? = null,
    data: T? = null
) {

    /**
     * Represents a loading state where the data is currently being fetched or processed.
     *
     * This state typically occurs when a network request or a time-consuming operation is in progress.
     *
     * Example Usage:
     * ```
     * val loadingState: Resource<MyData> = Resource.Loading()
     * ```
     */
    class Loading<T> : Resource<T>()

    /**
     * Represents a success state where the data has been successfully fetched or processed.
     *
     * @param data The actual data fetched or processed.
     *
     * Example Usage:
     * ```
     * val successState: Resource<MyData> = Resource.Success(data = myData)
     * ```
     */
    class Success<T>(val data: T?) : Resource<T>(data = data)

    /**
     * Represents an error state where there was a failure during the fetch or process operation.
     *
     * @param message A message describing the error or failure reason.
     *
     * Example Usage:
     * ```
     * val errorState: Resource<MyData> = Resource.Error(message = "Unable to fetch data")
     * ```
     */
    class Error<T>(val message: String?) : Resource<T>(message = message)
}