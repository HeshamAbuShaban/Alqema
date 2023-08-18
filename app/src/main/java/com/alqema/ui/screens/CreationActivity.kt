package com.alqema.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alqema.R
import com.alqema.adapters.view_pager.PagerAdapter
import com.alqema.databinding.ActivityCreationBinding
import com.alqema.ui.fragments.creation_ui.account.AddAccountFragment
import com.alqema.ui.fragments.creation_ui.category.AddCategoryFragment
import com.google.android.material.tabs.TabLayoutMediator

class CreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(AddAccountFragment())
        fragmentList.add(AddCategoryFragment())

        with(binding.viewPager2) {
            adapter = PagerAdapter(fragmentList, this@CreationActivity)
            currentItem = 0
        }

    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2
        ) { tab, position ->

            when (position) {
                0 -> tab.text = getString(R.string.accounts)
                1 -> tab.text = getString(R.string.categories)
                else -> tab.text = "GoodShow"
            }

        }.attach()
    }
}