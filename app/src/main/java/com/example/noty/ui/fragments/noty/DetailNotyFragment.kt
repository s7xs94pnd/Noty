package com.example.noty.ui.fragments.noty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noty.App
import com.example.noty.R
import com.example.noty.data.models.NotyModel
import com.example.noty.databinding.FragmentDetailNotyBinding
import com.example.noty.databinding.ItemNotyBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import java.util.zip.Inflater

class DetailNotyFragment : Fragment() {
    private lateinit var binding: FragmentDetailNotyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNotyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        startUpdatingTime()
    }

    private fun setUpListeners()= with(binding){
        val textWatcher =object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                inputsState()
            }

            private fun inputsState() {
                if (edDesc.text.isNotEmpty()&&edTitle.text.isNotEmpty()){
                    tvAddText.visibility = View.VISIBLE
                }else{
                    tvAddText.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        edTitle.addTextChangedListener(textWatcher)
        edDesc.addTextChangedListener(textWatcher)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        tvAddText.setOnClickListener{
           val title = edTitle.text.toString()
           val description = edDesc.text.toString()
           val time = getCurrentDateTime()
            App.appDataBaseRoom?.notyDao()?.insert(NotyModel(title,description,time))
            findNavController().navigateUp()
        }
    }

    // TODO: whata hell
    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("dd MMMM HH:mm ", Locale.getDefault())
        return dateFormat.format(System.currentTimeMillis())
    }

    private fun startUpdatingTime() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                // Update UI on Main thread
                activity?.runOnUiThread {
                    val currentTime = getCurrentDateTime()
                    // Update your TextView with the new time here (assuming you have a TextView)
                    binding.tvDateTime.text = currentTime
                }
            }
        }, 0, 60000) // Update every 60 seconds (1 minute)
    }
}