package com.example.asus.ecgraph;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private String rawData;
    private LineChart chart;
    private List<Entry> entries;
    private ArrayList<Float> values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }




    private void initialize(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        chart = (LineChart) findViewById(R.id.chart);
        chart.setNoDataText("Loading...");
        chart.setNoDataTextColor(R.color.colorPrimary);



        entries = new ArrayList<>();
        values = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rawData = dataSnapshot.getValue(String.class);
                createChart();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    private void createChart(){
        float xValue = 0;
        for (String s : rawData.split(" ")){
            entries.add(new Entry(xValue, Float.parseFloat(s)));
            xValue += 1.2;
        }

        LineDataSet dataSet = new LineDataSet(entries, "dipto's chart");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }
}
