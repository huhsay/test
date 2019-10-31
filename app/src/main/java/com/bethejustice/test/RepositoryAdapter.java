package com.bethejustice.test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bethejustice.test.model.GithubOwner;
import com.bethejustice.test.model.GithubRepo;
import com.bethejustice.test.model.RepositoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_USER = 0;

    private static final String TAG = "RepositoryAdapter";
    private List<RepositoryModel> items = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_USER) {
            return new UserViewHolder(parent);
        } else {
            return new RepositoryHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == TYPE_USER) {
            GithubOwner owner = (GithubOwner) items.get(position);

            if (owner == null) return;
            if (owner.login.length() == 0) return;

            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.textView.setText(owner.login);
            Glide.with(userViewHolder.itemView.getContext())
                    .load(owner.avatarUrl)
                    .into(userViewHolder.imageView);
        } else {
            GithubRepo repo = (GithubRepo) items.get(position);

            if (repo == null) return;
            if (repo.fullName.length() == 0 || repo.description.length() == 0) return;

            RepositoryHolder repositoryHolder = (RepositoryHolder) holder;
            repositoryHolder.tvName.setText(repo.fullName);
            repositoryHolder.tvDescription.setText(repo.description);
            repositoryHolder.tvStarCount.setText(Integer.toString(repo.stargazersCount));
            Log.d(TAG, "onBindViewHolder: " + repo.fullName);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    void setRepositoryItems(@NonNull List<GithubRepo> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    void setUserItem(@NonNull GithubOwner item) {
        this.items.add(item);
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

    static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        UserViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user, parent, false));

            imageView = itemView.findViewById(R.id.iv_avatar);
            textView = itemView.findViewById(R.id.tv_name);
        }
    }
}