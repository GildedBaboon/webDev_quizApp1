package com.example.webdev_quizapp1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Results extends Fragment {
    private static final String TAG = "Results";

    private Button btnNavHome;
    private Button updateScores;

    private TextView totalRight;
    private TextView totalWrong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_layout, container, false);
        btnNavHome = view.findViewById(R.id.btnNavHome);
        updateScores = view.findViewById(R.id.updateScores);
        totalRight = view.findViewById(R.id.totalRight);
        totalWrong = view.findViewById(R.id.totalWrong);
        Log.i(TAG, "onCreateView: Started.");

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("quizResults", 0); // 0 - for private mode
        totalRight.setText("Total Right: "+pref.getInt("correct", 0));
        totalWrong.setText("Total Wrong: "+pref.getInt("wrong", 0));

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Home Page", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        updateScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("quizResults", 0); // 0 - for private mode
                totalRight.setText("Total Right: "+pref.getInt("correct", 0));
                totalWrong.setText("Total Wrong: "+pref.getInt("wrong", 0));
                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
