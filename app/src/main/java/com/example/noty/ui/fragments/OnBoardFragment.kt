package com.example.noty.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noty.R
import com.example.noty.databinding.FragmentOnBoardBinding
import com.example.noty.databinding.FragmentViewPagerOnBoardBinding
import com.example.noty.ui.adapters.ViewPagerOnBoardAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager2()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.Viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.tvback.visibility = View.GONE
                    }

                    1 -> {
                        binding.tvSkip.visibility = View.VISIBLE
                        binding.btnStart.visibility = View.GONE
                        binding.tvback.visibility = View.VISIBLE
                        binding.btnNext.visibility = View.VISIBLE
                    }

                    2 -> {
                        binding.tvback.visibility = View.VISIBLE
                        binding.tvSkip.visibility = View.GONE
                        binding.btnNext.visibility = View.GONE
                        binding.btnStart.visibility = View.VISIBLE
                    }
                }
            }
        })

        binding.tvback.setOnClickListener {
            binding.Viewpager2.setCurrentItem(binding.Viewpager2.currentItem - 1, true)
        }
        binding.btnNext.setOnClickListener {
            binding.Viewpager2.setCurrentItem(binding.Viewpager2.currentItem + 1, true)
        }
        binding.tvSkip.setOnClickListener {
            binding.Viewpager2.setCurrentItem(2, true)
        }
        binding.btnStart.setOnClickListener {
        }
    }

    private fun initViewPager2() {
        binding.Viewpager2.adapter = ViewPagerOnBoardAdapter(this)
        binding.dots.attachTo(binding.Viewpager2)
    }
}