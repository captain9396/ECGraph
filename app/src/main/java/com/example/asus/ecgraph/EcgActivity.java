package com.example.asus.ecgraph;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class EcgActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private String rawData;
    private LineChart chart;
    private List<Entry> entries;
    private ProgressBar progressBar;
    private TextView ecgTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_1);

        initialize();

    }




    private void initialize(){
        database = FirebaseDatabase.getInstance();
        String MACHINE_ID = getIntent().getStringExtra("ECG_MACHINE_ID");
        myRef = database.getReference(MACHINE_ID);
        chart = (LineChart) findViewById(R.id.chart);
        progressBar = (ProgressBar) findViewById(R.id.progress_ecg1);
        ecgTextView = (TextView) findViewById(R.id.ecg_1_text);
        chart.setNoDataText("");
//        chart.setNoDataTextColor(R.color.colorPrimary);


//        if (MACHINE_ID == "ecg1")
        ecgTextView.setText(MACHINE_ID);
//        else ecgTextView.setText("ECG 2");

        entries = new ArrayList<>();
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
        entries.clear();
        for (String s : rawData.split(" ")){
            entries.add(new Entry(xValue, Float.parseFloat(s)));
            xValue += 1.2;
        }

        LineDataSet dataSet = new LineDataSet(entries, "dipto's chart");
        dataSet.setColor(Color.rgb(1, 1, 1));
        dataSet.setDrawValues(false);
        dataSet.setDrawCircles(false);
        LineData lineData = new LineData(dataSet);
        chart.clear();
        chart.setData(lineData);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setVisibleXRangeMaximum(100);
        chart.invalidate();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
