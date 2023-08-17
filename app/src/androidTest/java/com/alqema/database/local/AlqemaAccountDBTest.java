package com.alqema.database.local;

import android.app.Application;

import androidx.test.core.app.ApplicationProvider;

import com.alqema.database.repo.AlqemaRepository;
import com.alqema.models.Account;
import com.alqema.models.constants.account.AccountCurrency;
import com.alqema.models.constants.account.AccountDetails;
import com.alqema.models.constants.account.AccountNature;
import com.alqema.models.constants.account.AccountType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlqemaAccountDBTest {

    private AlqemaRepository alqemaRepository;

    @Before
    public void setup() {
        var context =
                ApplicationProvider.getApplicationContext();
        alqemaRepository = new AlqemaRepository((Application) context);
    }

    @Test
    public void testInserting() {
        alqemaRepository.insertAccount(instance());

        Assert.assertNotNull(alqemaRepository.observeAccounts());
    }

    private Account instance() {
        return new Account.Builder()
                .withAccountNumber(1)
                .withAccountName("Hesham")
                .withMobileNumber("+970-56-859-1804")
                .withAddress("Gaza Al Re-mal")
                .withAccountDetails(AccountDetails.Customer)
                .withAccountType(AccountType.Employment)
                .withAccountNature(AccountNature.CreditorOnly)
                .withAccountCurrency(AccountCurrency.NIS)
                .withBelongsToAccount(1)
                .build();
    }

    @Test
    public void testFetching() {
        var list = alqemaRepository.observeAccounts();
        if (list.isInitialized()) {
            Assert.assertNotNull(list);
        }
    }

    @After
    public void tearDown() {
        alqemaRepository.tearDown();
    }
}
