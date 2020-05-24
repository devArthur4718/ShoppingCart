package com.devarthur4718.mvp.ui.core.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devarthur4718.mvp.mock.MockedData
import com.devarthur4718.mvp.repository.database.entity.ProductCategory
import com.devarthur4718.mvp.repository.firestore.FirestoreRepository
import java.lang.RuntimeException

class SearchProductViewModel : ViewModel() {

    val db = FirestoreRepository()

    private val _listCategory =MutableLiveData<List<ProductCategory>>()
    val listCategory : LiveData<List<ProductCategory>>get()  = _listCategory


    fun getCategories(){
        if(MockedData.MOCK_FIREBASE){
            _listCategory.value = MockedData.CATEGORY_PRODUCT_MOCK
        }else{
            //Fetch from firebase
            db.fetchCategories().addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w("products", "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }

                if(querySnapshot != null){
                    var querryList = ArrayList<ProductCategory>()
                    for(doc in querySnapshot!!){
                        try {
                            var item = doc.toObject(ProductCategory::class.java)
                            querryList.add(item)

                        }catch (e : RuntimeException){
                            Log.e("Explore", "Error ${e}")
                        }
                    }
                    _listCategory.value = querryList

                }
            }
        }
    }

}