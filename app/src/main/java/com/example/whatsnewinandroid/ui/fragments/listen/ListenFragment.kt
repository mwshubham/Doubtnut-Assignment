package com.example.whatsnewinandroid.ui.fragments.listen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsnewinandroid.R
import com.example.whatsnewinandroid.ui.fragments.BaseFragment

class ListenFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listen, container, false)
    }

}
