package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.databinding.FragmentCalendarBinding
import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.presentation.MainViewModel
import com.antonov.dailynotescalendar.presentation.adapter.OnItemClickListener
import com.antonov.dailynotescalendar.presentation.adapter.OnLongItemClickListener
import com.antonov.dailynotescalendar.presentation.adapter.RecyclerItemAdapter

class CalendarFragment : Fragment(), OnItemClickListener, OnLongItemClickListener {
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
        viewModel.allItems.observe(viewLifecycleOwner) {
            binding.recycler.adapter?.notifyDataSetChanged()
        }
    }

    private fun initUI() {
        binding.recycler.layoutManager = LinearLayoutManager(context)
        viewModel.setDefaultItems()
        binding.recycler.adapter =
            viewModel.allItems.value?.let { RecyclerItemAdapter(it, R.layout.list_item, this) }
        binding.calendarView.setOnDateChangeListener{
                view, year, month, dayOfMonth -> {}
        }
    }

    override fun onItemClick(item: Note?, position: Int) {

    }

    override fun onLongItemClick(item: Note?, position: Int) {
        //findNavController(binding.recycler)
            //.navigate(R.id.action_navigation_favorite_to_navigation_details)
    }
}