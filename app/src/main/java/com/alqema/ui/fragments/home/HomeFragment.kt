package com.alqema.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.alqema.R
import com.alqema.adapters.view_pager.PagerAdapter
import com.alqema.databinding.FragmentHomeBinding
import com.alqema.ui.fragments.dialogs.choose.CreationBottomSheetDialogFragment
import com.alqema.ui.fragments.dialogs.choose.DisplayBottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), CreationBottomSheetDialogFragment.OnCreationClickListener,
    DisplayBottomSheetDialogFragment.OnDisplayClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabViewPagerUI()
        setupListeners()
    }

    private fun setupListeners() {
        /*with(binding) {
            creationIBtn.setOnClickListener {
                CreationBottomSheetDialogFragment().show(
                    childFragmentManager,
                    "Create_Event_Clicked"
                )
            }
            displayingIBtn.setOnClickListener {
                DisplayBottomSheetDialogFragment().show(
                    childFragmentManager,
                    "Display_Event_Clicked"
                )
            }
        }*/
    }


    private fun initTabViewPagerUI() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(CreationBottomSheetDialogFragment())
        fragmentList.add(DisplayBottomSheetDialogFragment())

        with(binding.viewPager2) {
            adapter = PagerAdapter(fragmentList, this@HomeFragment)
            /*currentItem = 0*/
        }

    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager2
        ) { tab, position ->

            when (position) {
                0 -> tab.text = getString(R.string.creation)
                1 -> tab.text = getString(R.string.query_s)
            }

        }.attach()
    }


    override fun onCreateClicked(view: View) {
        when (view.id) {
            R.id.btnCreateAccounts -> navController.navigate(R.id.action_navigation_home_to_navigation_addAccount)
            R.id.btnCreateCategory -> navController.navigate(R.id.action_navigation_home_to_navigation_addCategory)
            R.id.btnCreateReceipts -> navController.navigate(R.id.action_navigation_home_to_navigation_addReceipt)
            R.id.btnCreatePayments -> navController.navigate(R.id.action_navigation_home_to_navigation_addPayment)
        }
    }

    override fun onDisplayClicked(view: View) {
        when (view.id) {
            R.id.btnDisplayAccounts -> navController.navigate(R.id.action_navigation_home_to_navigation_accountDisplay)
            R.id.btnDisplayCategory -> navController.navigate(R.id.action_navigation_home_to_navigation_categoryDisplay)
            R.id.btnDisplayReceipts -> navController.navigate(R.id.action_navigation_home_to_navigation_receiptDisplay)
            R.id.btnDisplayPayments -> navController.navigate(R.id.action_navigation_home_to_navigation_paymentDisplay)
        }
    }

}