package com.example.whatsnewinandroid.ui.states

import com.example.whatsnewinandroid.constants.ErrorType

/**
 * Describes state of the view at any
 * point of time.
 */
sealed class ViewState<ResultType> {

    /**
     * Describes loading state of the UI
     */
    class Loading<ResultType> : ViewState<ResultType>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    /**
     * Describes success state of the UI with
     * [data] shown
     */
    data class Success<ResultType>(
        val data: ResultType
    ) : ViewState<ResultType>()

    /**
     *  Describes error state of the UI
     */
    data class Error<ResultType>(
        val errorType: ErrorType
    ) : ViewState<ResultType>()

    override fun toString(): String {
        if (isLoading()) return "Loading"
        if (isSuccess()) return "Success"
        if (isError()) return "Error"
        return ""
    }

    /**
     * Check if current view state is [Loading].
     *
     * @return True if view is in loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Success].
     *
     * @return True if view is in success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current view state is [Error].
     *
     * @return True if view is in error state, otherwise false.
     */
    fun isError() = this is Error

    companion object {

        /**
         * Creates [ViewState] object with [Loading] state to notify
         * the UI to showing loading.
         */
        fun <ResultType> loading(): ViewState<ResultType> = Loading()

        /**
         * Creates [ViewState] object with [Success] state and [data].
         */
        fun <ResultType> success(data: ResultType): ViewState<ResultType> = Success(data)

        /**
         * Creates [ViewState] object with [Error] state and [ErrorType].
         */
        fun <ResultType> error(errorType: ErrorType): ViewState<ResultType> = Error(errorType)
    }
}