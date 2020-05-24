package com.example.whatsnewinandroid.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.example.whatsnewinandroid.R
import com.example.whatsnewinandroid.data.responses.Address
import com.example.whatsnewinandroid.data.responses.DoubtnutDataResponse
import com.example.whatsnewinandroid.databinding.FragmentSearchBinding
import com.example.whatsnewinandroid.extentions.afterTextChangedDebounce
import com.example.whatsnewinandroid.extentions.observe
import com.example.whatsnewinandroid.ui.fragments.BaseFragment
import com.example.whatsnewinandroid.ui.states.ApiState
import com.example.whatsnewinandroid.ui.states.ScreenState
import com.example.whatsnewinandroid.utilities.CoreKeyboardUtils
import com.example.whatsnewinandroid.utilities.CoreNetworkUtils
import timber.log.Timber
import javax.inject.Inject

open class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SearchFragmentVM
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        CoreKeyboardUtils.showKeyboard(requireActivity())

        val adapter = SearchAdapter()
        binding.rvSearchResults.adapter = adapter

        binding.tvSearch.afterTextChangedDebounce { queryString ->
            Timber.i("doAfterTextChanged: $queryString")
            if (queryString.isBlank()) {
                viewModel.clearSearchInput()
                @Suppress("UNCHECKED_CAST")
                (binding.rvSearchResults.adapter as ListAdapter<Address, SearchAdapter.ViewHolder>).submitList(
                    emptyList()
                )
                return@afterTextChangedDebounce
            }
            if (!CoreNetworkUtils.isInternetAvailable(requireContext())) {
                findNavController().navigate(R.id.dialog_fragment_internet_unavailable)
                return@afterTextChangedDebounce
            }
            viewModel.userTyping()
            viewModel.getAddressList(queryString.trim())
        }
        initObservers()
    }

    private fun initObservers() {
        observe(viewModel.screenState, ::onScreenStateChange)
        observe(viewModel.searchViewState, ::onSearchViewStateChange)
        observe(viewModel.apiState, ::onApiStateChange)
        observe(viewModel.data, ::onDataChange)
    }

    private fun onScreenStateChange(screenState: ScreenState) {
        Timber.i(screenState.toString())
        when (screenState) {
            is ScreenState.Dismiss -> {
                CoreKeyboardUtils.hideKeyboard(requireActivity())
                findNavController().popBackStack()
            }
        }
    }

    private fun onSearchViewStateChange(searchViewState: SearchViewState) {
        Timber.i(searchViewState.toString())
        when (searchViewState) {
            is SearchViewState.Typing -> {
                // Do Nothing
            }
            // Cleared State will be called twice when cleared from clear button
            is SearchViewState.Cleared -> {
                if (!binding.tvSearch.text?.toString().isNullOrBlank()) {
                    binding.tvSearch.setText("")
                }
            }
        }
    }

    private fun onApiStateChange(apiState: ApiState) {
        Timber.i(apiState.toString())
        when (apiState) {
            is ApiState.Loading -> {
                // Do Nothing
            }

            is ApiState.Success -> {
                // Do Nothing
            }

            is ApiState.Error -> {
                findNavController().navigate(R.id.dialog_fragment_sww)
            }
        }
    }

    private fun onDataChange(response: DoubtnutDataResponse) {
        Timber.i("response: $response")
        @Suppress("UNCHECKED_CAST")
        (binding.rvSearchResults.adapter as ListAdapter<Address, SearchAdapter.ViewHolder>)
            .submitList(
                response.addressList
            )
    }

}

