package com.example.meena.bloombergprojectfinal;

/**
 * Created by meena on 5/28/2017.
 */
import android.app.Activity;
import android.graphics.Color;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GraphData extends Fragment  {
    LineGraphSeries<DataPoint> series;
    ArrayList<String> dates= new ArrayList<>();
    int dataPtNum=0;
    GraphView graph;
    DecimalFormat df;
    TextView textView;
    TextView textViewSpeed;
    TextView textViewDate;
    public GraphData(LineGraphSeries<DataPoint> series){
        this.series=series;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        Log.d("Graphdata","run");
        textView= (TextView) rootView.findViewById(R.id.textView);
        textViewDate= (TextView) rootView.findViewById(R.id.textViewDate);
        textViewSpeed= (TextView) rootView.findViewById(R.id.textViewSpeed);

        graph= (GraphView) rootView.findViewById(R.id.graph);
        df= new DecimalFormat("#.####");
        graph.setTitle("Speed Graph");
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.setTitleColor(Color.MAGENTA);
        dates=getArguments().getStringArrayList("Dates");
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                textView.setText("Entry #"+((int)dataPoint.getX()));
                textViewSpeed.setText("Speed: "+(df.format(dataPoint.getY()))+"mi/min ");
                textViewDate.setText("Date: "+dates.get((int)dataPoint.getX()-1).substring(0,10));
            }
        });
        series.setDrawDataPoints(true);


        graph.addSeries(series);


        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);


        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(50);

        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);
        //graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        //graph.getViewport().setYAxisBoundsStatus(Viewport.AxisBoundsStatus.AUTO_ADJUSTED);
        graph.getViewport().setXAxisBoundsStatus(Viewport.AxisBoundsStatus.AUTO_ADJUSTED);
        Log.d("TAG",graph.getViewport().getMaxX(true)+" "+graph.getViewport().getMaxXAxisSize()+" "+graph.getViewport().getXAxisBoundsStatus());
        return rootView;
    }
}


