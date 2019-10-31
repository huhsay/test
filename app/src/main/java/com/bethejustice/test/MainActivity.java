package com.bethejustice.test;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bethejustice.test.api.GithubApi;
import com.bethejustice.test.api.GithubApiProvider;
import com.bethejustice.test.model.GithubRepo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Call<List<GithubRepo>> repoCall;
    GithubApi api;
    RecyclerView recyclerView;
    RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = GithubApiProvider.provideGithubApi();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RepositoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Uri data = this.getIntent().getData();
        if (data != null && data.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            assert uri != null;
            if (uri.startsWith("testapp://repos/")) {
                String param = uri.replace("testapp://repos/", "");

                repoCall = api.getRepository(param);
                repoCall.enqueue(new Callback<List<GithubRepo>>() {
                    @Override
                    public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                        assert response.body() != null;
                        adapter.setUserItem(response.body().get(0).owner);
                        List<GithubRepo> repos = response.body();
                        Collections.sort(repos, new Comparator<GithubRepo>() {
                            @Override
                            public int compare(GithubRepo o1, GithubRepo o2) {
                                return o2.stargazersCount - o1.stargazersCount;
                            }
                        });
                        adapter.setRepositoryItems(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
