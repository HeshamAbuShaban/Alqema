package com.alqema.api.retrofit;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//.. This Class More Like a Builder for the retrofit Instance.
public class RetrofitSettings {

    /**
     *  Instance: Retrofit
     *  This is the core of all the processors
     *  and this Class {@link RetrofitSettings}
     *  is dedicated to create the needed settings
     *  and adjusts to this instance so it can
     *  request the endPoints within the {@link RetrofitRequests}
     *  */
    private static volatile Retrofit retrofit;

    /**
     *  BaseURL : which are targeting the domain ,
     *  and has in it the EndPoints that declared in the
     *  @see [RetrofitRequests.java]
     *  @apiNote {@link RetrofitRequests}
     *  */
    //TODO:Change the BaseURL.
    private static final String BaseURL = "https://picsum.photos/"; // This BaseURL the start line of the interface endpoints

    private RetrofitSettings(){}


    /** This responsible for creating a retrofit instance,
     *  and notice how we use the help of {@see {getClient()}
     * */
    public static synchronized Retrofit getInstance(){
        if (retrofit == null) {
            // Set Proper Settings, Headers ,etc..
            OkHttpClient client = getClient();
            // .. actual creation.
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // This intercept each request and add to it creation settings.
    private static OkHttpClient getClient(){
        OkHttpClient client;
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();

                        builder.addHeader("Accept","application/json");
                        builder.addHeader("Content-Type","application/json");

                        // --THIS IS FOR INJECTING THE TOKEN
                        // builder.addHeader("Authorization", AppSharedPreferences.getInstance(AppController.getInstance()).getToken());

                        return chain.proceed(builder.build());
                    }
                }).build();
        return client;
    }
}
