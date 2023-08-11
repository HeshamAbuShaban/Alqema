package com.alqema.api.controller.creation;

import androidx.annotation.NonNull;

import com.alqema.api.callbacks.ResponseCallback;
import com.alqema.api.retrofit.ApiController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUnitController {
    private final ApiController apiController;

    private CreateUnitController() {
        apiController = ApiController.getInstance();
    }

    private static volatile CreateUnitController createAccountController;

    public static synchronized CreateUnitController getInstance() {
        if (createAccountController == null) {
            createAccountController = new CreateUnitController();
        }
        return createAccountController;
    }

    private void create(ResponseCallback<ResponseBody> responseCallback) {
        Call<ResponseBody> call = apiController.getRetrofitRequests().createUnit();
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
