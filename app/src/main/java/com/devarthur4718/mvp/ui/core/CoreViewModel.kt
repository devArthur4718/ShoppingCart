package com.devarthur4718.mvp.ui.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoreViewModel : ViewModel() {

    private val _hideSearchAction = MutableLiveData<Boolean>()
    val hideSearchAction : LiveData<Boolean> get() = _hideSearchAction

    init {
        _hideSearchAction.value = false
    }

    fun toogleSearchActionVisibility(visibility: Boolean){
        _hideSearchAction.value = visibility
    }
}