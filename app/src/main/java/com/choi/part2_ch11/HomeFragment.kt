package com.choi.part2_ch11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.choi.part2_ch11.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentHomeBinding.bind(view)
    }
}