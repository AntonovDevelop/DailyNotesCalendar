package com.antonov.dailynotescalendar.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonov.dailynotescalendar.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val  _allNotes = MutableLiveData<List<Note>>()
    val allItems: LiveData<List<Note>> get() = _allNotes

    private val  _longPressedNote = MutableLiveData<Note>()
    val longPressedItem: LiveData<Note> get() = _longPressedNote

    /*fun setDefaultItems(){
        val items = ArrayList<Note>()
        for(i in 0..24){
            items.add(Note(i, "{$i}00:"))
        }
        _allNotes.value = items
    }*/

    fun insertNote(context: Context){
        viewModelScope.launch{

        }
    }
}