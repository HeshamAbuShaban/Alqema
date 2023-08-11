package com.alqema.api.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// This is an INTERFACE FOR - RETROFIT - AS =>API END POINTS
public interface RetrofitRequests {
    // For Reference : Here is the full request link : https://picsum.photos/200/300 , notice how the urn 200/300
    @GET("200/300")
    Call<ResponseBody> randomImage200by300();

    @GET("200")
    Call<ResponseBody> randomImage200();

    @GET("300")
    Call<ResponseBody> randomImage300();

    @GET("{size}")
    Call<ResponseBody> randomImage(@Path("size") int size);

    //------------------------------------------------------------------------
    @GET()
    Call<ResponseBody> createAccount();

    @GET()
    Call<ResponseBody> createCategory();

    @GET()
    Call<ResponseBody> createUnit();

    //.........
    @GET()
    Call<ResponseBody> fetchAccounts();

    @GET()
    Call<ResponseBody> fetchCategories();

    @GET()
    Call<ResponseBody> fetchUnits();

}
