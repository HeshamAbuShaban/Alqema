package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alqema.R
import com.alqema.adapters.listeners.OnItemActionClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.FragmentAddReceiptBinding
import com.alqema.ui.fragments.dialogs.data.PickAccountBottomSheetDialogFragment
import com.alqema.ui.fragments.dialogs.data.PickCategoryBottomSheetDialogFragment
import com.alqema.utils.GeneralUtils
import com.alqema.utils.TimeUpdater


class AddReceiptFragment : Fragment(), PickCategoryBottomSheetDialogFragment.OnDataClickListener,
    PickAccountBottomSheetDialogFragment.OnDataClickListener, OnItemActionClickListener<Category>,
    TimeUpdater.TimeUpdateListener {
    // UI
    private lateinit var binding: FragmentAddReceiptBinding

    // Creation and Update
    private val viewModel: ReceiptViewModel by viewModels() // TODO:Check This.
    private var updateState = false

    // only for account.. <tobe received from the Dialog>
    private var account: Account? = null

    // only for categorise..
    private val categoriesList: ArrayList<Category> = ArrayList()
    private val categoryAdapter: CategoryAdapter = CategoryAdapter(ArrayList()).apply {
        setVisibility(true)
        registerOnItemActionClickListener(this@AddReceiptFragment)
    }

    // Timer format Updated each tic
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
                R.layout.fragment_add_receipt, container, false
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
            // Pick account for Receipt
            btnAddReceiptAccountNumber.setOnClickListener {
                PickAccountBottomSheetDialogFragment().show(
                    childFragmentManager, "Add Account Event"
                )
            }

            // Pick categories for Receipt
            btnAddCategoryItem.setOnClickListener {
                PickCategoryBottomSheetDialogFragment().show(
                    childFragmentManager, "Add Category Event"
                )
            }

            // Create Receipt
            confirmButton.setOnClickListener {
//                viewModel.performCreation()
            }
        }
    }

    // From <Dialog> PickCategoryBottomSheetDialogFragment
    override fun onItemClicked(category: Category) {
        // ToDo: Add the item to a global array that is passed to the Recycler of this fragment
        categoriesList.add(category)

        // TODO:Check These Out.....*************.....*************.....*************.....
        categoryAdapter.addUpCategoryList(categoriesList) // Check This or This :
//        categoryAdapter.addSingleCategory(category) // Check This or This :
        Toast.makeText(
            requireContext(),
            "${getString(R.string.category_got_added)}${category.categoryName}",
            Toast.LENGTH_SHORT
        ).show()
    }

    // setupCategoriesRecycler with the data coming from dialog PickCategoryBottomSheetDialogFragment
    private fun setupCategoriesRecycler() {

        with(binding.recReceiptCategories) {
            adapter = categoryAdapter
            startAnimation(GeneralUtils.getInstance().getAppRecyclerAnimation(requireContext()))
            setHasFixedSize(false)
        }
    }

    // OnItemActionClickListener<Category> From Adapter
    override fun onDelete(obj: Category) {
        categoriesList.remove(obj)
        categoryAdapter.removeFromCategoryList(categoriesList)
        Toast.makeText(
            requireContext(),
            "${getString(R.string.category_got_removed)}${obj.categoryName}",
            Toast.LENGTH_SHORT
        ).show()
    }


    // Update ScreenTimer
    override fun onTimeUpdated(time: String) {
        binding.receiptDateInformTitle.text = time
    }

    // LifeCycle----
    override fun onDestroyView() {
        super.onDestroyView()
        timeUpdater.stopUpdatingTime()
        binding.recReceiptCategories.clearAnimation()
    }


    // From Dialog Gives You account to use
    //.. set the account data to UI and use the Id to create the Recipe don't forget to send it to viewModel

    // From <Dialog> PickAccountBottomSheetDialogFragment
    override fun onItemClicked(account: Account) {
        // set this to ur holder
        this.account = account
        setPickedAccountData(this.account!!)
    }

    // call this when the this.account is not null finally , which means this fragment has received data of account from dialog
    private fun setPickedAccountData(account: Account) {

        val name = account.accountName
        val number = account.accountNumber

        // Format the text using HTML <br>
        // Format the text using HTML with inline styling
        val formattedText = """
            <font color='#FF482C'><b>${getString(R.string.name)}: </b></font> <font color='#5A788C'>$name</font><br>
            <font color='#FF482C'><b>${getString(R.string.number)}: </b></font> <font color='#212121'>$number</font>""".trimIndent()

        val htmlFormattedText = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)

        with(binding.edReceiptAccountNumber) {
            text = htmlFormattedText
//                "name: ${account.accountName} number: ${account.accountNumber}"
        }
    }

}