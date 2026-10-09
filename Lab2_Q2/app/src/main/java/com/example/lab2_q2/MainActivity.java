package com.example.lab2_q2;

import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPassword;
    private EditText editPhone;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
    }

    public void onSubmit(View view) {
        String name = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String email = editEmail.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError("Name is required");
            editName.requestFocus();
            return;
        }
        if (containsDigits(name)) {
            editName.setError("Name cannot contain digits");
            editName.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Password must be at least 6 characters");
            editPassword.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            editPhone.setError("Telephone number is required");
            editPhone.requestFocus();
            return;
        }
        if (containsLetters(phone)) {
            editPhone.setError("Telephone number cannot contain letters");
            editPhone.requestFocus();
            return;
        }
        if (phone.length() < 7) {
            editPhone.setError("Telephone number must be at least 7 digits");
            editPhone.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please enter a valid email address");
            editEmail.requestFocus();
            return;
        }

        Random random = new Random();
        int code = random.nextInt(9000) + 1000;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Validation Code");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello " + name + ", your validation code is: " + code);
        if (emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }

        Intent validateIntent = new Intent(this,ValidateActivity.class);
        validateIntent.putExtra("name",name);
        validateIntent.putExtra("code",code);
        startActivity(validateIntent);
    }

    private boolean containsDigits(String string){
        for (char c : string.toCharArray())  {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    private boolean containsLetters(String string){
        for(char c : string.toCharArray()){
            if (Character.isLetter(c))
                return true;
        }
        return false;
    }
}