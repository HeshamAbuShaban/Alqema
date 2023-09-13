package com.alqema.ui.fragments.displaying_ui.category

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alqema.adapters.listeners.CategoryContract
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.listeners.OnItemExpandClickListener
import com.alqema.adapters.recycler_view.category.CategoryAdapter
import com.alqema.database.local_db.models.Category
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentCategoryDisplayBinding
import com.alqema.ui.fragments.dialogs.display.ExpandedCategoryFragment
import com.alqema.utils.GeneralUtils

class CategoryDisplayFragment : Fragment(),
    OnItemClickListener<CategoryContract>,
    OnItemExpandClickListener<Category> {
    private lateinit var binding: FragmentCategoryDisplayBinding

    private lateinit var viewModel: CategoryDisplayViewModel

    private lateinit var dbViewModel: DatabaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoryDisplayViewModel::class.java]
        dbViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoryDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        displayCategory()
    }

    private fun setupListeners() {
        binding.categoriesSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                /*searchQuery =*/  /*readSearchBarText()*/
                dbViewModel.getAllCategories(text.toString()).observe(viewLifecycleOwner) {
                    setupCategoryAdapter(it)
                }
            }
        })
    }

    private fun displayCategory() {
        dbViewModel.allCategory.observe(viewLifecycleOwner) {
            setupCategoryAdapter(it)
        }
    }

    private fun setupCategoryAdapter(categoryList: List<Category>) {
        with(binding.categoriesRecyclerView) {
            setHasFixedSize(true)
            adapter = CategoryAdapter(categoryList).apply {
                registerOnItemClickListener(this@CategoryDisplayFragment)
                registerOnExpandItemClickListener(this@CategoryDisplayFragment)
            }
        }
    }

    override fun onClick(obj: CategoryContract) {
        val navCon = findNavController()
        val action =
            CategoryDisplayFragmentDirections.actionNavigationCategoryDisplayToNavigationAddCategory(
                obj.categoryNumber
            )
        navCon.navigate(action)
        GeneralUtils.getInstance().showSnackBar(binding.root, obj.categoryName)
    }

    override fun onExpandClick(obj: Category) {
        val dialog = ExpandedCategoryFragment(obj)
        dialog.show(childFragmentManager, "Expand Category Details Event")
    }

}