package com.example.bmiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView category = (TextView) findViewById(R.id.category);
        TextView result = (TextView) findViewById(R.id.result);

        Intent getValues = getIntent();
        String category_value = getValues.getStringExtra("Category");
        String result_value = getValues.getStringExtra("Result");
        String color_value = getValues.getStringExtra("Color");

        result.setText("BMI = " + result_value + " kg/m2");
        category.setText(category_value);
        category.setTextColor(Color.parseColor(color_value));
    }
}