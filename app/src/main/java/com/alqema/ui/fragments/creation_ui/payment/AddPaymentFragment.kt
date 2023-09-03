package com.alqema.ui.fragments.creation_ui.payment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alqema.R
import com.alqema.database.local_db.models.Account
import com.alqema.databinding.FragmentAddPaymentBinding
import com.alqema.ui.fragments.dialogs.data.PickAccountBottomSheetDialogFragment
import com.alqema.utils.GeneralUtils
import com.alqema.utils.TimeUpdater

class AddPaymentFragment : Fragment(),
    PickAccountBottomSheetDialogFragment.OnDataClickListener,
    TimeUpdater.TimeUpdateListener {
    // ViewModel
    private val viewModel: PaymentViewModel by viewModels()

    // UI
    private lateinit var binding: FragmentAddPaymentBinding

    // only for account.. <tobe received from the Dialog>
    private var pickedAccount: Account? = null

    // Timer format Updated each tic
    private lateinit var timeUpdater: TimeUpdater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPaymentBinding.inflate(layoutInflater)
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
    }

    private fun setupTimer() {
        timeUpdater = TimeUpdater(this)
        timeUpdater.startUpdatingTime()
    }

    private fun setupListeners() {
        with(binding) {
            // Pick account for Payment
            btnAddPaymentAccountNumber.setOnClickListener {
                PickAccountBottomSheetDialogFragment().show(
                    childFragmentManager, "Add Account Event"
                )
            }

            // Create Payment
            confirmButton.setOnClickListener {
                if (pickedAccount != null) {
                    viewModel.performCreation(binding, pickedAccount!!)
                } else {
                    GeneralUtils.getInstance().showSnackBar(binding.root, "Pick An Account!")
                }
            }
        }
    }

    //==============================================================================================

    // Update ScreenTimer
    override fun onTimeUpdated(time: String) {
        binding.paymentDateInformTitle.text = time
    }

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
            <font color='#F9F871'><b>${getString(R.string.name)}: </b></font> <u><font color='#F1F0EE'>$name</font></u><br>
            <font color='#F9F871'><b>${getString(R.string.number)}: </b></font> <u><font color='#F1F0EE'>$number</font></u>""".trimIndent()

        val htmlFormattedText = Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY)

        with(binding.edPaymentAccountNumber) {
            setBackgroundResource(R.drawable.shape_text_view_account_info)
            text = htmlFormattedText
        }
        binding.tvAccountBalance.text = balance.toString()
    }

    // LifeCycle----
    override fun onDestroyView() {
        super.onDestroyView()
        timeUpdater.stopUpdatingTime()
    }
}