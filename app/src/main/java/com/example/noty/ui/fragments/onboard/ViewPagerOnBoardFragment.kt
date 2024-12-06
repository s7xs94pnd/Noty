package com.example.noty.ui.fragments.onboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noty.R
import com.example.noty.databinding.FragmentViewPagerOnBoardBinding


class ViewPagerOnBoardFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerOnBoardBinding.inflate(inflater,container,false)
        return binding.root
        Log.d("fff", "ViewPagerOnBoardFragment: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChanges()
    }

    private fun initChanges() = with(binding) {
        when(requireArguments().getInt("onBoardPosition" ,0)){
            0->{
                title1.text ="Manage your \n notes easily"
                title2.text = "A completely easy way to manage and customize \n your notes."
                lottie.setAnimation(R.raw.anim_onboard_1)
                lottie.playAnimation()
            }
            1->{
                title1.text ="Organize your \n thougts"
                title2.text = "Most beautiful note taking application."
                lottie.setAnimation(R.raw.anim_onboard_2)
                lottie.playAnimation()
            }
            2->{
                title1.text ="Create cards and \n easy styling"
                title2.text = "Making your content legible has never \n been easier."
                lottie.setAnimation(R.raw.anim_onboard_3)
                lottie.playAnimation()
            }
        }
    }
}