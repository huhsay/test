package com.bethejustice.test.api;

import com.google.gson.annotations.SerializedName;

public class GithubRepo {

    public final String name;

    @SerializedName("full_name")
    public final String fullName;
    public final GithubOwner owner;
    @SerializedName("description")
    public final String description;
    @SerializedName("stargazer_count")
    public final int stargazersCount;

    public GithubRepo(String name, String fullName, GithubOwner owner, String descriptions, int stars) {
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.description = descriptions;
        this.stargazersCount = stars;
    }
}
