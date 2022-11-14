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
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.hours

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
        val notes = ArrayList<Note>()
        //в этот день
        allNotes.value?.forEach { note ->
            if (note.date_start.year == date.year && note.date_start.month == date.month && note.date_start.day == date.day) {
                notes.add(note)
            }
        }
        val hours = ArrayList<Hour>()
        //смотрим по часам
        for (i in 0..22) {
            val hour = Hour("$i:00 - ${(i + 1)}:00")
            notes.forEach {
                if (it.date_start.hours==i || it.date_start.hours==i+1) {
                    hour.note = it
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

    fun addNote(note: Note?) {
        _allNotes.value?.plus(note)
    }
}