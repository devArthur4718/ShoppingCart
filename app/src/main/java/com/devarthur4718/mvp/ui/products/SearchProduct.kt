package com.devarthur4718.mvp.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.devarthur4718.mvp.R

class SearchProduct : Fragment() {

    companion object {
        fun newInstance() = SearchProduct()
    }

    private lateinit var viewModel: SearchProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
