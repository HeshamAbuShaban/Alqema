package com.alqema.app_system;

import android.app.Application;

import com.alqema.database.local_db.models.Account;
import com.alqema.database.local_db.models.Category;
import com.alqema.database.local_db.models.Receipt;
import com.alqema.database.local_db.models.ReceiptCategory;
import com.alqema.database.repo.AlqemaRepository;

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

    private void setupDatabaseWithInitialData() {
        var repo = new AlqemaRepository(this);

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


        // RC 1
        var rc11 = new ReceiptCategory.Builder().withRCId(1).withReceiptNumber(1).withCategoryNumber(1).build();
        var rc12 = new ReceiptCategory.Builder().withRCId(2).withReceiptNumber(1).withCategoryNumber(2).build();
        var rc13 = new ReceiptCategory.Builder().withRCId(3).withReceiptNumber(1).withCategoryNumber(3).build();
        var rc14 = new ReceiptCategory.Builder().withRCId(4).withReceiptNumber(1).withCategoryNumber(3).build();
        repo.insertReceiptCategory(rc11);
        repo.insertReceiptCategory(rc12);
        repo.insertReceiptCategory(rc13);
        repo.insertReceiptCategory(rc14);

        // RC 2
        var rc21 = new ReceiptCategory.Builder().withRCId(5).withReceiptNumber(2).withCategoryNumber(1).build();
        var rc22 = new ReceiptCategory.Builder().withRCId(6).withReceiptNumber(2).withCategoryNumber(4).build();
        var rc23 = new ReceiptCategory.Builder().withRCId(7).withReceiptNumber(2).withCategoryNumber(2).build();
        var rc24 = new ReceiptCategory.Builder().withRCId(8).withReceiptNumber(2).withCategoryNumber(3).build();
        repo.insertReceiptCategory(rc21);
        repo.insertReceiptCategory(rc22);
        repo.insertReceiptCategory(rc23);
        repo.insertReceiptCategory(rc24);

        // RC 3
        var rc31 = new ReceiptCategory.Builder().withRCId(9).withReceiptNumber(3).withCategoryNumber(1).build();
        var rc32 = new ReceiptCategory.Builder().withRCId(10).withReceiptNumber(3).withCategoryNumber(1).build();
        var rc33 = new ReceiptCategory.Builder().withRCId(11).withReceiptNumber(3).withCategoryNumber(1).build();
        var rc34 = new ReceiptCategory.Builder().withRCId(12).withReceiptNumber(3).withCategoryNumber(2).build();
        repo.insertReceiptCategory(rc31);
        repo.insertReceiptCategory(rc32);
        repo.insertReceiptCategory(rc33);
        repo.insertReceiptCategory(rc34);


        var r1 = new Receipt.Builder().withAccountNumber(1).withReceiptNumber(1).withCategoryListIds(1).withTotal(100).build();
        var r2 = new Receipt.Builder().withAccountNumber(2).withReceiptNumber(2).withCategoryListIds(2).withTotal(300).build();
        var r3 = new Receipt.Builder().withAccountNumber(3).withReceiptNumber(3).withCategoryListIds(3).withTotal(400).build();
        repo.insertReceipt(r1);
        repo.insertReceipt(r2);
        repo.insertReceipt(r3);
    }
}
