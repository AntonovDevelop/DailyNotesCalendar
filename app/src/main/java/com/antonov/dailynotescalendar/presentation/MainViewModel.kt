package com.antonov.dailynotescalendar.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonov.dailynotescalendar.domain.model.Hour
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.domain.usecase.NotesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val notesListUseCase: NotesListUseCase) :
    ViewModel() {
    private val _allNotes = MutableLiveData<List<Note>>()
    val allNotes: LiveData<List<Note>> get() = _allNotes

    private val _allHours = MutableLiveData<List<Hour>>()
    val allHours: LiveData<List<Hour>> get() = _allHours

    private val _pressedNote = MutableLiveData<Note?>()
    val pressedNote: LiveData<Note?> get() = _pressedNote

    fun setPressedNote(note: Note?) {
        _pressedNote.value = note
    }

    fun setHours(date: Date) {
        val hours = ArrayList<Hour>()
        for (i in 0..22) {
            val hour = Hour("$i:00 - ${(i + 1)}:00")
            allNotes.value?.forEach { note ->
                if (kotlin.math.abs(note.date_start.time - date.time) < 3600000) {
                    hour.note = note
                }
            }
            hours.add(hour)
        }
        _allHours.value = hours
    }

    fun setDefaultNotes(context: Context) {
        viewModelScope.launch {
            notesListUseCase.fillWithStartingNotes(context)
        }
    }

    fun getDataFromRoom() {
        viewModelScope.launch {
            _allNotes.postValue(notesListUseCase.getData())
        }
    }
    fun addNote(note: Note?){
        _allNotes.value?.plus(note)
    }
}