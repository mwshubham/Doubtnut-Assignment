package com.example.whatsnewinandroid.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsnewinandroid.constants.ErrorType
import com.example.whatsnewinandroid.data.repository.DoubtnutRepository
import com.example.whatsnewinandroid.data.responses.DoubtnutDataResponse
import com.example.whatsnewinandroid.testing.OpenForTesting
import com.example.whatsnewinandroid.ui.states.ApiState
import com.example.whatsnewinandroid.ui.states.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
class SearchFragmentVM
@Inject constructor(
    private val doubtnutRepository: DoubtnutRepository
) : ViewModel() {
    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    private val _searchViewState = MutableLiveData<SearchViewState>()
    val searchViewState: LiveData<SearchViewState>
        get() = _searchViewState

    private val _apiState = MutableLiveData<ApiState>()
    val apiState: LiveData<ApiState>
        get() = _apiState

    private val _data = MutableLiveData<DoubtnutDataResponse>()
    val data: LiveData<DoubtnutDataResponse>
        get() = _data

    init {
        Timber.i("init{}")
        _searchViewState.postValue(SearchViewState.Cleared)
    }

    fun userTyping() {
        _searchViewState.postValue(SearchViewState.Typing)
    }

    fun clearSearchInput() {
        _searchViewState.postValue(SearchViewState.Cleared)
    }

    fun dismiss() {
        _screenState.postValue(ScreenState.Dismiss)
    }

    private fun loadingData() {
        _apiState.postValue(ApiState.Loading)
    }

    fun postSuccess() {
        _apiState.postValue(ApiState.Success)
    }

    fun postError(errorType: ErrorType) {
        _apiState.postValue(ApiState.Error(errorType))
    }

    /**
     * Fetch address list.
     */
    fun getAddressList(queryString: String) {
        Timber.i("queryString: $queryString")
        loadingData()
        viewModelScope.launch(Dispatchers.IO) {
            Timber.i("launch{}")
            try {
                @Suppress("UNUSED_VARIABLE")
                val result = doubtnutRepository.getAddressList(queryString)
                _data.postValue(result.data)
                postSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                postError(ErrorType.UnknownError)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}