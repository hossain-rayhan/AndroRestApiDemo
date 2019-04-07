package com.rayapplica.androrestapidemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.rayapplica.androrestapidemo.R;
import com.rayapplica.androrestapidemo.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;

    public UserRVAdapter(Context context, List<User> userList) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_rv_custom_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tvFirstName.setText(userList.get(position).getFirstName());
        holder.tvLastName.setText(userList.get(position).getLastName());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(userList.get(position).getPhotoURL())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tvFirstName;
        TextView tvLastName;
        private ImageView ivPhoto;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            tvFirstName = mView.findViewById(R.id.firstName);
            tvLastName = mView.findViewById(R.id.lastName);
            ivPhoto = mView.findViewById(R.id.photo);
        }
    }
}
