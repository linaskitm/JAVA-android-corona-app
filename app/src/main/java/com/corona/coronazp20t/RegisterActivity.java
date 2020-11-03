package com.corona.coronazp20t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name1 = findViewById(R.id.setName);
        final EditText email1 = findViewById(R.id.setEmail);
        final EditText password1 = findViewById(R.id.setPassword);

        Button registerBtn1 = findViewById(R.id.registerBtn1);

        registerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String name = name1.getText().toString();
                 String email = email1.getText().toString();
                 String password = password1.getText().toString();

                 email1.setError(null);
                 // email vaidation
                if(EmailValidation.isEmailValid(email)) {
                    // Intention to go in search window                       from     --------->   to
                    Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    // going to search window, action.
                    startActivity(goToLoginActivity);
                } else {
                    email1.setError("Wrong Email");
                    email1.requestFocus();
                }
            }
        });
    }
}