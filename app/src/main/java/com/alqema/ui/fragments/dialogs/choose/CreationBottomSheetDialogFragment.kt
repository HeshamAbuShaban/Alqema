package com.alqema.ui.fragments.dialogs.choose

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alqema.databinding.FragmentCreationBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreationBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private var onCreationClickListener: OnCreationClickListener? = null

    private lateinit var binding: FragmentCreationBottomSheetDialogBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onCreationClickListener = parentFragment as OnCreationClickListener?
            Log.d("OnClickListener", "onAttach: Done for :$onCreationClickListener")
            /*
             *  Must BE "*childFragmentManager*"
             *  ImagePickerDialogFragment().show(childFragmentManager, "ImagePicking")
             *
             */
        } catch (exception: ClassCastException) {
            throw ClassCastException(
                parentFragment.toString()
                        + " must implement OnClickListener, exception: " + exception
            )
        }
    }

    /*fun BottomSheetDialogFragment() {
        // Required empty public constructor
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCreationBottomSheetDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnCreateAccounts.setOnClickListener { viewAccount ->
                onCreationClickListener?.onCreateClicked(viewAccount)
                dismiss()
            }

            btnCreateCategory.setOnClickListener { viewCategory ->
                onCreationClickListener?.onCreateClicked(viewCategory)
                dismiss()
            }

            btnCreateUnits.setOnClickListener { viewUnit ->
                onCreationClickListener?.onCreateClicked(viewUnit)
                dismiss()
            }

            btnCreateReceipts.setOnClickListener { viewReceipts ->
                onCreationClickListener?.onCreateClicked(viewReceipts)
                dismiss()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        onCreationClickListener = null
    }

    interface OnCreationClickListener {
        fun onCreateClicked(view: View)
    }

}