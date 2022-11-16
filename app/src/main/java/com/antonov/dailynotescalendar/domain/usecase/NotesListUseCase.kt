package com.antonov.dailynotescalendar.domain.usecase

import android.content.Context
import android.util.Log
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import com.antonov.dailynotescalendar.domain.model.Note
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.util.*
import javax.inject.Inject

class NotesListUseCase @Inject constructor(
    private val roomNotesRepository: RoomNotesRepository
) {
    suspend fun getData(): List<Note>{
        return roomNotesRepository.getAllItems()
    }
    suspend fun addNote(note: Note){
        roomNotesRepository.insert(note)
    }
    suspend fun deleteNote(note: Note){
        roomNotesRepository.deleteItem(note)
    }
    ///мы можем заполнить из файла json наши заметки
    ///но в программе не использую
    suspend fun fillWithStartingNotes(context: Context){
        var notes: JSONArray? = null
        try {
            notes = loadJSONArray(context)
        }
        catch (e: JSONException) {
            Log.d("MyError", e.message.toString())
        }
        if (notes != null){
            for (i in 0 until notes.length()){
                val item = notes.getJSONObject(i)
                val id = item.getInt("id")
                val date_start = item.getLong("date_start")
                val date_finish = item.getLong("date_finish")
                val name = item.getString("name")
                val description = item.getString("description")

                val note = Note(
                    id, Date(date_start), Date(date_finish), name, description
                )
                roomNotesRepository.insert(note)
            }
        }
    }
    private fun loadJSONArray(context: Context): JSONArray?{
        val inputStream = context.resources.openRawResource(R.raw.notes)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
    ///
}