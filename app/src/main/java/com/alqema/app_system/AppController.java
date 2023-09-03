package com.alqema.app_system;

import android.app.Application;

import com.alqema.app_system.node.UseDatabase;
import com.alqema.database.local_db.models.Account;
import com.alqema.database.local_db.models.Category;

public class AppController extends Application {
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        setupDatabaseWithInitialData();
    }

    // Instance : helpful with the context etc...
    public static AppController getInstance() {
        if (appController != null) {
            return appController;
        }
        return null;
    }

    private void setupDatabaseWithInitialData(){
        var repo = UseDatabase.getInstance().getRepository();

        var category1 = new Category.Builder().withCategoryNumber(1).withCategoryName("Food-(1) XD.").withSellingPrice(10.0).withUnitName("Test-Unit1").build();
        var category2 = new Category.Builder().withCategoryNumber(2).withCategoryName("Games-(2) XD.").withSellingPrice(20.0).withUnitName("Test-Unit1").build();
        var category3 = new Category.Builder().withCategoryNumber(3).withCategoryName("Car-(3) XD.").withSellingPrice(30.0).withUnitName("Test-Unit1").build();
        var category4 = new Category.Builder().withCategoryNumber(4).withCategoryName("Guns-(4) XD.").withSellingPrice(40.0).withUnitName("Test-Unit1").build();
        repo.insertCategory(category1);
        repo.insertCategory(category2);
        repo.insertCategory(category3);
        repo.insertCategory(category4);

        var account1 = new Account.Builder().withAccountNumber(1).withAccountName("Hesham-(1) XD.").build();
        var account2 = new Account.Builder().withAccountNumber(2).withAccountName("Ahmed-(2) XD.").build();
        var account3 = new Account.Builder().withAccountNumber(3).withAccountName("Omar-(3) XD.").build();
        var account4 = new Account.Builder().withAccountNumber(4).withAccountName("Khaled-(4) XD.").build();
        repo.insertAccount(account1);
        repo.insertAccount(account2);
        repo.insertAccount(account3);
        repo.insertAccount(account4);
    }
}
