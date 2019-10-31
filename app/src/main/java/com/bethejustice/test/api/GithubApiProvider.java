package com.bethejustice.test.api;

import android.content.Context;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApiProvider {

    public static GithubApi provideGithubApi(@NonNull Context context) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApi.class);
    }
}
