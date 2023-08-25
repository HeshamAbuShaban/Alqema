package com.alqema.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.alqema.R
import com.alqema.databinding.FragmentHomeBinding
import com.alqema.ui.fragments.dialogs.choose.CreationBottomSheetDialogFragment
import com.alqema.ui.fragments.dialogs.choose.DisplayBottomSheetDialogFragment

class HomeFragment : Fragment(), CreationBottomSheetDialogFragment.OnCreationClickListener,
    DisplayBottomSheetDialogFragment.OnDisplayClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
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
        }
    }


    override fun onCreateClicked(view: View) {
        when (view.id) {
            R.id.btnCreateAccounts -> navController.navigate(R.id.action_navigation_home_to_navigation_addAccount)
            R.id.btnCreateCategory -> navController.navigate(R.id.action_navigation_home_to_navigation_addCategory)
            R.id.btnCreateReceipts -> navController.navigate(R.id.action_navigation_home_to_navigation_addReceipt)
        }
    }

    override fun onDisplayClicked(view: View) {
        when (view.id) {
            R.id.btnDisplayAccounts -> navController.navigate(R.id.action_navigation_home_to_navigation_accountDisplay)
            R.id.btnDisplayCategory -> navController.navigate(R.id.action_navigation_home_to_navigation_categoryDisplay)
            R.id.btnDisplayReceipts -> navController.navigate(R.id.action_navigation_home_to_navigation_receiptDisplay)
        }
    }

}