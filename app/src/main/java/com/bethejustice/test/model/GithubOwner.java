package com.bethejustice.test.model;

import com.google.gson.annotations.SerializedName;

public class GithubOwner implements RepositoryModel {

    public final String login;

    @SerializedName("avatar_url")
    public final String avatarUrl;

    public GithubOwner(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int getType() {
        return 0;
    }
}
