package com.aad.cn47.gadsleaderboard2.services;

import com.aad.cn47.gadsleaderboard2.models.Skilliq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkilliqService {
    @GET("skilliq")
    Call<List<Skilliq>> getSkilliqs();
}
