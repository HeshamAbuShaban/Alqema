package com.alqema.api.controller.creation;

import androidx.annotation.NonNull;

import com.alqema.api.callbacks.ResponseCallback;
import com.alqema.api.retrofit.ApiController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryController {
    private final ApiController apiController;

    private CreateCategoryController() {
        apiController = ApiController.getInstance();
    }

    private static volatile CreateCategoryController createAccountController;

    public static synchronized CreateCategoryController getInstance() {
        if (createAccountController == null) {
            createAccountController = new CreateCategoryController();
        }
        return createAccountController;
    }

    private void create(ResponseCallback<ResponseBody> responseCallback) {
        Call<ResponseBody> call = apiController.getRetrofitRequests().createCategory();
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
