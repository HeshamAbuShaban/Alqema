package com.alqema.ui.fragments.creation_ui.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alqema.R
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentAddAccountBinding
import com.alqema.models.constants.state.EditingState
import com.alqema.ui.fragments.dialogs.alert.DeleteItemDialogFragment
import com.alqema.utils.GeneralUtils

class AddAccountFragment : Fragment() {
    private lateinit var binding: FragmentAddAccountBinding
    private lateinit var viewModel: AccountViewModel
    private var updateState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddAccountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddAccountFragmentArgs by navArgs()
        Log.d("AddAccountFragment", "onViewCreated() returned: ${args.accountItemId}")
        val editingState =
            if (args.accountItemId > 0) EditingState.EXISTING_ITEM else EditingState.NEW_ITEM

        if (editingState == EditingState.EXISTING_ITEM) {
            updateState = true
            val dbViewModel: DatabaseViewModel =
                ViewModelProvider(this)[DatabaseViewModel::class.java]

            dbViewModel.getAccount(args.accountItemId).observe(viewLifecycleOwner) {
                Log.i("AddAccountFragment", "onViewCreated: QueryTrigger")
                it?.let { account ->
                    Log.d("AddAccountFragment", "onViewCreated() returned: ${account.accountName}")
                    viewModel.setUIData(binding, account){
                        with(binding){
                            btnDeleteAccount.visibility = View.VISIBLE
                            title.text = getString(R.string.editing_account)
                            btnDeleteAccount.setOnClickListener {
                                val dialog = DeleteItemDialogFragment().apply {
                                    registerLogoutDialogListener {
                                        viewModel.preformDelete(args.accountItemId){
                                            if (updateState){
                                                GeneralUtils.getInstance()
                                                    .showSnackBar(binding.root, "Data Got Deleted...")
                                                findNavController().popBackStack()
                                            }
                                        }
                                    }
                                }
                                dialog.show(childFragmentManager, "Deleting_Item_Event")
                            }
                        }
                    }
                }
            }
        }

        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            confirmButton.setOnClickListener {

                viewModel.performCreation(binding, {
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, getString(R.string.empty_fields_message))
                }) {
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, getString(R.string.created_message))
                    if (updateState){
                        GeneralUtils.getInstance()
                            .showSnackBar(binding.root, "Updated Data...")
                        findNavController().popBackStack()
                    }
                }

            }
        }
    }

}