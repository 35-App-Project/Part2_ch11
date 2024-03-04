package com.choi.part2_ch11

import android.animation.ValueAnimator
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

        val homeData = context?.readData("home.json", Home::class.java) ?: return
        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        initBar(homeData)
        initRecommend(homeData, menuData)
        initBanner(homeData)
        initFood(menuData)
        initFloatingActionButton()

    }

    private fun initBar(homeData: Home) {
        with(binding) {
            appbarTitleTextView.text = getString(R.string.appbar_title_text, homeData.user.nickname)
            starCountTextView.text = getString(
                R.string.appbar_star_title, homeData.user.starCount,
                homeData.user.totalCount
            )

            appBarProgressBar.max = homeData.user.totalCount


            Glide.with(appbarImageView)
                .load(homeData.appbarImage)
                .into(binding.appbarImageView)
        }

        ValueAnimator.ofInt(0, homeData.user.starCount).apply {
            duration = 1000
            addUpdateListener {
                binding.appBarProgressBar.progress = it.animatedValue as Int
            }
            start()
        }
    }

    private fun initRecommend(homeData: Home, menuData: Menu) {
        with(binding) {
            menuData.coffee.forEach { menuItem ->
                recommendMenuList.menuLayout.addView(
                    MenuView(context = requireContext()).apply {
                        setTitle(menuItem.name)
                        setImageView(menuItem.image)
                    }
                )
            }

            recommendMenuList.titleTextView.text =
                getString(R.string.recommend_title, homeData.user.nickname)
        }
    }

    private fun initBanner(homeData: Home) {
        with(binding) {
            bannerLayout.bannerImageView.apply {
                Glide.with(this)
                    .load(homeData.banner.image)
                    .into(this)
                this.contentDescription = homeData.banner.contentDescription
            }
        }
    }

    private fun initFood(menuData: Menu) {
        with(binding) {
            menuData.food.forEach { menuItem ->
                foodMenuList.menuLayout.addView(
                    MenuView(context = requireContext()).apply {
                        setTitle(menuItem.name)
                        setImageView(menuItem.image)
                    }
                )
            }

            foodMenuList.titleTextView.text = getString(R.string.food_title)
        }
    }

    private fun initFloatingActionButton() {
        binding.scrollView.setOnScrollChangeListener { view, _, scrollY, _, oldScrollY ->
            if (scrollY == 0) binding.floatingActionButton.extend()
            else binding.floatingActionButton.shrink()
        }
    }
}