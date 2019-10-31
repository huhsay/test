package com.bethejustice.test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bethejustice.test.api.GithubRepo;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder> {

    private final int TYPE_USER = 0;
    private final int TYPE_ITEM = 1;

    private static final String TAG = "RepositoryAdapter";
    private List<GithubRepo> items = new ArrayList<>();
    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryHolder(parent);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        final GithubRepo repo = items.get(position);

        holder.tvName.setText(repo.fullName);
        holder.tvDescription.setText(repo.description);
        holder.tvStarCount.setText(Integer.toString(repo.stargazersCount));
        Log.d(TAG, "onBindViewHolder: " + repo.fullName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<GithubRepo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class RepositoryHolder extends RecyclerView.ViewHolder {

        TextView tvStarCount;
        TextView tvName;
        TextView tvDescription;

        RepositoryHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_repository, parent, false));

            tvStarCount = itemView.findViewById(R.id.tv_star);
            tvName = itemView.findViewById(R.id.tv_reponame);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}