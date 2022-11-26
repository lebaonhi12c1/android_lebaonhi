package com.example.lebaonhi_dh51904155;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lebaonhi_dh51904155.adapter.classesAdapter;
import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.model.classes;

import java.util.ArrayList;
import java.util.List;

public class ProccessClassesActivity extends AppCompatActivity {
    ListView listView;
    classesAdapter adapter;
    List<classes> list;
    Button themkhoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proccess_classes);
        addControls();
        getdskhoa();
        themkhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddClassesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public  void addControls(){
        list = new ArrayList<>();
        listView = findViewById(R.id.lvClasses);
        adapter = new classesAdapter(this,list);
        listView.setAdapter(adapter);
        themkhoa = findViewById(R.id.btn_add_classes);

    }
    public void getdskhoa(){
        khoadao dao = new khoadao(this);
        list = dao.getAll();
        adapter = new classesAdapter(this, list);
        listView.setAdapter(adapter);
    }

}