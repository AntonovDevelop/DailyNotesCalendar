package com.antonov.dailynotescalendar.presentation.mainFragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.databinding.FragmentEditNoteBinding
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.presentation.MainViewModel
import java.util.*


class EditNoteFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentEditNoteBinding

    //Приходящая заметка
    private var note: Note? = null

    //
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mHour = 0
    private var mMinute = 0

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

    private fun initUI() {
        note = viewModel.pressedNote.value

        binding.buttonDateSelection.setOnClickListener {
            // Get Current Date
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(requireContext(),
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.editTextDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }
        binding.buttonTimeSelection.setOnClickListener {
            // Get Current Time
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(requireContext(),
                OnTimeSetListener { view, hourOfDay, minute -> binding.editTextTime.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false)
            timePickerDialog.show()
        }
        binding.buttonSave.setOnClickListener {
            if (note != null) {

            } else {
                val ds = GregorianCalendar(mYear, mMonth, mMonth, mHour, mMinute)
                val note = Note(
                    ds.time,
                    ds.time,
                    binding.editTextName.text.toString(),
                    binding.editTextDesc.text.toString()
                )
                viewModel.addNote(note)
            }
            Navigation.findNavController(binding.buttonSave).navigate(R.id.action_editNoteFragment_to_calendarFragment)
        }
        binding.buttonDelete.setOnClickListener {

        }
    }
}