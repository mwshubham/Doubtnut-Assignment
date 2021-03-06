package com.example.whatsnewinandroid.ui.fragments.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.whatsnewinandroid.R
import com.example.whatsnewinandroid.databinding.FragmentNewsDetailsBinding
import com.example.whatsnewinandroid.extentions.observeNotNull
import com.example.whatsnewinandroid.ui.fragments.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

class NewsDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: NewsDetailsFragmentVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news_details, container, false
        )
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.publishedAt = args.publishedAt
        binding.root.transitionName = args.publishedAt

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.newsArticle.observeNotNull(viewLifecycleOwner) { newsArticle ->
            Timber.i("newsArticle: $newsArticle")
        }

        initListeners()
        initObservers()
    }

    private fun initListeners() {

    }

    private fun initObservers() {
    }

}