package com.alqema.ui.fragments.displaying_ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alqema.adapters.recycler_view.category.CategoryAdapter
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentCategoryDisplayBinding
import com.alqema.models.Category

class CategoryDisplayFragment : Fragment() {
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
        displayCategory()
    }

    private fun displayCategory() {
        dbViewModel.allCategory.observe(viewLifecycleOwner) {
            setupCategoryAdapter(it)
        }

        // first call the data
        /*viewModel.fetchData()
        // than read it
        viewModel.categoryList.observe(viewLifecycleOwner) {
            if (it != null) {
                setupCategoryAdapter(it)
            }
        }*/
    }

    private fun setupCategoryAdapter(categoryList: List<Category>) {
        with(binding.categoriesRecyclerView) {
            setHasFixedSize(true)
            adapter = CategoryAdapter(categoryList)
        }
    }

}