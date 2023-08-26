package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alqema.R
import com.alqema.adapters.listeners.OnItemActionClickListener
import com.alqema.adapters.recycler_view.receipt.cate.CategoryAdapter
import com.alqema.app_system.AppController
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.local_db.models.ReceiptCategory
import com.alqema.database.repo.AlqemaRepository
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

    // database
    private val repository: AlqemaRepository = AlqemaRepository(AppController.getInstance())

    // Creation and Update
    private val viewModel: ReceiptViewModel by viewModels() // TODO:Check This.
    private var updateState = false

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
                performCreation()
            }
        }
    }

    //=====================================================================================

    //.. Holders
    private var receiptNumber: Int? = null
    private var accountNumber: Int? = null
    private var receiptDetails: String? = null
    private var receiptDate: Long? = null
    private var barcodeNumber: String? = null

    // Unique ID to be set as a ref between the 2 classes
    private var categoryListIds: Int? = null

    private var total: Double = 0.0


    private fun performCreation() {
        readInputs()
        if (checkData()) create() else showError()
    }

    private fun checkData(): Boolean =
        pickedAccount != null &&
                categoriesList.isNotEmpty() &&
                receiptNumber != null &&
                accountNumber != null &&
                receiptDetails != null &&
                receiptDate != null &&
                barcodeNumber != null &&
                categoryListIds != null

    private fun readInputs() {
        with(binding) {
            receiptNumber = edReceiptNumber.text.toInt()
            accountNumber = pickedAccount?.accountNumber
            receiptDetails = edReceiptDetails.text.toString()
            receiptDate = System.currentTimeMillis()
            barcodeNumber = edReceiptBarcode.text.toString()
            categoryListIds = receiptNumber
        }
    }

    private fun calculateTotal(category: Category) {
        /*        categoriesList.forEach {
                    this.total = total.plus(it.sellingPrice)
                }*/
        this.total = total.plus(category.sellingPrice)

        binding.tvReceiptTotal.text = this.total.toString()
        Log.i("AddReceiptFragment", "calculateTotal: total: ${this.total}")
    }

    private fun calculateRemovedTotal(obj: Category) {

        if (total != 0.0) total = total.minus(obj.sellingPrice) else GeneralUtils.getInstance()
            .showSnackBar(binding.root, "cant mains 0")

        binding.tvReceiptTotal.text = this.total.toString()
        Log.i("AddReceiptFragment", "calculateTotal: total: ${this.total}")
    }

    private fun createRCInstances() {
        for (it in categoriesList) {
            Log.i("ARF", "createRCInstances: ${it.categoryName}")

            val receiptCategory = ReceiptCategory.Builder()
                .withRCId(receiptNumber!!)
                .withReceiptNumber(receiptNumber!!)
                .withCategoryNumber(it.categoryNumber)
                .build()

            repository.insertReceiptCategory(receiptCategory)
        }
    }

    private fun create() {
        val receipt = Receipt.Builder()
            .withReceiptNumber(receiptNumber!!)
            .withAccountNumber(accountNumber!!)
            .withReceiptDetails(receiptDetails!!)
            .withReceiptDate(receiptDate!!)
            .withBarcodeNumber(barcodeNumber!!)
            .withCategoryListIds(categoryListIds!!)
            .withTotal(total)
            .build()

        // this must go first
        createRCInstances()
        // this comes second
        repository.insertReceipt(receipt)

        GeneralUtils.getInstance().showSnackBar(binding.root, getString(R.string.created_message))
    }

    private fun showError() {
        GeneralUtils
            .getInstance()
            .showSnackBar(binding.root, getString(R.string.empty_fields_message))
    }

    //=====================================================================================

    // From <Dialog> PickCategoryBottomSheetDialogFragment
    override fun onItemClicked(category: Category) {
        // TODO:Clean ThisOne
        // fill the current array (holderOfTempData)
        categoriesList.add(category)
        // notify the recycler and it adapter with the changes to the data
        categoryAdapter.addUpCategoryList(categoriesList)
//        categoryAdapter.addSingleCategory(category) ..this works but no need .due the need of the (holderOfTempData)

        // update the count of item in the ui
        binding.tvReceiptCategoriesCount.text = categoriesList.size.toString()
        //..show message that indicate the user with the state
        GeneralUtils.getInstance().showToast(
            requireContext(),
            "${getString(R.string.category_got_added)}${category.categoryName}"
        )
        //.. now update the total value to the screen
        calculateTotal(category)
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
        // TODO:Clean ThisOne
        categoriesList.remove(obj)
        categoryAdapter.removeFromCategoryList(categoriesList)
        // update the count of item in the ui
        binding.tvReceiptCategoriesCount.text = categoriesList.size.toString()
        GeneralUtils.getInstance().showToast(
            requireContext(),
            "${getString(R.string.category_got_removed)}${obj.categoryName}"
        )
        //.. now update the total value to the screen
        calculateRemovedTotal(obj)
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

        // Format the text using HTML <br>
        // Format the text using HTML with inline styling
        val formattedText = """
            <font color='#FF482C'><b>${getString(R.string.name)}: </b></font> <font color='#5A788C'>$name</font><br>
            <font color='#FF482C'><b>${getString(R.string.number)}: </b></font> <font color='#212121'>$number</font>""".trimIndent()

        val htmlFormattedText = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)

        with(binding.edReceiptAccountNumber) {
            text = htmlFormattedText
        }
    }

}

private fun Editable?.toInt(): Int? {
    if (this?.isNotEmpty() == true) return this.toString().toInt()
    return null
}