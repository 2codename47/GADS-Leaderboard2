package com.aad.cn47.gadsleaderboard2.services;

import com.aad.cn47.gadsleaderboard2.models.Hour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HourService {
    @GET("hours")
    Call<List<Hour>> getHours();
}
