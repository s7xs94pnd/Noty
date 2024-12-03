package com.example.noty.ui.fragments.noty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noty.App
import com.example.noty.R
import com.example.noty.databinding.FragmentHomeNotyBinding
import com.example.noty.databinding.FragmentOnBoardBinding
import com.example.noty.ui.adapters.RvNotyAdapter

class HomeNotyFragment : Fragment() {

    private lateinit var binding: FragmentHomeNotyBinding
    private val rvNotyAdapter = RvNotyAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeNotyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        setUpListeners()
        getData()
    }


    private fun getData() {
        App.appDataBaseRoom?.notyDao()?.getAll()?.observe(viewLifecycleOwner){itemsAndData->
            rvNotyAdapter.submitList(itemsAndData)
        }
    }

    private fun setUpListeners() {
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeNotyFragment_to_detailNotyFragment)
            // TODO: animation
        }
    }

    private fun initRv() {
        binding.rvNoty.adapter = rvNotyAdapter
        binding.rvNoty.layoutManager = LinearLayoutManager(requireContext())
    }
}