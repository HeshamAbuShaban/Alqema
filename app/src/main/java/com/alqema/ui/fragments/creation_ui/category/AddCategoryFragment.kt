package com.alqema.ui.fragments.creation_ui.category

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
import com.alqema.databinding.FragmentAddCategoryBinding
import com.alqema.models.constants.state.EditingState
import com.alqema.ui.fragments.dialogs.alert.DeleteItemDialogFragment
import com.alqema.utils.GeneralUtils

class AddCategoryFragment : Fragment() {
    private lateinit var binding: FragmentAddCategoryBinding
    private lateinit var viewModel: CategoryViewModel
    private var updateState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddCategoryBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddCategoryFragmentArgs by navArgs()
        Log.d("AddCategoryFragment", "onViewCreated() returned: ${args.categoryItemId}")
        val editingState =
            if (args.categoryItemId > 0) EditingState.EXISTING_ITEM else EditingState.NEW_ITEM

        if (editingState == EditingState.EXISTING_ITEM) {
            updateState = true

            val dbViewModel: DatabaseViewModel =
                ViewModelProvider(this)[DatabaseViewModel::class.java]

            dbViewModel.getCategory(args.categoryItemId).observe(viewLifecycleOwner) {
                Log.i("AddCategoryFragment", "onViewCreated: QueryTrigger")
                it?.let { category ->
                    Log.d(
                        "AddCategoryFragment",
                        "onViewCreated() returned: ${category.categoryName}"
                    )
                        viewModel.setUIData(binding, category) {
                            binding.btnDeleteCategory.visibility = View.VISIBLE
                            binding.title.text = getString(R.string.editing_category)

                            binding.btnDeleteCategory.setOnClickListener {
                                val dialog = DeleteItemDialogFragment().apply {
                                    registerLogoutDialogListener {
                                        viewModel.preformDelete(args.categoryItemId){
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
                    if (updateState) {
                        GeneralUtils.getInstance()
                            .showSnackBar(binding.root, "Updated Data...")
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}