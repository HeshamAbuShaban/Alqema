package com.alqema.ui.fragments.dialogs.read_camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.alqema.databinding.FragmentReadCameraQrCodeBinding
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReadCameraQrCodeFragment : BottomSheetDialogFragment() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    var listener: QrCodeCallback? = null

    private lateinit var binding: FragmentReadCameraQrCodeBinding

    private lateinit var codeScanner: CodeScanner

    private fun setupRegister() {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                // Check if all permissions are granted
                val allGranted = permissions.all { it.value }
                if (allGranted) {
                    // All permissions granted, you can proceed with sending notifications
                    Toast.makeText(
                        requireContext(),
                        "Great Now you are all set to use The QrReader",
                        Toast.LENGTH_LONG
                    ).show()
                    //.. try access camera when done
                    activateScannerView()
                } else {
                    // Permission denied, handle accordingly
                    // At least one permission denied, handle accordingly (e.g., show a message or disable certain features)
                    Toast.makeText(
                        requireContext(),
                        "Permission denied. Cannot read Qrcode.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReadCameraQrCodeBinding.inflate(layoutInflater)
        initCodeScanner()
        setupRegister()
        return binding.root
    }

    private fun initCodeScanner() {
        val scannerView = binding.scannerView
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arePermissionsGranted()) {

            activateScannerView()

        } else {
            requestPermissions()
        }
    }

    private fun activateScannerView() {
        val scannerView = binding.scannerView
        val activity = requireActivity()
        codeScanner.decodeCallback = DecodeCallback { result ->
            activity.runOnUiThread {
                Toast.makeText(activity, result.text, Toast.LENGTH_LONG).show()

                binding.tvResult.text = result.text

                binding.confirmButton.setOnClickListener {
                    listener?.submit(result.text)
                    dismiss()
                }

            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    // =====================================================================

    private fun arePermissionsGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    // this lunch the Permission runtime Dialog for the user asking for the @REQUIRED_PERMISSIONS array of string using the ActivityResultLauncher<Array<String>>
    private fun requestPermissions() {
        requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
    }
    interface QrCodeCallback {
        fun submit(result: String)
    }
}
