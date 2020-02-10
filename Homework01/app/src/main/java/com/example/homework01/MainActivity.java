package com.example.homework01;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_BMI;
    private TextView tv_weight;
    private EditText et_weight;
    private TextView tv_height;
    private EditText et_feet;
    private EditText et_inches;
    private Button calculateBMI;
    private TextView tv_calculated_BMI;
    private TextView tv_status;

    double totalHeight;
    double totalWeight;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");

        et_weight = findViewById(R.id.et_weight);
        et_feet = findViewById(R.id.et_feet);
        et_inches = findViewById(R.id.et_inches);
        calculateBMI = findViewById(R.id.btn_calculateBMI);
        tv_calculated_BMI = findViewById(R.id.tv_calculated_BMI);
        tv_status = findViewById(R.id.tv_status);

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String weight = et_weight.getText().toString();
                final String feet = et_feet.getText().toString();
                final String inches = et_inches.getText().toString();


                if (et_weight.getText().toString().equals("") || Double.parseDouble(weight)<= 0) {
                    et_weight.setError("Invalid Input!");
                    visiblity();
                } else if (et_feet.getText().toString().equals("") || Double.parseDouble(feet)== 0) {
                    et_feet.setError("Invalid Input!");
                    visiblity();
                } else if (et_inches.getText().toString().equals("") || Double.parseDouble(inches)>= 12) {
                    et_inches.setError("Invalid Input!");
                    visiblity();
                }else {
                    tv_calculated_BMI.setVisibility(View.VISIBLE);
                    tv_status.setVisibility(View.VISIBLE);

                    Toast toast = Toast.makeText(getApplicationContext(), "BMI Calculated", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 90);
                    toast.show();

                    totalWeight = Double.parseDouble(weight);
                    totalHeight = 12 * Double.parseDouble(feet) + Double.parseDouble(inches);
                    result = Math.round((totalWeight / (totalHeight * totalHeight)) * 703 * 10) / 10.0;
                }




                if(result < 18.5){
                    tv_calculated_BMI.setText("Your BMI: " + result);
                    tv_status.setText("You are Underweight");
                }else if(result > 18.5 && result <= 24.9){
                    tv_calculated_BMI.setText("Your BMI: " + result);
                    tv_status.setText("Your weight is normal");
                }else if(result > 25 && result <= 29.9){
                    tv_calculated_BMI.setText("Your BMI: " + result);
                    tv_status.setText("You are overweight");
                }else if(result >= 30){
                    tv_calculated_BMI.setText("Your BMI: " + result);
                    tv_status.setText("You are obese");
                }

            }

            public void visiblity(){
                tv_calculated_BMI.setVisibility(View.INVISIBLE);
                tv_status.setVisibility(View.INVISIBLE);

            }
        });
    }
}
