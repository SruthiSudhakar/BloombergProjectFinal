package com.example.meena.bloombergprojectfinal;
    /*GraphView - open source graph plotting
    library for Android*/
import android.graphics.Color;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;

public class BloombergProjectFinal extends AppCompatActivity implements EnterInfo.CommunicationChannel {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    LineGraphSeries<DataPoint> series;
    ArrayList<Date> dates= new ArrayList<>();
    int dataPtNum=0;
    ArrayList<String> dates2= new ArrayList<>();
    LineGraphSeries<DataPoint> goalSeries;
    LineGraphSeries<DataPoint> distanceSeries;
    int x=0;
    View view;
    PopupWindow popupWindow;
    double goalpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloomberg_project_final);
        view= findViewById(R.id.main_content);
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(BloombergProjectFinal.this);
        LayoutInflater inflater= (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view2= inflater.inflate(R.layout.popupwindow,null);
        view2.setBackgroundColor(Color.LTGRAY);
        TextView say= (TextView) view2.findViewById(R.id.whattosay_id);
        say.setText("Enter The Distance (in miles) That You Aim to Run Each Day");
        Button closeButton= (Button) view2.findViewById(R.id.closepopup_id);
        final EditText goalNum= (EditText) view2.findViewById(R.id.goal_id);
        alertDialog.setView(view2);
        final AlertDialog dialog= alertDialog.create();
        dialog.show();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!goalNum.getText().toString().isEmpty()) {
                    goalpoint = Double.parseDouble(goalNum.getText().toString());
                    Log.d("goalpt", goalpoint + "");
                    dialog.dismiss();
                }

            }
        });
        series = new LineGraphSeries<>();
        goalSeries= new LineGraphSeries<>();
        distanceSeries= new LineGraphSeries<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Snackbar.make(view, "This app is for runner who would like to track their progress and view statistics of their accomplishments.", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
          }
      });
      Log.d("fabsize",fab.getSize()+"");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bloomberg_project_final, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.goal_id) {
            //DO HERE
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(BloombergProjectFinal.this);
            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View view2 = inflater.inflate(R.layout.popupwindow, null);
            view2.setBackgroundColor(Color.LTGRAY);
            TextView say = (TextView) view2.findViewById(R.id.whattosay_id);
            say.setText("Enter The Distance (in miles) That You Aim to Run Each Day");
            Button closeButton = (Button) view2.findViewById(R.id.closepopup_id);
            final EditText goalNum = (EditText) view2.findViewById(R.id.goal_id);
            alertDialog.setView(view2);
            final AlertDialog dialog = alertDialog.create();
            dialog.show();
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!goalNum.getText().toString().isEmpty())
                        goalpoint = Double.parseDouble(goalNum.getText().toString());
                    dialog.dismiss();


                }
            });
        }
        if (id==R.id.item){
            AlertDialog.Builder alertDialog1= new AlertDialog.Builder(BloombergProjectFinal.this);
            LayoutInflater inflater1= (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View view3= inflater1.inflate(R.layout.dialogwindow,null);
            view3.setBackgroundColor(Color.LTGRAY);;
            Button closeButton1= (Button) view3.findViewById(R.id.close);
            alertDialog1.setView(view3);
            final AlertDialog dialog1= alertDialog1.create();
            dialog1.show();
            closeButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog1.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void sendInfo(Double speed,Double distance, Date date) {
        while (x<=1) {
            if (x==0)
            dataPtNum++;
            dates.add(date);
            for (int x = 0; x < dates.size(); x++)
                dates2.add(dates.get(x).toString());
            series.appendData(new DataPoint(dataPtNum, speed), true, 100);
            goalSeries.appendData(new DataPoint(dataPtNum, goalpoint), true, 100);
            distanceSeries.appendData(new DataPoint(dataPtNum, distance), true, 100);
            GraphData graphData = new GraphData(series);
            Bundle args = new Bundle();
            args.putStringArrayList("Dates", dates2);
            graphData.setArguments(args);
            Log.d("called", "sendinfo called");
            GraphGoal graphGoal = new GraphGoal(distanceSeries, series);
            graphGoal.setArguments(args);
            x++;
        }
        x=0;
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: EnterInfo enterInfo= new EnterInfo();
                    return enterInfo;
                case 1: GraphData graphData= new GraphData(series);
                    Bundle args = new Bundle();
                    args.putStringArrayList("Dates", dates2);
                    graphData.setArguments(args);
                    Log.d("called","case1 called");
                    return graphData;
                case 2: Timert timert= new Timert();
                    return timert;
                case 3: GraphGoal graphGoal= new GraphGoal(distanceSeries,goalSeries);
                    Bundle args1 = new Bundle();
                    args1.putStringArrayList("Dates", dates2);
                    graphGoal.setArguments(args1);
                    return graphGoal;
                default: return new Fragment();
            }
            //return new Fragment();
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Enter Distance and Time";
                case 1:
                    return "Track Progress";
                case 2:
                    return "Stopwatch";
                case 3:
                    return "Goal Graph";
            }
            return null;
        }
    }


}
