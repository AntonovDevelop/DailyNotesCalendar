package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.antonov.dailynotescalendar.databinding.FragmentEditNoteBinding
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.presentation.MainViewModel

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
            currentHour = 12
            currentMinute = 0

        }
        binding.buttonSave.setOnClickListener {

        }
        binding.buttonDelete.setOnClickListener {

        }
    }
}