package com.aad.cn47.gadsleaderboard2.services;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com/api/";
    private static final String URL2 = "https://docs.google.com/forms/d/e/";

    // Create Logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp Client
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            //.readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request request = chain.request();

                    request = request.newBuilder()
                            .addHeader("x-device-type", Build.DEVICE)
                            .addHeader("accept-language", Locale.getDefault().getLanguage())
                            .build();

                    return chain.proceed(request);
                }
            })
            .addInterceptor(logger);

    // For First URL
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }

    // For Second URL
    private static Retrofit.Builder builder2 = new Retrofit.Builder().baseUrl(URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit2 = builder2.build();

    public static <S> S buildService2(Class<S> serviceType){
        return retrofit2.create(serviceType);
    }
}
