package com.devarthur4718.mvp.ui.core.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.FragmentHomeBinding
import com.devarthur4718.mvp.repository.database.entity.ProductCategory
import com.devarthur4718.mvp.ui.base.BaseFragment
import com.devarthur4718.mvp.ui.recyclerview.CategoryAdapter

class SearchProductFragment : BaseFragment() {

    private lateinit var viewModel: SearchProductViewModel
    private lateinit var binding : FragmentHomeBinding
    private val adapter by lazy { CategoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(this).get(SearchProductViewModel::class.java)

        viewModel.getCategories()

        setAdapters()
        setObservables()
    }

    private fun setObservables() {
        viewModel.listCategory.observe(viewLifecycleOwner, Observer { onProductListReceived(it)  })
        mainViewModel.searchQuerry.observe(viewLifecycleOwner, Observer { onSearchQuerryReceived(it) })
    }

    private fun onSearchQuerryReceived(querry: String?) {
        querry?.let { viewModel.searchCategories(it) }
    }

    private fun onProductListReceived(data: List<ProductCategory>) {

        if(data.isEmpty()){
            //No itens
        }else{
            adapter.data = data
        }
    }

    private fun setAdapters() {
        binding.rvProducts.adapter = adapter
    }
}
