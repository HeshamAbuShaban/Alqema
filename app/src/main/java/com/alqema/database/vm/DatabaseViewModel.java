package com.alqema.database.vm;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alqema.database.repo.AlqemaRepository;
import com.alqema.models.Account;
import com.alqema.models.Category;

import java.util.List;


public class DatabaseViewModel extends AndroidViewModel {
    private final AlqemaRepository repository;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new AlqemaRepository(application);
    }

    // Category
    public LiveData<List<Category>> getAllCategory() {
        return repository.observeCategories();
    }

    public LiveData<List<Category>> getAllCategories(String name) {
        return repository.observeCategories(name);
    }

    public LiveData<Category> getCategory(int id) {
        return repository.getCategory(id);
    }

    public void insertCategory(Category reminder) {
        repository.insertCategory(reminder);
    }

    public void deleteCategory(Category reminder) {
        repository.deleteCategory(reminder);
    }


    // Account
    public LiveData<List<Account>> getAllAccount() {
        return repository.observeAccounts();
    }

    public LiveData<List<Account>> getAllAccount(String name) {
        return repository.observeAccounts(name);
    }

    public LiveData<Account> getAccount(int id) {
        return repository.getAccount(id);
    }

    public void insertAccount(Category reminder) {
        repository.insertCategory(reminder);
    }

    public void deleteAccount(Category reminder) {
        repository.deleteCategory(reminder);
    }

}