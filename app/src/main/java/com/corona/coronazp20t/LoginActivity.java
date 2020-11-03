package com.corona.coronazp20t;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // open empty window
        setContentView(R.layout.activity_login);// load data
        Button loginBtn = findViewById(R.id.loginBtn); // get needed elements from xml file (view file)
        Button registerBtn = findViewById(R.id.registerBtn);
        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName2 = userName.getText().toString();
                String password2 = password.getText().toString();
                Toast.makeText(LoginActivity.this,
                        "Prisijungimo vardo: "  +  userName2 + "\n" +
                        "Slatptazodis: " + password2,
                        Toast.LENGTH_SHORT).show();
                // Intention to go in search window                       from     --------->   to
                Intent goToRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                // going to search window, action.

                startActivity(goToRegisterActivity);
                userName.setError(null);
                password.setError(null);
                if(Validation.isCredentialsValid(userName2)&& Validation.isCredentialsValid(password2)) {
                    Intent gotoSearchActivity = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(goToRegisterActivity);
                } else {
                    userName.setError(getResources().getString(R.string.login_invalid_credentials_message));
                    userName.requestFocus();
                }
            }
        });
    }

}