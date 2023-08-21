package com.alqema.ui.fragments.creation_ui.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alqema.R
import com.alqema.databinding.FragmentAddReceiptBinding

class AddReceiptFragment : Fragment() {
    private lateinit var binding: FragmentAddReceiptBinding
    private lateinit var viewModel: ReceiptViewModel
    private var updateState = false

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
        println()
    }

}