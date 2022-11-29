package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lebaonhi_dh51904155.adapter.classesAdapter;
import com.example.lebaonhi_dh51904155.adapter.studentAdapter;
import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.database.sinhviendao;
import com.example.lebaonhi_dh51904155.model.student;

import java.util.ArrayList;
import java.util.List;

public class ProccessStudentActivity extends AppCompatActivity {
    Button themsinhvien;
    ListView lvsinhvien;
    List<student> sinhviens;
    studentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proccess_student);
        addControls();
        getdssinhvien();
        themsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void addControls(){
        lvsinhvien = findViewById(R.id.lvStudent);
        sinhviens = new ArrayList<>();
        studentAdapter = new studentAdapter(this,sinhviens);
        lvsinhvien.setAdapter(studentAdapter);
        themsinhvien = findViewById(R.id.btn_add_student);

    }
    public void getdssinhvien(){
        sinhviendao dao = new sinhviendao(this);
        sinhviens = dao.getAll();
        studentAdapter = new studentAdapter(this, sinhviens);
        lvsinhvien.setAdapter(studentAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent = new Intent(ProccessStudentActivity.this, ProccessStudentActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(ProccessStudentActivity.this,"en");
                Intent intent1 = new Intent(ProccessStudentActivity.this,ProccessStudentActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(ProccessStudentActivity.this,"vi");
                Intent intent2 = new Intent(ProccessStudentActivity.this,ProccessStudentActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}