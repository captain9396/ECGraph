package com.example.asus.ecgraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button ecg1Button;
    private Button ecg2Button;
    private Button petButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ecg1Button = (Button) findViewById(R.id.button_ecg1);
        ecg2Button = (Button) findViewById(R.id.button_ecg2);
        petButton = (Button) findViewById(R.id.button_pet);


        ecg1Button.setOnClickListener(this);
        ecg2Button.setOnClickListener(this);
        petButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == ecg1Button) switchActivity(Constants.ECG_MACHINE_ID_1);
        else if( view == ecg2Button) switchActivity(Constants.ECG_MACHINE_ID_2);
//        else if (view == petButton) switchActivity("pet");
    }

    private void switchActivity(String activityname){
        Intent myIntent = new Intent(this, EcgActivity.class);
        if (activityname == Constants.ECG_MACHINE_ID_1)
            myIntent.putExtra("ECG_MACHINE_ID", Constants.ECG_MACHINE_ID_1);
        else if (activityname == Constants.ECG_MACHINE_ID_2)
            myIntent.putExtra("ECG_MACHINE_ID", Constants.ECG_MACHINE_ID_2);

//        else if (activityname == "pet")
//            myIntent = new Intent(this, activities[2]);
        startActivity(myIntent);
    }
}
