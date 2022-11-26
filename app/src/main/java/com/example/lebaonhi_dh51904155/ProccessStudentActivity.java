package com.example.lebaonhi_dh51904155;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
}