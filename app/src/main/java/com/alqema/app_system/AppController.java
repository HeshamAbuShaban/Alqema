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
        repo.insertCategory(categoryInstance());
    }

    private Account accountInstance() {
        return new Account.Builder()
                .withAccountNumber(3)
                .withAccountName("Mo-taz")
                .withMobileNumber("+970-56-245-2465")
                .withAddress("Rafa-h Dow ar naser")
                .withAccountDetails(AccountDetails.Employees)
                .withAccountType(AccountType.BalanceSheet)
                .withAccountNature(AccountNature.DebtorAndCreditor)
                .withAccountCurrency(AccountCurrency.EGP)
                .withBelongsToAccount(2)
                .build();
    }

    private Category categoryInstance() {
        return new Category.Builder()
                .withCategoryNumber(3)
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

}
