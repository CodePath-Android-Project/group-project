package com.example.jobfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvCreatedAt;
    public String fullTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        //retrieve data
        Post post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvCreatedAt.setText(getFullTimestamp(post)); // this gives a short time, e.g: 7h ago
//        tvCreatedAt.setText(post.getFullTimestamp()); // this gives a full date, e.g: Tue. 27/10
    }

    public String getFullTimestamp(Post post) {
        fullTime = TimeFormatter.getTimeStamp(post.getCreatedAt().toString());
        return fullTime;
    }
}