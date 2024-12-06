package com.example.noty.ui.fragments.noty

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noty.App
import com.example.noty.R
import com.example.noty.data.models.NotyModel
import com.example.noty.databinding.FragmentHomeNotyBinding
import com.example.noty.databinding.FragmentOnBoardBinding
import com.example.noty.ui.OnItemClick
import com.example.noty.ui.adapters.RvNotyAdapter

class HomeNotyFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentHomeNotyBinding
    private val rvNotyAdapter = RvNotyAdapter(this, this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeNotyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initRv()
        setUpListeners()
    }


    private fun getData() {
        App.appDataBaseRoom?.notyDao()?.getAll()?.observe(viewLifecycleOwner) { itemsAndData ->
            rvNotyAdapter.submitList(itemsAndData)
        }
    }

    private fun setUpListeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeNotyFragment_to_detailNotyFragment)
            // TODO: animation
        }
    }

    private fun initRv() {
        binding.rvNoty.adapter = rvNotyAdapter
        binding.rvNoty.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onLongClick(notyModel: NotyModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("delete ${notyModel.title}\n${notyModel.description}  ${notyModel.time}")
            setPositiveButton("delete") { dialog, _ ->
                // TODO: что за dialog, _ ->?
                App.appDataBaseRoom!!.notyDao().delete(notyModel)
            }
            setNegativeButton("cancel") { dialog, _ ->
                dialog.cancel()
            }
            setNeutralButton("ignore") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(notyModel: NotyModel) {
        Log.d("ttt", "onClick: ${notyModel.id} ")
        val action =
            HomeNotyFragmentDirections.actionHomeNotyFragmentToDetailNotyFragment(notyModel.id)
        findNavController().navigate(action)
    }
}