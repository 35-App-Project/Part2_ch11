package com.choi.part2_ch11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.part2_ch11.databinding.FragmentOrderBinding

class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var binding : FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentOrderBinding.bind(view)

        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        val menuAdapter=MenuAdapter().apply {
            submitList(menuData.coffee)
        }


        binding.recyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=menuAdapter
        }


    }

}