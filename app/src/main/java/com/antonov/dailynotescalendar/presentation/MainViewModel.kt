package com.antonov.dailynotescalendar.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonov.dailynotescalendar.domain.model.Item

class MainViewModel: ViewModel() {
    var listItems: MutableLiveData<ArrayList<Item>>? = null
    var longPressedItem: MutableLiveData<Item>? = null
}