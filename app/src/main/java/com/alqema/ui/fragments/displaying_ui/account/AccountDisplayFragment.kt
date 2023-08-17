package com.alqema.ui.fragments.displaying_ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alqema.adapters.recycler_view.account.AccountAdapter
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentAccountDisplayBinding
import com.alqema.models.Account

class AccountDisplayFragment : Fragment() {
    private lateinit var binding: FragmentAccountDisplayBinding

    private lateinit var viewModel: AccountDisplayViewModel
    private lateinit var dbViewModel: DatabaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountDisplayViewModel::class.java]
        dbViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayAccounts()
    }

    private fun displayAccounts() {
        // first call the data
        dbViewModel.allAccount.observe(viewLifecycleOwner){
            setupAccountAdapter(it)
        }
       /* // than read it
        viewModel.accountList.observe(viewLifecycleOwner) {
            if (it != null) {
                setupAccountAdapter(it)
            }
        }*/
    }

    private fun setupAccountAdapter(accountList: List<Account>) {
        with(binding.accountsRecyclerView) {
            setHasFixedSize(true)
            adapter = AccountAdapter(accountList)
        }
    }

}