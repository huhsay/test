package com.bethejustice.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.bethejustice.test.api.GithubApi;
import com.bethejustice.test.api.GithubApiProvider;
import com.bethejustice.test.api.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Call<List<GithubRepo>> repoCall;
    GithubApi api;
    RecyclerView recyclerView;
    RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = GithubApiProvider.provideGithubApi(this);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RepositoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        Uri data = this.getIntent().getData();
        if (data != null && data.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            if(uri.startsWith("testapp://repos/")){
                String param = uri.replace("testapp://repos/", "");

                repoCall = api.getRepository(param);
                repoCall.enqueue(new Callback<List<GithubRepo>>() {
                    @Override
                    public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                        Log.d(TAG, "onResponse: " + response.body().get(1).owner.name);
                        adapter.setItems(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

                    }
                });
            }
        }
    }
}
