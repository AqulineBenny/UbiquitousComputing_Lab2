package com.example.lab2_q2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class ValidateActivity extends AppCompatActivity {

    private EditText editCode;
    private int expectedCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        editCode = findViewById(R.id.editCode);
        String name = getIntent().getExtras().getString("name");
        expectedCode = getIntent().getExtras().getInt("code");
        TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("hello " + name + " , please check your email and enter the code below.");
    }

    public void onValidate(View view) {
        String entered = editCode.getText().toString().trim();
        if (entered.isEmpty()) {
            Toast.makeText(this, "Please enter the code", Toast.LENGTH_SHORT).show();
            return;
        }
        int code = Integer.parseInt(entered);
        if (code == expectedCode) {
            Toast.makeText(this, "Account validated!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Invalid code", Toast.LENGTH_LONG).show();
        }
    }

}