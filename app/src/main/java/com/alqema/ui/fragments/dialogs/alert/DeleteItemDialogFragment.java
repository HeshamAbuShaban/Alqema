package com.alqema.ui.fragments.dialogs.alert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alqema.databinding.FragmentDeleteItemDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class DeleteItemDialogFragment extends BottomSheetDialogFragment {

    private FragmentDeleteItemDialogBinding binding;
    private DeleteItemDialogListener logoutDialogListener;

    public void registerLogoutDialogListener(DeleteItemDialogListener logoutDialogListener) {
        this.logoutDialogListener = logoutDialogListener;
    }

    /*=@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            logoutDialogListener = (DeleteItemDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context
                    + " must implement LogoutDialogListener Exception: " + e);
        }
    }*/

    public DeleteItemDialogFragment() {
        // require empty one
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDeleteItemDialogBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListeners();
    }

    private void setupListeners() {
        binding.btnDelete.setOnClickListener(view -> {
            if (logoutDialogListener != null) {
                logoutDialogListener.onDialogDeleteClicked();
                dismiss();
            } else {
                Log.e("DDF", "setupListeners: LogoutDialogListener is null, register it first");
            }
        });
        binding.btnCancel.setOnClickListener(view -> dismiss());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logoutDialogListener = null;
    }

    public interface DeleteItemDialogListener {
        void onDialogDeleteClicked();
    }
}
