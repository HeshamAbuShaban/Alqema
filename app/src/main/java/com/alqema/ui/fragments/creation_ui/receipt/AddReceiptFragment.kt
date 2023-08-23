package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alqema.R
import com.alqema.adapters.listeners.OnItemActionClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.FragmentAddReceiptBinding
import com.alqema.ui.fragments.dialogs.data.PickCategoryBottomSheetDialogFragment
import com.alqema.utils.TimeUpdateListener
import com.alqema.utils.TimeUpdater


class AddReceiptFragment : Fragment(), PickCategoryBottomSheetDialogFragment.OnDataClickListener,
    OnItemActionClickListener<Category>, TimeUpdateListener {
    private lateinit var binding: FragmentAddReceiptBinding

    private val viewModel: ReceiptViewModel by viewModels() // TODO:Check This.
    private var updateState = false

    private val categoriesList: ArrayList<Category> = ArrayList()
    private val categoryAdapter: CategoryAdapter = CategoryAdapter(ArrayList()).apply {
        setVisibility(true)
        registerOnItemActionClickListener(this@AddReceiptFragment)
    }
    private lateinit var timeUpdater: TimeUpdater

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
        setupTimer()
        setupListeners()
        setupCategoriesRecycler()
    }

    private fun setupTimer() {
        timeUpdater = TimeUpdater(this)
        timeUpdater.startUpdatingTime()
    }

    private fun setupListeners() {
        with(binding) {
            btnAddCategoryItem.setOnClickListener {
                PickCategoryBottomSheetDialogFragment().show(
                    childFragmentManager,
                    "Add Category Event"
                )
            }

            //DONE.. toAdd removeBtn from the list action (within recycler for this screen)

            confirmButton.setOnClickListener {
//                viewModel.performCreation()
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

    /// TODO:Remove From the global list the item that arrive from this method
    /// TODO:Remove AND update the recycler.
    override fun onDelete(obj: Category) {
        categoriesList.remove(obj)
        categoryAdapter.removeFromCategoryList(categoriesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timeUpdater.stopUpdatingTime()
    }

    override fun onTimeUpdated(time: String) {
        binding.receiptDateInformTitle.text = time
    }
}