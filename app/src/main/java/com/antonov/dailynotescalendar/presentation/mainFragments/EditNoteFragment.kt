package com.antonov.dailynotescalendar.presentation.mainFragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var dateAndTime = Calendar.getInstance()

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

        if (note != null) {
            dateAndTime.time= note!!.date_start

            binding.editTextDate.setText(dateAndTime.get(Calendar.YEAR).toString() + "-" + dateAndTime.get(Calendar.MONTH) + "-" + dateAndTime.get(Calendar.DAY_OF_MONTH))
            binding.editTextTime.setText("${dateAndTime.get(Calendar.HOUR_OF_DAY)}:${dateAndTime.get(Calendar.MINUTE)}")
            binding.editTextName.setText(note!!.name)
            binding.editTextDesc.setText(note!!.description)
        }

        binding.buttonDateSelection.setOnClickListener {
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(requireContext(),
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.editTextDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateAndTime.set(Calendar.YEAR, year)
                    dateAndTime.set(Calendar.MONTH, mMonth)
                    dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }
        binding.buttonTimeSelection.setOnClickListener {
            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(requireContext(),
                OnTimeSetListener { view, hourOfDay, minute ->
                    binding.editTextTime.setText("$hourOfDay:$minute")
                    dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    dateAndTime.set(Calendar.MINUTE, minute)
                },
                mHour,
                mMinute,
                true)
            timePickerDialog.show()
        }
        binding.buttonSave.setOnClickListener {
            if (binding.editTextName.text.toString().isNullOrEmpty()
                || binding.editTextDesc.text.toString().isNullOrEmpty()
                || binding.editTextDate.text.toString().isNullOrEmpty()
                || binding.editTextTime.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(requireActivity(), "Заполните все поля", Toast.LENGTH_LONG)
                return@setOnClickListener
            }
            if (note != null) {
                Log.d("MyDebug",
                    "old mYear, mMonth, mDay, mHour, mMinute: " +
                            "${dateAndTime.get(Calendar.YEAR)} " +
                            "${dateAndTime.get(Calendar.MONTH)} " +
                            "${dateAndTime.get(Calendar.DAY_OF_MONTH)} " +
                            "${dateAndTime.get(Calendar.HOUR_OF_DAY)} " +
                            "${dateAndTime.get(Calendar.MINUTE)}")
                //update note
                note?.apply {
                    date_start = dateAndTime.time
                    date_finish = dateAndTime.time
                    name = binding.editTextName.text.toString()
                    description = binding.editTextDesc.text.toString()
                }
                viewModel.addNote(note)
            } else {
                Log.d("MyDebug",
                    "new mYear, mMonth, mDay, mHour, mMinute: " +
                            "${dateAndTime.get(Calendar.YEAR)} " +
                            "${dateAndTime.get(Calendar.MONTH)} " +
                            "${dateAndTime.get(Calendar.DAY_OF_MONTH)} " +
                            "${dateAndTime.get(Calendar.HOUR_OF_DAY)} " +
                            "${dateAndTime.get(Calendar.MINUTE)}")
                val note = Note(
                    dateAndTime.time,
                    dateAndTime.time,
                    binding.editTextName.text.toString(),
                    binding.editTextDesc.text.toString()
                )
                viewModel.addNote(note)
            }
            Navigation.findNavController(binding.buttonSave)
                .navigate(R.id.action_editNoteFragment_to_calendarFragment)
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.deleteNote()
            Navigation.findNavController(binding.buttonDelete)
                .navigate(R.id.action_editNoteFragment_to_calendarFragment)
            Toast.makeText(requireActivity(), "Удалено", Toast.LENGTH_LONG)
        }
    }
}