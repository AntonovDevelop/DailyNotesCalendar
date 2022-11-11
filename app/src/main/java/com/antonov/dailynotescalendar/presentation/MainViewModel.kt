package com.antonov.dailynotescalendar.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonov.dailynotescalendar.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val  _allItems = MutableLiveData<List<Item>>()
    val allItems: LiveData<List<Item>> get() = _allItems

    private val  _longPressedItem = MutableLiveData<Item>()
    val longPressedItem: LiveData<Item> get() = _longPressedItem

    fun setDefaultItems(){
        val items = ArrayList<Item>()
        for(i in 1..9){
            items.add(Item(i, "desc$i"))
        }
        _allItems.value = items
    }

}