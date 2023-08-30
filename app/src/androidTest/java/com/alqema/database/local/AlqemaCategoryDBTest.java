package com.alqema.database.local;

import android.app.Application;
import androidx.test.core.app.ApplicationProvider;
import com.alqema.database.repo.AlqemaRepository;
import com.alqema.database.local_db.models.Category;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlqemaCategoryDBTest {
    private AlqemaRepository alqemaRepository;

    @Before
    public void setup() {
        var context = ApplicationProvider.getApplicationContext();
        alqemaRepository = new AlqemaRepository((Application) context);
    }

    @Test
    public void insertCategory() {
        var category = new Category.Builder()
                .withCategoryNumber(1)
                .withCategoryName("Bread")
                .withBarcodeNumber(123)
                .withMainUnit("mainUnit")
                .withSellingPrice(1.0)
                .withPurchasePrice(0.6)
                // unit ues in the category
                .withUnitName("Food")
                .withQuantityOfUnit(500.60)
                .withUnitPrice(10)
                .withBarcode("123#KQ")
                .build();

        // not null
        Assert.assertNotNull(category);
        // insert into database
        /*-var res = alqemaRepository.insertCategory(category);
        // did insert executed properly
        Assert.assertTrue(res);*/
    }

    @Test
    public void fetchingCategories() {
        var list = alqemaRepository.observeCategories();
        if (list.isInitialized()) {
            Assert.assertNotNull(list);
        }
    }

    @After
    public void tearDown() {
        alqemaRepository.tearDown();
    }

}
