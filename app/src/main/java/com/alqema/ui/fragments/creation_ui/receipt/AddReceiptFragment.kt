package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alqema.R
import com.alqema.adapters.listeners.OnItemActionClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.app_system.node.UseDatabase
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.local_db.models.ReceiptCategory
import com.alqema.database.local_db.models.constants.state.ItemState
import com.alqema.databinding.FragmentAddReceiptBinding
import com.alqema.ui.fragments.dialogs.data.PickAccountBottomSheetDialogFragment
import com.alqema.ui.fragments.dialogs.data.PickCategoryBottomSheetDialogFragment
import com.alqema.utils.GeneralUtils
import com.alqema.utils.TimeUpdater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddReceiptFragment : Fragment(),
    PickCategoryBottomSheetDialogFragment.OnDataClickListener,
    PickAccountBottomSheetDialogFragment.OnDataClickListener,
    OnItemActionClickListener<Category>,
    TimeUpdater.TimeUpdateListener {
    // UI
    private lateinit var binding: FragmentAddReceiptBinding


    // only for account.. <tobe received from the Dialog>
    private var pickedAccount: Account? = null

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
        initializer()
    }

    private fun initializer() {
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
                performCreation()
            }
        }
    }

    //=====================================================================================

    //.. Holders
//    private var receiptNumber: Int? = null

    private fun getLastReceiptId(callback: (Int) -> Unit) {
        lifecycleScope.launch {
            val value = withContext(Dispatchers.IO) {
                UseDatabase.getInstance().repository.getLastId()
            }
            callback(value)
        }
    }


    private var accountNumber: Int? = null
    private var receiptDetails: String? = null
    private var receiptDate: Long? = null
//    private var barcodeNumber: String? = null

    // Unique ID to be set as a ref between the 2 classes
    private var categoryListIds: Int? = null

    private var total: Double = 0.0


    private fun performCreation() {
        readInputs()
        if (checkData()) create() else showError()
    }

    private fun checkData(): Boolean = pickedAccount != null && categoriesList.isNotEmpty()

    private fun readInputs() {
        with(binding) {
            accountNumber = pickedAccount?.accountNumber
            receiptDetails = edReceiptDetails.text.toString()
            receiptDate = System.currentTimeMillis()
            getLastReceiptId { categoryListIds = it + 1 }
        }
    }

    private fun calculateTotal(obj: Category, state: Int) {
        if (state == ItemState.ADDED) {
            this.total = total.plus(obj.sellingPrice)
            displayTotal()
        } else {
            total = if (total != 0.0) total.minus(obj.sellingPrice) else 0.0
            displayTotal()
        }
    }

    private fun displayTotal() {
        binding.tvReceiptTotal.text = this.total.toString()
        Log.i("AddReceiptFragment", "calculateTotal: total: ${this.total}")
    }

    private fun createRelationsInstances() {
        var counter = 0
        getLastReceiptId { lastReceiptId ->
            for (category in categoriesList) {
                Log.i("ARF", "createRCInstances: ${category.categoryName}")
                val receiptCategory = ReceiptCategory.Builder()
                    .withReceiptNumber(lastReceiptId)
                    .withCategoryNumber(category.categoryNumber)
                    .build()
                UseDatabase.getInstance().repository.insertReceiptCategory(receiptCategory)
            }

            counter++
        }
//        GeneralUtils.getInstance().showToast(requireContext(), "items count:$counter")
    }

    private fun create() {
        getLastReceiptId { lastReceiptId ->
            val receipt = Receipt.Builder()
                .withAccountNumber(accountNumber!!)
                .withReceiptDetails(receiptDetails!!)
                .withReceiptDate(receiptDate!!)
                .withCategoryListIds(lastReceiptId + 1)
                .withTotal(total)
                .build()

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    UseDatabase.getInstance().repository.insertReceipt(receipt)
                    createRelationsInstances()
                    UseDatabase.getInstance().repository
                        .updateAccountBalanceForReceipt(pickedAccount!!.accountNumber, total)
                }
                withContext(Dispatchers.Main) {
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, getString(R.string.created_message))
                    binding.confirmButton.isEnabled = false
                    delay(1500)
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, "You Can leave now!")
                }
            }
        }
    }

    private fun showError() {
        GeneralUtils
            .getInstance()
            .showSnackBar(binding.root, getString(R.string.empty_fields_message))
    }

    //=====================================================================================

    // From <Dialog> PickCategoryBottomSheetDialogFragment
    override fun onItemClicked(category: Category) {
        //.. state of item added
        addToListRecycler(category)
        // update count in ui
        displayItemsCount()
        //..show message
        GeneralUtils.getInstance().showToast(
            requireContext(),
            "${getString(R.string.category_got_added)}${category.categoryName}"
        )
        //.. update total to screen
        calculateTotal(category, ItemState.ADDED)
    }

    private fun addToListRecycler(category: Category) {
        // (holderOfTempData)
        categoriesList.add(category)
        // notify the recycler and it adapter with the changes to the data
        categoryAdapter.addUpCategoryList(categoriesList)
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
        //.. update state of items
        popListRecycler(obj)
        //.. count in ui
        displayItemsCount()
        //.. show message
        GeneralUtils.getInstance().showToast(
            requireContext(),
            "${getString(R.string.category_got_removed)}${obj.categoryName}"
        )
        //.. now update the total value to the screen
        calculateTotal(obj, ItemState.REMOVED)
    }

    private fun displayItemsCount() {
        binding.tvReceiptCategoriesCount.text = categoriesList.size.toString()
    }

    private fun popListRecycler(obj: Category) {
        categoriesList.remove(obj)
        categoryAdapter.removeFromCategoryList(categoriesList)
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
        this.pickedAccount = account
        setPickedAccountData(this.pickedAccount!!)
    }

    // call this when the this.account is not null finally , which means this fragment has received data of account from dialog
    private fun setPickedAccountData(account: Account) {

        val name = account.accountName
        val number = account.accountNumber
        val balance = account.balance

        // Format the text using HTML <br>
        // Format the text using HTML with inline styling
        val formattedText = """
            <font color='#F9F871'><b>${getString(R.string.name)}: </b></font> <u><font color='#F1FAEE'>$name</font></u><br>
            <font color='#F9F871'><b>${getString(R.string.number)}: </b></font> <u><font color='#F1FAEE'>$number</font></u>""".trimIndent()

        val htmlFormattedText = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)

        with(binding.edReceiptAccountNumber) {
            setBackgroundResource(R.drawable.shape_text_view_account_info)
            text = htmlFormattedText
        }
        binding.tvAccountBalance.text = balance.toString()
    }
}