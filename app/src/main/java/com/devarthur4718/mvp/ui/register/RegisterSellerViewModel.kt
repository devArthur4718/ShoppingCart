package com.devarthur4718.mvp.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devarthur4718.mvp.repository.LoginRepository
import com.devarthur4718.mvp.repository.business.Business
import com.devarthur4718.mvp.repository.business.BusinessRepository
import com.devarthur4718.mvp.repository.database.ApplicationDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterSellerViewModel(application: Application) : AndroidViewModel(application) {


    private val businesRepository: BusinessRepository
    val currentBussinessData: LiveData<List<Business>>

    private val _onDataSaved = MutableLiveData<Boolean>()
    val onDataSaved: LiveData<Boolean> get() = _onDataSaved

    init {
        val businessDao = ApplicationDatabase.getDatabase(application).BussinessDAO()
        businesRepository = BusinessRepository(businessDao)
        currentBussinessData = businesRepository.currentBusiness
    }

    fun saveBusinessData(business: Business) = viewModelScope.launch(Dispatchers.IO) {
        businesRepository.insert(business)
    }

    fun updateBussinessData(data: Business) = viewModelScope.launch(Dispatchers.IO) {
        var currentData = businesRepository.currentBusiness.value?.first()
        currentData?.apply {
            email = data.email ?: currentData.email
            contactname = data.contactname ?: currentData.contactname
            nickname = data.nickname ?: currentData.nickname
            business_name = data.business_name ?: currentData.business_name
            business_address = data.business_address ?: currentData.business_address
            yapePhone = data.yapePhone ?: currentData.yapePhone

        }
        currentData?.let { businesRepository.insert(it) }


    }
}