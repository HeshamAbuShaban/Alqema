package com.alqema.app_system;

import android.app.Application;

import com.alqema.database.local_db.models.Category;
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
        var category1 = new Category.Builder().withCategoryNumber(4).withCategoryName("Test1").withUnitName("Test-Unit1").build();
        var category2 = new Category.Builder().withCategoryNumber(5).withCategoryName("Test2").withUnitName("Test-Unit1").build();
        var category3 = new Category.Builder().withCategoryNumber(6).withCategoryName("Test3").withUnitName("Test-Unit1").build();
        var category4 = new Category.Builder().withCategoryNumber(7).withCategoryName("Test4").withUnitName("Test-Unit1").build();
        var repo = new AlqemaRepository(this);
        repo.insertCategory(category1);
        repo.insertCategory(category2);
        repo.insertCategory(category3);
        repo.insertCategory(category4);
    }
}
