package com.alqema.ui.fragments.creation_ui.unit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alqema.R
import com.alqema.databinding.FragmentAddUnitBinding

class AddUnitFragment : Fragment() {
    private lateinit var binding: FragmentAddUnitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddUnitBinding.bind(
            inflater.inflate(
                R.layout.fragment_add_unit,
                container,
                false)
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println()
    }
}