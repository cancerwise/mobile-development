package com.example.cancerwise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cancerwise.model.FakeRewardDataSource

class HomeViewModel : ViewModel() {

    fun getData() = FakeRewardDataSource.dummyProduct
}