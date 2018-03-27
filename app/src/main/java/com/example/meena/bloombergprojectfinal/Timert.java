package com.example.meena.bloombergprojectfinal;

/**
 * Created by meena on 5/28/2017.
 */

import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class Timert extends Fragment {
    TextView textView;
    Chronometer chronometer;
    Button stop_id;
    Button start_id;
    Button pause_id;
    long timeStopped=0;
    int x=0;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);
        chronometer= (Chronometer) rootView.findViewById(R.id.chronometer);
        stop_id= (Button) rootView.findViewById(R.id.stop_id);
        start_id= (Button) rootView.findViewById(R.id.start_id);
        pause_id= (Button) rootView.findViewById(R.id.pause_id);
        stop_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeStopped=0;
                chronometer.stop();
            }
        });
        start_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime()+timeStopped);
                chronometer.start();
            }
        });
        pause_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeStopped= chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
            }
        });
        return rootView;
    }
}
