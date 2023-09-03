package com.alqema.ui.fragments.displaying_ui.receipt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.self.ReceiptAdapter
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.vm.DatabaseViewModel
import com.alqema.databinding.FragmentReceiptDisplayBinding
import com.alqema.utils.GeneralUtils

class ReceiptDisplayFragment : Fragment(), OnItemClickListener<Receipt> {
    private lateinit var binding: FragmentReceiptDisplayBinding

    private lateinit var dbViewModel: DatabaseViewModel

    /*private var searchQuery: String? = null*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReceiptDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        displayReceipts()
    }

    private fun setupListeners() {
        binding.receiptsSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                /*searchQuery =*/  /*readSearchBarText()*/
                dbViewModel.getAllReceipts(text.toInt() ?: return).observe(viewLifecycleOwner) {
                    setupReceiptAdapter(it)
                }
            }
        })
    }

    /*private fun readSearchBarText(): String {
        return binding.accountsSearchBar.text.toString()
    }*/

    private fun displayReceipts() {
        // first call the data
        dbViewModel.allReceipts.observe(viewLifecycleOwner) {
            setupReceiptAdapter(it)
        }
    }

    private fun setupReceiptAdapter(receiptList: List<Receipt>) {
        with(binding.receiptsRecyclerView) {
            setHasFixedSize(true)
            adapter = ReceiptAdapter(receiptList).also {
                it.registerOnItemClickListener(this@ReceiptDisplayFragment)
            }
        }
    }

    // this to handle the click on an item to make an update on it.
    override fun onClick(obj: Receipt) {
        val navCon = binding.root.findNavController()
        val action =
            ReceiptDisplayFragmentDirections.actionNavigationReceiptDisplayToNavigationReceiptItemsDisplay(
                obj.receiptNumber!!
            )
        //.. REMOVE DEFAULT VALUE OF THE ARG
        navCon.navigate(action)
        GeneralUtils.getInstance().showSnackBar(binding.root, obj.receiptNumber.toString())
    }

}

private fun Editable?.toInt(): Int? {
    if (this?.isNotEmpty() == true) return this.toString().toInt()
    return null
}
