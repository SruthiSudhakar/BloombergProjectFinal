package com.example.meena.bloombergprojectfinal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by meena on 6/2/2017.
 */

public class GraphGoal extends Fragment {
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> goalSeries;

    ArrayList<String> dates= new ArrayList<>();
    int dataPtNum=0;
    GraphView graph;
    DecimalFormat df;
    TextView textView;
    TextView textDistance;
    TextView textDate1;
    public GraphGoal(LineGraphSeries<DataPoint> series,LineGraphSeries<DataPoint> goalSeries){
        this.series=series;
        this.goalSeries=goalSeries;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4, container, false);
        Log.d("GrapgGoal activ","add");

        textView= (TextView) rootView.findViewById(R.id.textView3);
        textDistance= (TextView) rootView.findViewById(R.id.textDistance);
        textDate1= (TextView) rootView.findViewById(R.id.textDate1);

        graph= (GraphView) rootView.findViewById(R.id.graphView);
        df= new DecimalFormat("#.####");
        graph.setTitle("Goal Comparison Graph");
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.setTitleColor(Color.MAGENTA);
        dates=getArguments().getStringArrayList("Dates");
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                textView.setText("Entry #"+((int)dataPoint.getX()));
                textDistance.setText("Distance: "+(df.format(dataPoint.getY()))+"mi");
                textDate1.setText("Date: "+dates.get((int)dataPoint.getX()-1).substring(0,10));
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

       /* graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1);*/
        //graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        //graph.getViewport().setYAxisBoundsStatus(Viewport.AxisBoundsStatus.AUTO_ADJUSTED);
        graph.getViewport().setXAxisBoundsStatus(Viewport.AxisBoundsStatus.AUTO_ADJUSTED);
        graph.addSeries(goalSeries);
        goalSeries.setColor(Color.RED);
        return rootView;
    }}
