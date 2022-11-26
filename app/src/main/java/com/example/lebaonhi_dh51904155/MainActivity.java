package com.example.lebaonhi_dh51904155;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("lebaonhi") && password.getText().toString().equals("lebaonhi")){
                    Intent intent  = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Intent intent  = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Username or Password is not correct !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}