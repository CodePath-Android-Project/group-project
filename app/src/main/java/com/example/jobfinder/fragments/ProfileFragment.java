package com.example.jobfinder.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jobfinder.EndlessRecyclerViewScrollListener;
import com.example.jobfinder.Post;
import com.example.jobfinder.PostAdapter;
import com.example.jobfinder.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProfileFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    private TextView userName;
    private TextView userAbout;
    private ImageView profilePhoto;

    private RecyclerView rvItems;
    private EndlessRecyclerViewScrollListener scrollListener;
    private RecyclerView rvPosts;
    private PostAdapter adapter;
    private List<Post> allPosts;

    public SwipeRefreshLayout swipeContainer;
    public ScrollView scrollView;


    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeContainer = view.findViewById(R.id.swipeContainer);
        //configure colors refreshing on page refresh
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //set up refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Fetching new data");
                queryUserPosts();
            }
        });

        rvItems = view.findViewById(R.id.rvItems);
        rvItems.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        //create the adapter
        allPosts = new ArrayList<>();
        adapter = new PostAdapter(getContext(), allPosts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvItems.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvItems.setLayoutManager(layoutManager);

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        rvItems.addOnScrollListener(scrollListener);

        view.findViewById(R.id.fabLogout).setVisibility(View.VISIBLE);
        //log out user
        view.findViewById(R.id.fabLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                getActivity().finish();
            }
        });

        userName = view.findViewById(R.id.tvUserName);
        userAbout = view.findViewById(R.id.tvUserAbout);
        profilePhoto = view.findViewById(R.id.ivProfileImage);


        ParseUser currentUser = ParseUser.getCurrentUser();
        userName.setText(currentUser.getUsername());
        userAbout.setText(currentUser.getUsername() + "' about goes here(to be coded)");

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        queryUserPosts();

    }

    private void loadNextDataFromApi(int page) {
    }


    private void queryUserPosts() {
        //clear old data before retrieving new ones
        allPosts.clear();

        // Specify the class to query, here Post
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        //include all information about the post
        query.include(Post.KEY_USER);
//        query.include(Post.KEY_CREATED_AT);

        //show post of current user only
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        //set limit of post that we ant to retrieve on app launch
        query.setLimit(20);

        // get the most recently created post at the top of the screen
        query.addDescendingOrder(Post.KEY_CREATED_AT);

        //retrieve all the posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                //if everything succeeded, iterate through all the post
//                for(ParseObject post : posts) {
//                    Log.i(TAG, "Post: " + post.get() + " - Username: " + post.getUser().getUsername());
//                }

                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false); // refresh is done
            }
        });
    }

    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static ProfileFragment newInstance(String param1, String param2) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
}