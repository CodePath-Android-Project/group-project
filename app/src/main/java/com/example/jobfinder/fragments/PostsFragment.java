package com.example.jobfinder.fragments;

import android.graphics.drawable.Drawable;
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
import android.widget.AdapterView;

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

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PostsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class PostsFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "PostsFragment";
    public SwipeRefreshLayout swipeContainer;

    private EndlessRecyclerViewScrollListener scrollListener;

    private RecyclerView rvPosts;
    private PostAdapter adapter;
    private List<Post> allPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


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
        return inflater.inflate(R.layout.fragment_posts, container, false);
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
                queryPosts();
            }
        });

        rvPosts = view.findViewById(R.id.rvPosts);
        rvPosts.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        //create the adapter
        allPosts = new ArrayList<>();
        adapter = new PostAdapter(getContext(), allPosts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //set adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //use adapter on recyclerview
        rvPosts.setLayoutManager(layoutManager);

        //call resetState() for fresh searches by retaining an instance
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //this only trigger when new data needs to be append to the list
                //add here whatever code needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };

        //add the scroll listener to the recyclerview
        rvPosts.addOnScrollListener(scrollListener);
        queryPosts();
    }

    protected void loadNextDataFromApi(int page) {
    }

    private void queryPosts() {
        //specify the class to query (Post in this case)
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        //include all needed info to query
        query.include(Post.KEY_USER);
//        query.include(Post.KEY_DESCRIPTION);
        //limit of post that will be retrieved on app launch
        query.setLimit(20);
        //get most recently posts first
        query.addDescendingOrder(Post.KEY_CREATED_AT);


        //retrieve all posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issues with getting posts", e);
                    return;
                }
                //when post retrieve succeed
                for (ParseObject post : posts) {
                    ParseUser user = (ParseUser) post.get(Post.KEY_USER);
                    Log.i(TAG, "Post: " + post.get(Post.KEY_DESCRIPTION) + " - username: " + user.getUsername());
                }
                allPosts.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }


    //    // TODO: Rename and change types of parameters
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
//     * @return A new instance of fragment PostsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PostsFragment newInstance(String param1, String param2) {
//        PostsFragment fragment = new PostsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
}