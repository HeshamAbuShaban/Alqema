package com.alqema.ui.fragments.dialogs.data

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.app_system.AppController
import com.alqema.database.local_db.models.Category
import com.alqema.database.repo.AlqemaRepository
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentPickCategoryBottomSheetDialogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PickCategoryBottomSheetDialogFragment : DialogFragment(), OnItemClickListener<Category> {

    private var onDataClickListener: OnDataClickListener? = null

    private lateinit var binding: FragmentPickCategoryBottomSheetDialogBinding

    private val dbViewModel: DatabaseViewModel by viewModels()
    private val repo: AlqemaRepository = AlqemaRepository(AppController.getInstance())


    //  No Use @Deprecated()
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
//        this.isCancelable = false
        init()
    }

    private fun init() {
        fetchData()
    }

    /*private fun setupListeners() {

    }*/

    private fun fetchData() {

//        GlobalScope.launch {
//            println("Starting...")
//            try {
//                println("StartingTry...")
//
////                Toast.makeText(
////                    AppController.getInstance(),
////                    "Starting...",
////                    Toast.LENGTH_LONG
////                ).show()
//                val categoryList = repo.getCategories()
//                println("Aftre...")
//
////                Toast.makeText(
////                    AppController.getInstance(),
////                    "After...",
////                    Toast.LENGTH_LONG
////                ).show()
//                withContext(Dispatchers.Main) {
//                    println("Main...")
//
////                    Toast.makeText(
////                        AppController.getInstance(),
////                        "Main...",
////                        Toast.LENGTH_LONG
////                    ).show()
//
//                    if (categoryList.isNullOrEmpty()) {
//                        // Handle the case where categoryList is empty or null
//
//                        println("List Is NullOrEmpty")
////                        Toast.makeText(
////                            AppController.getInstance(),
////                            "List Is NullOrEmpty",
////                            Toast.LENGTH_LONG
////                        ).show()
//                    } else {
//                        println("List Is GOOD")
//                        /*Toast.makeText(
//                            AppController.getInstance(),
//                            "List Is GOOD",
//                            Toast.LENGTH_LONG
//                        ).show()*/
//
//                        setupCategoriesRecycler(ArrayList(categoryList))
//                    }
//                }
//            } catch (e: Exception) {
//                println("Catch")
//                /*
//                Toast.makeText(
//                    AppController.getInstance(),
//                    "Catch...",
//                    Toast.LENGTH_LONG
//                ).show()*/
//                // Log the exception and handle it
//                Log.e("CoroutineError", "Exception while fetching data: ${e.message}")
//            }
//        }


        /*lifecycleScope.launch {
            try {
                val categoryList = withContext(Dispatchers.IO){
                    repo.getCategories()
                }
                withContext(Dispatchers.Main){
                    if (categoryList.isNullOrEmpty()) {
                        // Handle the case where categoryList is empty or null
                        Toast.makeText(
                            AppController.getInstance(),
                            "List Is NullOrEmpty",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        setupCategoriesRecycler(ArrayList(categoryList))
                    }
                }
            } catch (e: Exception) {
                // Log the exception and handle it
                Log.e("CoroutineError", "Exception while fetching data: ${e.message}")
            }
        }*/

//        dbViewModel.getAllCategory().observe(viewLifecycleOwner) { categoryList ->
//            try {
//                if (categoryList.isNullOrEmpty()) {
//                    // Handle the case where categoryList is empty or null
//                } else {
//                    setupCategoriesRecycler(ArrayList(categoryList))
//                }
//            } catch (e: Exception) {
//                // Log the exception and handle it
//                Log.e("LiveDataError", "Exception while computing database live data: ${e.message}")
//            }
//        }

//        viewModelScope.launch {
//            try {
//                val categories = repo.observeCategories().value
//                if (categories.isNullOrEmpty()) {
//                    // Handle the case where categories are empty or null
//                } else {
//                    // Process the categories
//                }
//            } catch (e: Exception) {
//                Log.e("DatabaseViewModel", "Error fetching data: ${e.message}")
//            }
//        }
//

        /*lifecycleScope.launch {
            try {
                val categoryList = dbViewModel.getAllCategory().value
                if (categoryList == null) {
                    Log.e("Database", "Category list is null")
                } else {
                    // Process the categoryList
                }
            } catch (e: Exception) {
                Log.e("Database", "Error fetching data: ${e.message}", e)
            }
        }*/


        /*lifecycleScope.launch {
            try {
                // Fetch data from the repository
                val categoryList1 = repo.observeCategories().value

                if (categoryList1 == null) {
                    Toast.makeText(
                        AppController.getInstance(),
                        "List Is Null",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        AppController.getInstance(),
                        "List Is NOT Null",
                        Toast.LENGTH_LONG
                    ).show()
                }

                // Observe ViewModel LiveData
                dbViewModel.allCategory.observe(viewLifecycleOwner) { categoryList ->
                    categoryList?.let {
                        setupCategoriesRecycler(ArrayList(it))
                    }
                }
            } catch (e: Exception) {
                // Handle exception here
                Log.e("FetchData", "Error fetching data: ${e.message}")
            }
        }*/

        /*
            lifecycleScope.launch {

                try {
                    Toast.makeText(
                        AppController.getInstance(),
                        "Starting..........",
                        Toast.LENGTH_LONG
                    ).show()

                    val categoryList = dbViewModel.allCategory?.value
                    val categoryList1 = repo.observeCategories()

                    if (categoryList1.value == null) {
                        Toast.makeText(
                            AppController.getInstance(),
                            "List Is Null",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    if (categoryList1.value != null) {
                        Toast.makeText(
                            AppController.getInstance(),
                            "List Is NOT-Null",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    categoryList?.let {
                        setupCategoriesRecycler(ArrayList(it))
                    }
                } catch (e: Exception) {
                    // Handle exception here
                    Log.e("FetchData", "Error fetching data: ${e.message}")
                }
            }.start()*/

        /*lifecycleScope.launch {
            try {
                val categoryList = dbViewModel.allCategory?.value
                categoryList?.let {
                    setupCategoriesRecycler(ArrayList(it))
                }
            } catch (e: Exception) {
                // Handle exception here
                Log.e("FetchData", "Error fetching data: ${e.message}")
            }

            *//*val categories = withContext(Dispatchers.IO) {
//                dbViewModel.allCategory.value ?: emptyList()
                repo.observeCategories().value ?: emptyList()
            }
            withContext(Dispatchers.Main) {
                setupCategoriesRecycler(ArrayList(categories))
            }*//*


            *//*
                val data = ArrayList(categories)
                setupCategoriesRecycler(data)
            *//*
        }*/

        /*
        dbViewModel!!.allCategory.observe(this) {
            val data = ArrayList<Category>()
            data.addAll(it)

            setupCategoriesRecycler(data)
        }*/
        lifecycleScope.launch {
            val categoryList = withContext(Dispatchers.IO) { repo.getCategories() }
            withContext(Dispatchers.Main) {
                setupCategoriesRecycler(ArrayList(categoryList))
            }
        }
    }

    // apply data from database to Dialog recycler
    private fun setupCategoriesRecycler(categoryList: ArrayList<Category>) {
        with(binding.categoriesRecyclerDialogData) {
            adapter = CategoryAdapter(categoryList).apply {
                // INIT FOR
                registerOnItemClickListener(this@PickCategoryBottomSheetDialogFragment)
            }
            setHasFixedSize(false)
        }
    }


    // SingleClick not Like the original adapter that activated with a long click
    // From Receive Adapter Clicks OnItemClickListener<Category>
    override fun onClick(obj: Category) {
        onDataClickListener?.onItemClicked(category = obj)
    }

    override fun onDetach() {
        super.onDetach()
        onDataClickListener = null
    }

    //.. ****for this class communication with outSide****outSide*******outSide****outSide
    interface OnDataClickListener {
        fun onItemClicked(category: Category)

    }
}

