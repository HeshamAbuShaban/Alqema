package com.alqema.database.vm;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.alqema.database.repo.AlqemaRepository;
import com.alqema.models.Account;
import com.alqema.models.Category;

import java.util.ArrayList;
import java.util.List;


public class DatabaseViewModel extends AndroidViewModel {
    private final AlqemaRepository repository;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new AlqemaRepository(application);
    }

    // Category
    public LiveData<List<Category>> getAllCategory(){
        return repository.observeCategories();
    }

    public void insertCategory(Category reminder) {
        repository.insertCategory(reminder);
    }

    public void deleteCategory(Category reminder) {
        repository.deleteCategory(reminder);
    }


    // Account
    public LiveData<List<Account>> getAllAccount(){
        return repository.observeAccounts();
    }

    public void insertAccount(Category reminder) {
        repository.insertCategory(reminder);
    }

    public void deleteAccount(Category reminder) {
        repository.deleteCategory(reminder);
    }

}