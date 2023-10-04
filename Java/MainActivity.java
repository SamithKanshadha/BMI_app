package com.example.bmiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String category,color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate_btn = (Button) findViewById(R.id.calculate_btn);
        EditText age = (EditText) findViewById(R.id.age);
        EditText height = (EditText) findViewById(R.id.height);
        EditText weight = (EditText) findViewById(R.id.weight);

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (age.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Double height_value = Double.parseDouble(height.getText().toString());
                    Double mass_value = Double.parseDouble(weight.getText().toString());

                    if(Integer.parseInt(age.getText().toString()) < 18)
                    {
                        Toast.makeText(getApplicationContext(),"Valid only for 18+ Age", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        double h_meter = height_value/100; // convert height value cm to m
                        double bmi = mass_value/(h_meter*h_meter);
                        double round = Math.round(bmi);

                        SetCategory(round);
                        String result = String.valueOf(round);

                        Intent sendValues = new Intent(MainActivity.this, SecondActivity.class);
                        sendValues.putExtra("Category", category);
                        sendValues.putExtra("Result", result);
                        sendValues.putExtra("Color", color);

                        startActivity(sendValues);

                    }
                }

            }

            public void SetCategory(Double result){

                if(result < 16)
                {
                    category = "Severe Thinness";
                    color ="#b30000";
                }
                else if(16 <= result && result < 17)
                {
                    category = "Moderate Thinness";
                    color ="#990000";
                }
                else if(17 <= result && result < 18.5)
                {
                    category = "Mild Thinness";
                    color ="#ffd11a";
                }
                else if(18.5 <= result && result < 25)
                {
                    category = "Normal";
                    color ="#33cc33";
                }
                else if(25 <= result && result < 30)
                {
                    category = "Overweight";
                    color ="#ffcc00";
                }
                else if(30 <= result && result < 35)
                {
                    category = "Obese Class I";
                    color ="#d14747";
                }
                else if(35 <= result && result < 40)
                {
                    category = "Obese Class II";
                    color ="#b82e2e";
                }
                else
                {
                    category = "Obese Class III";
                    color ="#661919";
                }

            }

        });

    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit from the app?")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}