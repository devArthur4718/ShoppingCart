package com.devarthur4718.mvp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.ui.core.CoreViewModel

open class BaseFragment : Fragment() {

    lateinit var mainViewModel: CoreViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            mainViewModel = ViewModelProvider(this).get(CoreViewModel::class.java)
        } ?: throw  Throwable("Invalid activity")
    }


}