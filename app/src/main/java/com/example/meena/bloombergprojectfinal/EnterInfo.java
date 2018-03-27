package com.example.meena.bloombergprojectfinal;

/**
 * Created by meena on 5/28/2017.
 */

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class EnterInfo extends Fragment {
    EditText distance_id;
    EditText time_id;
    Button send;
    Button graph;
    CommunicationChannel mCallback;
    public interface CommunicationChannel{
        void sendInfo(Double speed, Double distance, Date date);
        //void graphIt();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (CommunicationChannel) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        distance_id= (EditText) rootView.findViewById(R.id.distance_id);
        time_id= (EditText) rootView.findViewById(R.id.time_id);
        send= (Button) rootView.findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!distance_id.getText().toString().isEmpty())||(!time_id.getText().toString().isEmpty()))
                    mCallback.sendInfo(Double.parseDouble(String.valueOf(distance_id.getText()))/Double.parseDouble(String.valueOf(time_id.getText())), Double.parseDouble(String.valueOf(distance_id.getText())),Calendar.getInstance().getTime());
            }
        });
        return rootView;
    }
}
