package com.alqema.app_system.node;

import com.alqema.app_system.AppController;
import com.alqema.database.repo.AlqemaRepository;

public class UseDatabase {
    private final AlqemaRepository repository;
    private static volatile UseDatabase useDatabase;

    private UseDatabase() {
        assert AppController.getInstance() != null;
        repository = new AlqemaRepository(AppController.getInstance());
    }

    public static synchronized UseDatabase getInstance() {
        if (useDatabase == null) {
            useDatabase = new UseDatabase();
        }
        return useDatabase;
    }

    public AlqemaRepository getRepository() {
        return repository;
    }
}
