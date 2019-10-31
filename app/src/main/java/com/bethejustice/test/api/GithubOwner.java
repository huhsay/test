package com.bethejustice.test.api;

import com.google.gson.annotations.SerializedName;

public class GithubOwner {

    public final String name;

    @SerializedName("avatar_url")
    public final String avatarUrl;

    public GithubOwner(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }
}
