package com.example.whatsnewinandroid.ui.states

import com.example.whatsnewinandroid.constants.ErrorType

/**
 * Different states for [ApiState].
 *
 * @see BaseViewState
 */
sealed class ApiState : BaseViewState {

    object Loading : ApiState()

    object Success : ApiState()

    data class Error(val errorType: ErrorType) : ApiState()

    override fun toString(): String {
        if (isLoading()) return "Loading"
        if (isSuccess()) return "Success"
        if (isError()) return "Error"
        return ""
    }

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

}