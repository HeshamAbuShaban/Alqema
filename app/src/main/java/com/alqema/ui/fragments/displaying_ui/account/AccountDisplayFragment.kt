package com.alqema.ui.fragments.displaying_ui.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.alqema.adapters.listeners.AccountContract
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.listeners.OnItemExpandClickListener
import com.alqema.adapters.recycler_view.account.AccountAdapter
import com.alqema.database.local_db.models.Account
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentAccountDisplayBinding
import com.alqema.ui.fragments.dialogs.display.ExpandedAccountFragment
import com.alqema.utils.GeneralUtils

class AccountDisplayFragment : Fragment(), OnItemClickListener<AccountContract>,
    OnItemExpandClickListener<Account> {
    private lateinit var binding: FragmentAccountDisplayBinding

    private lateinit var viewModel: AccountDisplayViewModel
    private lateinit var dbViewModel: DatabaseViewModel

    /*private var searchQuery: String? = null*/


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
        setupListeners()
        displayAccounts()
    }

    private fun setupListeners() {
        binding.accountsSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                /*searchQuery =*/  /*readSearchBarText()*/
                dbViewModel.getAllAccount(text.toString()).observe(viewLifecycleOwner) {
                    setupAccountAdapter(it)
                }
            }
        })
    }

    /*private fun readSearchBarText(): String {
        return binding.accountsSearchBar.text.toString()
    }*/

    private fun displayAccounts() {
        // first call the data
        dbViewModel.allAccount.observe(viewLifecycleOwner) {
            setupAccountAdapter(it)
        }
    }

    private fun setupAccountAdapter(accountList: List<Account>) {
        with(binding.accountsRecyclerView) {
            setHasFixedSize(true)
            adapter = AccountAdapter(accountList).apply {
                registerOnItemClickListener(this@AccountDisplayFragment)
                registerOnExpandItemClickListener(this@AccountDisplayFragment)
            }
        }
    }

    // this to handle the click on an item to make an update on it.
    override fun onClick(obj: AccountContract) {
        val navCon = binding.root.findNavController()
        val action =
            AccountDisplayFragmentDirections.actionNavigationAccountDisplayToNavigationAddAccount(
                obj.accountNumber
            )
        navCon.navigate(action)
        GeneralUtils.getInstance().showSnackBar(binding.root, obj.accountName)
    }

    override fun onExpandClick(obj: Account) {
        val dialog = ExpandedAccountFragment(obj)
        dialog.show(childFragmentManager, "Expand Account Details Event")
    }

}