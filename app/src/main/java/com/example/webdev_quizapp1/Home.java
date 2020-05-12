package com.example.webdev_quizapp1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Home extends Fragment {
    private static final String TAG = "Home";

    private Button btnNavQuiz;
    private Button btnNavResults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        btnNavQuiz = view.findViewById(R.id.btnNavQuiz);
        btnNavResults = view.findViewById(R.id.btnNavResults);
        Log.i(TAG, "onCreateView: Started.");

        btnNavQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Continuing Quiz", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnNavResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Viewing Results", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        return view;
    }
}
