package com.bethejustice.test.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("users/{owner}/repos")
    Call<List<GithubRepo>> getRepository(@Path("owner") String owner);
}
