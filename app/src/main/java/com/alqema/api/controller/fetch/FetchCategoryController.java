package com.alqema.api.controller.fetch;

import androidx.annotation.NonNull;

import com.alqema.api.callbacks.ResponseCallback;
import com.alqema.api.retrofit.ApiController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchCategoryController {
    private final ApiController apiController;

    private FetchCategoryController() {
        apiController = ApiController.getInstance();
    }

    private static volatile FetchCategoryController fetchCategoryController;

    public static synchronized FetchCategoryController getInstance() {
        if (fetchCategoryController == null) {
            fetchCategoryController = new FetchCategoryController();
        }
        return fetchCategoryController;
    }

    private void fetchAll(ResponseCallback<ResponseBody> responseCallback) {
        Call<ResponseBody> call = apiController.getRetrofitRequests().fetchCategories();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                responseCallback.onResponse(call, response);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                responseCallback.onFailure(call, throwable);
            }
        });
    }

}
