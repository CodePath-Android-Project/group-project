package com.example.jobfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        ParseObject post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        private TextView tvSponsor;
        public String formattedTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvSponsor = itemView.findViewById(R.id.tvSponsor);
        }

        public void bind(ParseObject post) {
            //Bind the post data to the view elements
            ParseUser user = (ParseUser) post.get(Post.KEY_USER);
            String s = post.get(Post.KEY_DESCRIPTION).toString();
            String visa = post.get(Post.KEY_COMPANY_SPONSOR).toString();
            tvUserName.setText(post.get(Post.KEY_COMPANY_NAME).toString());
            tvDescription.setText(s);
            tvSponsor.setText(visa);
            tvCreatedAt.setText(getFormattedTimestamp(post));

            //set on click listeners to access post details
            tvDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.detailScreen);
                }
            });
        }

        public String getFormattedTimestamp(ParseObject post) {
            formattedTime = TimeFormatter.getTimeDifference(post.getCreatedAt().toString());
            return formattedTime;
        }


    }
}
