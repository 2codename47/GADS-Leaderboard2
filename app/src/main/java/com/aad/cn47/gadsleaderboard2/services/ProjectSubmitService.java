package com.aad.cn47.gadsleaderboard2.services;

import com.aad.cn47.gadsleaderboard2.models.SubmitProject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProjectSubmitService {
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<SubmitProject> createSubmission(
            @Field("entry.1877115667")String first_name,
            @Field("entry.2006916086")String last_name,
            @Field("entry.1824927963")String email,
            @Field("entry.284483984")String project_link
    );
}
