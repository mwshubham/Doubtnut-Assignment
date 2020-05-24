package com.example.whatsnewinandroid.data

import android.content.Context
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.whatsnewinandroid.constants.ErrorType
import com.example.whatsnewinandroid.ui.states.ViewState
import com.example.whatsnewinandroid.utilities.CoreNetworkUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import timber.log.Timber

/**
 * A generic class that can provide a resource backed by both
 * the SQLite database and the network.
 *
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    @ExperimentalCoroutinesApi
    fun asFlow(appContext: Context) = flow {
        emit(ViewState.loading())
        val dbValue = loadFromDb().first()
        if (shouldFetch(dbValue)) {
            emit(ViewState.success(dbValue))
            try {
                val apiResponse = fetchFromNetwork()
                when {
                    apiResponse.isSuccessful && apiResponse.body() != null -> {
                        apiResponse.body()?.let { saveNetworkResult(it) }
                        emitAll(loadFromDb().map { ViewState.success(it) })
                    }
                    else -> {
                        emit(ViewState.error(ErrorType.UnknownError))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                if (CoreNetworkUtils.isInternetAvailable(appContext)) {
                    emit(ViewState.error(ErrorType.UnknownError))
                } else {
                    emit(ViewState.error(ErrorType.NetworkError.InternetUnavailable))
                }
            }
        } else {
            emitAll(loadFromDb().map { ViewState.success(it) })
        }
    }

    @WorkerThread
    protected open fun processResponse(response: Response<RequestType>) = response

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(response: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Response<RequestType>
}