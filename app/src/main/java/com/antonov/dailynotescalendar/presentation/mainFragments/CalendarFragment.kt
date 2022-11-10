package com.antonov.dailynotescalendar.presentation.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.antonov.dailynotescalendar.databinding.FragmentCalendarBinding
import com.antonov.dailynotescalendar.domain.model.Item
import com.antonov.dailynotescalendar.presentation.MainViewModel
import com.antonov.dailynotescalendar.presentation.adapter.RecyclerItemAdapter

class CalendarFragment : Fragment(), RecyclerItemAdapter.OnItemClickListener, RecyclerItemAdapter.OnLongItemClickListener{
    private var viewModel: MainViewModel? = null
    private var binding: FragmentCalendarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        initUI()
        addObservers()

        return binding!!.root
    }

    private fun addObservers() {
        viewModel?.listItems?.observe(viewLifecycleOwner) { l ->
            binding!!.recycler.adapter =
                viewModel?.listItems?.value?.let {
                    RecyclerItemAdapter(this, this,
                        context, it)
                }
        }
    }

    private fun initUI() {

    }

    override fun onItemClick(item: Item?, position: Int) {
        item!!.isChecked = !item.isChecked
        binding!!.recycler.adapter!!.notifyDataSetChanged()
    }

    override fun onLongItemClick(item: Item?, position: Int) {
        viewModel?.longPressedItem?.value = item
        //findNavController(binding.buttonAdd).navigate(R.id.action_productFragment_to_editProductFragment)
    }
}