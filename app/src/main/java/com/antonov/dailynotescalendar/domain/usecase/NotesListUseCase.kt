package com.antonov.dailynotescalendar.domain.usecase

import android.content.Context
import android.util.Log
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import com.antonov.dailynotescalendar.domain.model.Note
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import javax.inject.Inject

class NotesListUseCase @Inject constructor(
    private val roomNotesRepository: RoomNotesRepository
) {
    private suspend fun fillWithStartingNotes(context: Context){
        try {
            val notes = loadJSONArray(context)
            if (notes != null){
                for (i in 0 until notes.length()){
                    val item = notes.getJSONObject(i)
                    val id = item.getInt("id")
                    val description = item.getString("description")

                    val note = Note(
                        id, description
                    )

                    roomNotesRepository.insert(note)
                }
            }
        }

        catch (e: JSONException) {
            Log.d("MyError", e.message.toString())
        }
    }
    private fun loadJSONArray(context: Context): JSONArray?{
        val inputStream = context.resources.openRawResource(R.raw.notes)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}