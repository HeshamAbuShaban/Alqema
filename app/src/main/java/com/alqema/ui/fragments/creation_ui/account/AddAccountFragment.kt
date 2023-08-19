package com.alqema.ui.fragments.creation_ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alqema.R
import com.alqema.databinding.FragmentAddAccountBinding
import com.alqema.utils.GeneralUtils

class AddAccountFragment : Fragment() {
    private lateinit var binding: FragmentAddAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddAccountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            confirmButton.setOnClickListener {
                viewModel.performCreation(binding, {
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, getString(R.string.empty_fields_message))
                }) {
                    GeneralUtils.getInstance()
                        .showSnackBar(binding.root, getString(R.string.created_message))
                }
            }
        }
    }

}