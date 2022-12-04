package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
                if(username.getText().toString().equals("nam") && password.getText().toString().equals("nam")){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(MainActivity.this,"en");
                Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(MainActivity.this,"vi");
                Intent intent2 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}