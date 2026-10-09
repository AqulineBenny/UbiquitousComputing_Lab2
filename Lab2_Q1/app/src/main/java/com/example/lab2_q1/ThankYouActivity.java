package com.example.lab2_q1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        String name = getIntent().getExtras().getString("name");
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText("Thank you " + name + ", your request is being processed");
    }
}