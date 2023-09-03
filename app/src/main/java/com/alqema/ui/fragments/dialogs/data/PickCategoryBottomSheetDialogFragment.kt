package com.alqema.ui.fragments.dialogs.data

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.app_system.node.UseDatabase
import com.alqema.database.local_db.models.Category
import com.alqema.database.repo.AlqemaRepository
import com.alqema.databinding.FragmentPickCategoryBottomSheetDialogBinding
import com.alqema.utils.GeneralUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PickCategoryBottomSheetDialogFragment : BottomSheetDialogFragment(),
    OnItemClickListener<Category> {

    private var onDataClickListener: OnDataClickListener? = null
    private lateinit var binding: FragmentPickCategoryBottomSheetDialogBinding
    private val repo: AlqemaRepository =  UseDatabase.getInstance().repository


    //  No Need To Use if the screen already inherent the interface and the OnAttach do the rest job.. @Deprecated()
    /*fun registerOnDataClickListener(onDataClickListener: OnDataClickListener) {
        this.onDataClickListener = onDataClickListener
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onDataClickListener = parentFragment as OnDataClickListener?
            Log.d("OnClickListener", "onAttach: Done for :$onDataClickListener")/*
             *  Must BE "*childFragmentManager*"
             *  ImagePickerDialogFragment().show(childFragmentManager, "ImagePicking")
             *
             */
        } catch (exception: ClassCastException) {
            throw ClassCastException(
                parentFragment.toString() + " must implement OnClickListener, exception: " + exception
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPickCategoryBottomSheetDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        this.isCancelable = false
        setupListeners()
        fetchData()
    }

    private fun setupListeners() {
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun fetchData() {

        var query: String  // TODO:Check This and Try it null.

        binding.categoriesSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                query = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                query = text.toString()
                lifecycleScope.launch {
                    val categoryList = withContext(Dispatchers.IO) {
                        repo.getCategories(query)
                    }
                    withContext(Dispatchers.Main) {
                        setupCategoriesRecycler(ArrayList(categoryList))
                    }
                }
            }
        })

        lifecycleScope.launch {
            val categoryList = withContext(Dispatchers.IO) { repo.getCategories() }
            withContext(Dispatchers.Main) {
                setupCategoriesRecycler(ArrayList(categoryList))
            }
        }

        /*lifecycleScope.launch {
            val categoryList = withContext(Dispatchers.IO) { repo.getCategories() }
            withContext(Dispatchers.Main) {
                setupCategoriesRecycler(ArrayList(categoryList))
            }
        }*/
    }


    // apply data from database to Dialog recycler
    private fun setupCategoriesRecycler(categoryList: ArrayList<Category>) {
        with(binding.categoriesRecyclerDialogData) {
            adapter = CategoryAdapter(categoryList).apply {
                // INIT FOR
                registerOnItemClickListener(this@PickCategoryBottomSheetDialogFragment)
            }
            startAnimation(GeneralUtils.getInstance().getAppRecyclerAnimation(requireContext()))
            setHasFixedSize(true)
        }
    }


    // SingleClick not Like the original adapter that activated with a long click
    // From Receive Adapter Clicks OnItemClickListener<Category>
    override fun onClick(obj: Category) {
        // from Adapter( OnItemClickListener<Category>) clicks .. to dialog(OnDataClickListener) click to screen usage.
        onDataClickListener?.onItemClicked(category = obj)
    }

    override fun onDetach() {
        super.onDetach()
        onDataClickListener = null
        binding.categoriesRecyclerDialogData.clearAnimation()
    }

    //.. ****for this class communication with outSide****outSide*******outSide****outSide
    interface OnDataClickListener {
        fun onItemClicked(category: Category)

    }
}

