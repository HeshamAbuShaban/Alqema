package com.alqema.api.controller.fetch;

import androidx.annotation.NonNull;

import com.alqema.api.callbacks.ResponseCallback;
import com.alqema.api.retrofit.ApiController;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUnitController {
    private final ApiController apiController;

    private FetchUnitController() {
        apiController = ApiController.getInstance();
    }

    private static volatile FetchUnitController fetchUnitController;

    public static synchronized FetchUnitController getInstance() {
        if (fetchUnitController == null) {
            fetchUnitController = new FetchUnitController();
        }
        return fetchUnitController;
    }

    private void fetchAll(ResponseCallback<ResponseBody> responseCallback) {
        Call<ResponseBody> call = apiController.getRetrofitRequests().fetchUnits();
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
