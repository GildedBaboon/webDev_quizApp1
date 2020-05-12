package com.example.webdev_quizapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PageAdapter mPageAdapter;
    private ViewPager2 mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: Started.");

        mPageAdapter = new PageAdapter(this);
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager2 viewPager2) {
        PageAdapter adapter = new PageAdapter(this);
        adapter.addFragment(new Home(), "Home");
        adapter.addFragment(new Quiz(), "Results");
        adapter.addFragment(new Results(), "Results");
        viewPager2.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
