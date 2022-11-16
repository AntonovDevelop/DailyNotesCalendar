package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.databinding.FragmentCalendarBinding
import com.antonov.dailynotescalendar.domain.model.Hour
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.presentation.MainViewModel
import com.antonov.dailynotescalendar.presentation.adapter.OnItemClickListener
import com.antonov.dailynotescalendar.presentation.adapter.RecyclerItemAdapter
import java.util.*

class CalendarFragment : Fragment(), OnItemClickListener {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater)

        initUI()
        addObservers()

        return binding.root
    }

    private fun addObservers() {
        //обновляем адаптер, если появляются новые заметки
        viewModel.allNotes.observe(viewLifecycleOwner) {
            viewModel.setHours(Date(binding.calendarView.date))
            binding.recycler.adapter =
                viewModel.allHours.value?.let { RecyclerItemAdapter(it, R.layout.list_item, this) }
        }
        //или обновляется значение списка часов
        viewModel.allHours.observe(viewLifecycleOwner) {
            binding.recycler.adapter = RecyclerItemAdapter(it, R.layout.list_item, this)
        }
    }

    private fun initUI() {
        //получение данных
        viewModel.getDataFromRoom()

        binding.recycler.layoutManager = LinearLayoutManager(context)

        //устанавливаем таблицу с часами
        viewModel.setHours(Date(binding.calendarView.date))

        //создаем адаптер для часов
        binding.recycler.adapter =
            viewModel.allHours.value?.let { RecyclerItemAdapter(it, R.layout.list_item, this) }

        //кнопка Добавить
        binding.fab.setOnClickListener{
            viewModel.setPressedNote(null)
            findNavController(binding.recycler).navigate(R.id.action_calendarFragment_to_editNoteFragment)
        }

        //при смене даты обновляем таблицу часов
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            run {
                viewModel.setHours(Date(binding.calendarView.date))
            }
        }
    }

    override fun onItemClick(hour: Hour?, position: Int) {
        hour?.note?.let {
            viewModel.setPressedNote(it)
            findNavController(binding.recycler).navigate(R.id.action_calendarFragment_to_editNoteFragment)
        }
    }
}