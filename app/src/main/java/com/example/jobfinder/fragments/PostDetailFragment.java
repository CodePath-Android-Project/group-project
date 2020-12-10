package com.example.jobfinder.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobfinder.Post;
import com.example.jobfinder.R;
import com.example.jobfinder.TimeFormatter;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PostDetailFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class PostDetailFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvCreatedAt;
    public String fullTime;

    public PostDetailFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment PostDetailFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PostDetailFragment newInstance(String param1, String param2) {
//        PostDetailFragment fragment = new PostDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvCreatedAt = view.findViewById(R.id.tvCreatedAt);

        //retrieve data
//        ParseObject post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
//        ParseUser user = (ParseUser) post.get(Post.KEY_USER);
//        tvUsername.setText(user.getUsername());
//        tvDescription.setText(post.get(Post.KEY_DESCRIPTION).toString());
//        tvCreatedAt.setText(getFullTimestamp(post)); // this gives a short time, e.g: 7h ago
//        tvCreatedAt.setText(post.getFullTimestamp()); // this gives a full date, e.g: Tue. 27/10

    }

    public String getFullTimestamp(ParseObject post) {
        fullTime = TimeFormatter.getTimeStamp(post.getCreatedAt().toString());
        return fullTime;
    }
}