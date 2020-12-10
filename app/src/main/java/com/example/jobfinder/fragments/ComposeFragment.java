package com.example.jobfinder.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jobfinder.MainActivity;
import com.example.jobfinder.Post;
import com.example.jobfinder.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {ComposeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComposeFragment extends Fragment {

    public static final String TAG = "ComposeFragment";

    private EditText etDescription;
    private Button btnSubmit;

    ProgressBar progressBar;

    public ComposeFragment() {
        // Required empty public constructor
    }


//    @Override
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;

    //    private String mParam2;
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment composeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ComposeFragment newInstance(String param1, String param2) {
//        ComposeFragment fragment = new ComposeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
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
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.pbLoading);
        etDescription = view.findViewById(R.id.etDescription);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(getContext(), "Description can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //get user if description not empty
                ParseUser currentUser = ParseUser.getCurrentUser();
                progressBar.setVisibility(ProgressBar.VISIBLE);

                //save user's post
                savePost(description, currentUser);

                //go back to posts fragment
//                Navigation.findNavController(view).navigate(R.id.postsView);

//                findNavController().navigate(R.id.fragmentPosts);

                Fragment fragmentCompose = new ComposeFragment();
                Fragment fragmentPost = new PostsFragment();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.postsView, getTargetFragment())
                        .commit();
            }
        });
    }

    // this will be needed if Post contain picture or files
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void savePost(String description, ParseUser currentUser) {
        ParseObject post = ParseObject.create(Post.class);
        post.put(Post.KEY_DESCRIPTION, description);
        post.put(Post.KEY_USER, currentUser);

        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Error while saving post", e);
                    Toast.makeText(getContext(), "Error while saving post!", Toast.LENGTH_SHORT).show();
                } else {
                    // when everything succeeded
                    Log.i(TAG, "Post save was successful!");
                    etDescription.setText("");
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(getContext(), "Post saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}