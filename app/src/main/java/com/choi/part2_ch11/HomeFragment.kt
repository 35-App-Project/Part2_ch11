package com.choi.part2_ch11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.choi.part2_ch11.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val homeData = context?.readData() ?: return

        with(binding) {
            appbarTitleTextView.text = getString(R.string.appbar_title_text, homeData.user.nickname)
            starCountTextView.text = getString(
                R.string.appbar_star_title, homeData.user.starCount,
                homeData.user.totalCount
            )

            appBarProgressBar.also {
                it.progress=homeData.user.starCount
                it.max=homeData.user.totalCount
            }

            Glide.with(binding.appbarImageView)
                .load(homeData.appbarImage)
                .into(binding.appbarImageView)

            // 커스텀 View 추가
            recommendMenuList.menuLayout.addView(
                MenuView(context=requireContext()).apply {
                    setTitle("카페 모카")
                    setImageView("https://picsum.photos/120/120")
                }
            )

        }
    }
}