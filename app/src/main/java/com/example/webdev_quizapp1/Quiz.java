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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz extends Fragment {
    private static final String TAG = "Quiz";

    public static TextView question;
    public static TextView totalRight;
    public static Button a1;
    public static Button a2;
    public static Button a3;
    public static Button a4;

    private RequestQueue requestQueue;
    private int correctAns;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_layout, container, false);
        question = view.findViewById(R.id.tvQuestion);
        totalRight = view.findViewById(R.id.tvRight);
        a1 = view.findViewById(R.id.btnAnswer1);
        a2 = view.findViewById(R.id.btnAnswer2);
        a3 = view.findViewById(R.id.btnAnswer3);
        a4 = view.findViewById(R.id.btnAnswer4);

        final SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("quizResults", 0);
        final SharedPreferences.Editor editor = pref.edit();

        showNewProblem();
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = pref.getInt("correct", 0);
                int wrong  = pref.getInt("wrong", 0);
                if(correctAns == 0)
                {
                    correct++;
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    totalRight.setText("Total Correct: " + correct);
                }
                else{
                    wrong++;
                    Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
                }
                editor.putInt("correct", correct);
                editor.putInt("wrong", wrong);
                editor.commit();
                showNewProblem();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = pref.getInt("correct", 0);
                int wrong  = pref.getInt("wrong", 0);
                if(correctAns == 1)
                {
                    correct++;
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    totalRight.setText("Total Correct: " + correct);
                }
                else{
                    wrong++;
                    Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
                }
                editor.putInt("correct", correct);
                editor.putInt("wrong", wrong);
                editor.commit();
                showNewProblem();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = pref.getInt("correct", 0);
                int wrong  = pref.getInt("wrong", 0);
                if(correctAns == 2)
                {
                    correct++;
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    totalRight.setText("Total Correct: " + correct);
                }
                else{
                    wrong++;
                    Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
                }
                editor.putInt("correct", correct);
                editor.putInt("wrong", wrong);
                editor.commit();
                showNewProblem();
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = pref.getInt("correct", 0);
                int wrong  = pref.getInt("wrong", 0);
                if(correctAns == 3)
                {
                    correct++;
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    totalRight.setText("Total Correct: " + correct);
                }
                else{
                    wrong++;
                    Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
                }
                editor.putInt("correct", correct);
                editor.putInt("wrong", wrong);
                editor.commit();
                showNewProblem();
            }
        });

        return view;
    }

    private void showNewProblem() {
        requestQueue = Volley.newRequestQueue((MainActivity)getActivity());
        jsonParse();
        final SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("quizResults", 0);
        int correct = pref.getInt("correct", 0);
        totalRight.setText("Total Correct: " + correct);
    }

    private void jsonParse() {
        String url = "https://opentdb.com/api.php?amount=1&category=9&type=multiple";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    JSONObject result = jsonArray.getJSONObject(0);
                    String tempQ = result.getString("question");
                    tempQ.replaceAll("&amp", "&");
                    tempQ.replaceAll("&quot", "\"");
                    tempQ.replaceAll(";quot", "\"");
                    tempQ.replaceAll("''amp", "&");
                    question.setText(tempQ);
                    String[] answers = new String[4];
                    answers[0] = result.getString("correct_answer");
                    answers[0].replaceAll("&amp", "&");
                    answers[0].replaceAll("&quot", "\"");
                    answers[0].replaceAll(";quot", "\"");
                    answers[0].replaceAll("''amp", "&");
                    JSONArray incorrectAns = result.getJSONArray("incorrect_answers");
                    answers[1] = org.apache.commons.text.StringEscapeUtils.escapeXml10(incorrectAns.getString(0));
                    answers[2] = org.apache.commons.text.StringEscapeUtils.escapeXml10(incorrectAns.getString(1));
                    answers[3] = org.apache.commons.text.StringEscapeUtils.escapeXml10(incorrectAns.getString(2));

                    answers[1].replaceAll("&amp", "&");
                    answers[1].replaceAll("&quot", "\"");
                    answers[1].replaceAll(";quot", "\"");
                    answers[1].replaceAll("''amp", "&");

                    answers[2].replaceAll("&amp", "&");
                    answers[2].replaceAll("&quot", "\"");
                    answers[2].replaceAll(";quot", "\"");
                    answers[2].replaceAll("''amp", "&");

                    answers[3].replaceAll("&amp", "&");
                    answers[3].replaceAll("&quot", "\"");
                    answers[3].replaceAll(";quot", "\"");
                    answers[3].replaceAll("''amp", "&");
                    Log.d(TAG, "onResponse: PARSED Correct Answer: "+answers[0]);
                    String stringCorrect = answers[0];
                    List<String> arrayList = Arrays.asList(answers);
                    Collections.shuffle(arrayList);
                    arrayList.toArray(answers);
                    a1.setText(answers[0]);
                    a2.setText(answers[1]);
                    a3.setText(answers[2]);
                    a4.setText(answers[3]);
                    correctAns = Arrays.asList(answers).indexOf(stringCorrect);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

}
