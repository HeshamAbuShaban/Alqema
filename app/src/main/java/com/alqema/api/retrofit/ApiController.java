package com.alqema.api.retrofit;

// THIS CLASS IS FOR connecting between the RetrofitRequests Interface AND RetrofitSettings Class
public class ApiController {

    private final RetrofitRequests retrofitRequests;
    private static volatile ApiController Instance;

    private ApiController(){
        retrofitRequests = RetrofitSettings.getInstance().create(RetrofitRequests.class);
    }

    public static synchronized ApiController getInstance(){
        if (Instance == null) {
            Instance = new ApiController();
        }
        return Instance;
    }

    public RetrofitRequests getRetrofitRequests() {
        return retrofitRequests;
    }
}
