package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.antonov.dailynotescalendar.databinding.FragmentEditNoteBinding
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.presentation.MainViewModel
import java.sql.Timestamp
import java.util.*

class EditNoteFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentEditNoteBinding

    //Приходящая заметка
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(inflater)

        initUI()
        addObservers()

        return binding.root
    }

    private fun addObservers() {
    }

    //использую api level 21
    @Suppress("DEPRECATION")
    private fun initUI() {
        binding.datePickerStart.apply {
            setIs24HourView(true)
            if(note!=null){
                currentHour = note!!.date_start.hours
                currentMinute = note!!.date_start.minutes
            }
        }
        binding.datePickerFinish.apply {
            setIs24HourView(true)
            if(note!=null){
                currentHour = note!!.date_finish.hours
                currentMinute = note!!.date_finish.minutes
            }
        }
        binding.buttonSave.setOnClickListener {
            note!!.date_start.hours = binding.datePickerStart.currentHour

        }
        binding.buttonDelete.setOnClickListener {

        }
    }
}