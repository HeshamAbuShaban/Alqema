package com.alqema.ui.fragments.dialogs.chooes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alqema.databinding.FragmentCreationBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreationBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private var onClickListener: OnClickListener? = null

    private lateinit var binding: FragmentCreationBottomSheetDialogBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onClickListener = parentFragment as OnClickListener?
            Log.d("OnClickListener", "onAttach: Done for :$onClickListener")
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
            btnCreateUnits.setOnClickListener { view_account ->
                onClickListener?.onClick(view_account)
                dismiss()
            }

            btnCreateCategory.setOnClickListener { view_category ->
                onClickListener?.onClick(view_category)
                dismiss()
            }

            btnCreateUnits.setOnClickListener { view_unit ->
                onClickListener?.onClick(view_unit)
                dismiss()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        onClickListener = null
    }

    interface OnClickListener {
        fun onClick(view: View)
    }

}