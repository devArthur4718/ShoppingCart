package com.devarthur4718.mvp.ui.core.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devarthur4718.mvp.mock.MockedData
import com.devarthur4718.mvp.repository.database.entity.ProductCategory

class SearchProductViewModel : ViewModel() {


    private val _listCategory =MutableLiveData<List<ProductCategory>>()
    val listCategory : LiveData<List<ProductCategory>>get()  = _listCategory


    fun getCategories(){
        if(MockedData.MOCK_APP_DATA){
            _listCategory.value = MockedData.CATEGORY_PRODUCT_MOCK
        }else{
            //Fetch from firebase
        }
    }
}