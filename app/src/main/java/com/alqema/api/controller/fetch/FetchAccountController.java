package com.alqema.api.controller.fetch;

import androidx.annotation.NonNull;

import com.alqema.api.callbacks.ResponseCallback;
import com.alqema.api.retrofit.ApiController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchAccountController {
    private final ApiController apiController;

    private FetchAccountController() {
        apiController = ApiController.getInstance();
    }

    private static volatile FetchAccountController fetchAccountController;

    public static synchronized FetchAccountController getInstance() {
        if (fetchAccountController == null) {
            fetchAccountController = new FetchAccountController();
        }
        return fetchAccountController;
    }

    private void fetchAll(ResponseCallback<ResponseBody> responseCallback) {
        Call<ResponseBody> call = apiController.getRetrofitRequests().fetchAccounts();
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
