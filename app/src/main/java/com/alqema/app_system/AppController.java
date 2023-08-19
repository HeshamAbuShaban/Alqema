package com.alqema.app_system;

import android.app.Application;

import com.alqema.database.repo.AlqemaRepository;
import com.alqema.models.Account;
import com.alqema.models.Category;
import com.alqema.models.constants.account.AccountCurrency;
import com.alqema.models.constants.account.AccountDetails;
import com.alqema.models.constants.account.AccountNature;
import com.alqema.models.constants.account.AccountType;

public class AppController extends Application {
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        initDatabase();
    }

    // Instance : helpful with the context etc...
    public static AppController getInstance() {
        if (appController != null) {
            return appController;
        }
        return null;
    }

    private void initDatabase() {

        var repo = new AlqemaRepository(this);
        repo.insertAccount(accountInstance());
        repo.insertAccount(accountInstance2());
        repo.insertAccount(accountInstance3());

        repo.insertCategory(categoryInstance());
        repo.insertCategory(categoryInstance2());
        repo.insertCategory(categoryInstance3());
    }

    private Account accountInstance() {
        return new Account.Builder()
                .withAccountNumber(1)
                .withAccountName("Hesham")
                .withMobileNumber("+970-56-245-2465")
                .withAddress("Gaza-Omar Al Mokhtar")
                .withAccountDetails(AccountDetails.Employees)
                .withAccountType(AccountType.Employment)
                .withAccountNature(AccountNature.DebtorAndCreditor)
                .withAccountCurrency(AccountCurrency.JOD)
                .withBelongsToAccount(1)
                .build();
    }

    private Category categoryInstance() {
        return new Category.Builder()
                .withCategoryNumber(1)
                .withCategoryName("Cheese")
                .withBarcodeNumber(4424)
                .withMainUnit("mainUnit5")
                .withSellingPrice(5.40)
                .withPurchasePrice(4.4)
                // unit ues in the category
                .withUnitName("Food")
                .withQuantityOfUnit(80.60)
                .withUnitPrice(20)
                .withBarcode("920#QF")
                .build();
    }


    private Account accountInstance2() {
        return new Account.Builder()
                .withAccountNumber(2)
                .withAccountName("Ahmed")
                .withMobileNumber("+970-56-245-2465")
                .withAddress("Rafa-h Dow ar naser")
                .withAccountDetails(AccountDetails.Employees)
                .withAccountType(AccountType.BalanceSheet)
                .withAccountNature(AccountNature.DebtorAndCreditor)
                .withAccountCurrency(AccountCurrency.EGP)
                .withBelongsToAccount(2)
                .build();
    }
    private Category categoryInstance2() {
        return new Category.Builder()
                .withCategoryNumber(2)
                .withCategoryName("Cars")
                .withBarcodeNumber(2442)
                .withMainUnit("Cars")
                .withSellingPrice(5525.40)
                .withPurchasePrice(415.54)
                // unit ues in the category
                .withUnitName("Car")
                .withQuantityOfUnit(14580.60)
                .withUnitPrice(22520)
                .withBarcode("920#QF")
                .build();
    }

    private Account accountInstance3() {
        return new Account.Builder()
                .withAccountNumber(3)
                .withAccountName("Was-sem")
                .withMobileNumber("+970-56-123-2575")
                .withAddress("Rafa-Jab-alia")
                .withAccountDetails(AccountDetails.Expenses)
                .withAccountType(AccountType.BalanceSheet)
                .withAccountNature(AccountNature.DebtorAndCreditor)
                .withAccountCurrency(AccountCurrency.USD)
                .withBelongsToAccount(2)
                .build();
    }
    private Category categoryInstance3() {
        return new Category.Builder()
                .withCategoryNumber(3)
                .withCategoryName("Phone")
                .withBarcodeNumber(4424)
                .withMainUnit("mainUnit87")
                .withSellingPrice(5525.40)
                .withPurchasePrice(164.4)
                // unit ues in the category
                .withUnitName("Cell Phones")
                .withQuantityOfUnit(1280.60)
                .withUnitPrice(520)
                .withBarcode("6o3#qa")
                .build();
    }

}
