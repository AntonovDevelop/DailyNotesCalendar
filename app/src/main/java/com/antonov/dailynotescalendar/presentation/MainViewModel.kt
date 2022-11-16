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
import kotlinx.coroutines.Dispatchers
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
        //заполнение таблицы часов
        //запрашиваем информацию о заметках
        val cal1 = GregorianCalendar()
        cal1.time=date

        val notes = ArrayList<Note>()
        //если есть заметки в этот день
        allNotes.value?.forEach { note ->
            val cal2 = GregorianCalendar()
            cal2.time=note.date_start

            if (cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH)
            ) {
                notes.add(note)
            }
        }
        val hours = ArrayList<Hour>()
        //смотрим заметки в этот день по часам
        for (i in 0..22) {
            val hour = Hour("$i:00 - ${(i + 1)}:00")
            notes.forEach {
                val cal = GregorianCalendar()
                cal.time=it.date_start
                if (cal.get(Calendar.HOUR_OF_DAY)==i) {
                    hour.note = it
                }
            }
            //записываем данные в таблицу
            hours.add(hour)
        }
        _allHours.value = hours
    }

    fun getDataFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            _allNotes.postValue(notesListUseCase.getData())
        }
    }

    fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            pressedNote.value?.let { notesListUseCase.deleteNote(it) }
        }
    }

    fun addNote(note: Note?) {
        viewModelScope.launch(Dispatchers.IO) {
            note?.let { notesListUseCase.addNote(it) }
        }
    }

    fun setDefaultNotes(context: Context) {
        viewModelScope.launch {
            notesListUseCase.fillWithStartingNotes(context)
        }
    }
}