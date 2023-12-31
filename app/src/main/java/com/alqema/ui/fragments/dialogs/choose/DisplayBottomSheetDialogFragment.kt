package com.alqema.ui.fragments.dialogs.choose

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alqema.databinding.FragmentDisplayBottomSheetDialogBinding

class DisplayBottomSheetDialogFragment : Fragment() {


    private var onDisplayClickListener: OnDisplayClickListener? = null

    private lateinit var binding: FragmentDisplayBottomSheetDialogBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onDisplayClickListener = parentFragment as OnDisplayClickListener?
            Log.d("OnClickListener", "onAttach: Done for :$onDisplayClickListener")
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


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDisplayBottomSheetDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnDisplayAccounts.setOnClickListener { viewAccount ->
                onDisplayClickListener?.onDisplayClicked(viewAccount)
                /*dismiss()*/
            }

            btnDisplayCategory.setOnClickListener { viewCategory ->
                onDisplayClickListener?.onDisplayClicked(viewCategory)
                /*dismiss()*/
            }

            /*            btnDisplayUnits.setOnClickListener { viewUnit ->
                            onDisplayClickListener?.onDisplayClicked(viewUnit)
                            /*dismiss()*/
                        }*/
            btnDisplayReceipts.setOnClickListener { viewReceipts ->
                onDisplayClickListener?.onDisplayClicked(viewReceipts)
                /*dismiss()*/
            }

            btnDisplayPayments.setOnClickListener { viewPayments ->
                onDisplayClickListener?.onDisplayClicked(viewPayments)
                /*dismiss()*/
            }

        }
    }

    override fun onDetach() {
        super.onDetach()
        onDisplayClickListener = null
    }

    interface OnDisplayClickListener {
        fun onDisplayClicked(view: View)
    }

}