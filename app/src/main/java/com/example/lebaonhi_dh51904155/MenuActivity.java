package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button classes = findViewById(R.id.btn_class_list);
        Button student = findViewById(R.id.btn_student_list);
        Button about = findViewById(R.id.btn_about);
        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ProccessClassesActivity.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ProccessStudentActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,AboutActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(MenuActivity.this,"en");
                Intent intent1 = new Intent(MenuActivity.this,MenuActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(MenuActivity.this,"vi");
                Intent intent2 = new Intent(MenuActivity.this,MenuActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}