package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonov.dailynotescalendar.R
import com.antonov.dailynotescalendar.databinding.FragmentCalendarBinding
import com.antonov.dailynotescalendar.databinding.FragmentEditNoteBinding
import com.antonov.dailynotescalendar.presentation.MainViewModel
import com.antonov.dailynotescalendar.presentation.adapter.RecyclerItemAdapter

class EditNoteFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentEditNoteBinding

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

    }
}