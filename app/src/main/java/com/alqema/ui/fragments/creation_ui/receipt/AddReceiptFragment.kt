package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alqema.R
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.FragmentAddReceiptBinding
import com.alqema.ui.fragments.dialogs.data.PickCategoryBottomSheetDialogFragment

class AddReceiptFragment : Fragment(), PickCategoryBottomSheetDialogFragment.OnDataClickListener {
    private lateinit var binding: FragmentAddReceiptBinding
    private lateinit var viewModel: ReceiptViewModel
    private var updateState = false
    private var categoriesList: ArrayList<Category> = ArrayList()
    private val categoryAdapter: CategoryAdapter = CategoryAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddReceiptBinding.bind(
            inflater.inflate(
                R.layout.fragment_add_receipt,
                container,
                false
            )
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupListeners()
        setupCategoriesRecycler()
    }

    private fun setupListeners() {
        with(binding) {
            btnAddCategoryItem.setOnClickListener {
                PickCategoryBottomSheetDialogFragment().show(
                    childFragmentManager,
                    "Add Category Event"
                )
            }
        }
    }

    // From Dialog
    override fun onItemClicked(category: Category) {
        // ToDo: Add the item to a global array that is passed to the Recycler of this fragment
        categoriesList.add(category)

        // TODO:Check These Out.....*************.....*************.....*************.....
        categoryAdapter.addUpCategoryList(categoriesList) // Check This or This :
//        categoryAdapter.addSingleCategory(category) // Check This or This :
    }

    private fun setupCategoriesRecycler() {
        with(binding.recReceiptCategories) {
            adapter = categoryAdapter
            setHasFixedSize(false)
        }
    }
}