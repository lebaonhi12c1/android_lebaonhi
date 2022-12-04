package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent = new Intent(ProccessClassesActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(ProccessClassesActivity.this,"en");
                Intent intent1 = new Intent(ProccessClassesActivity.this,ProccessClassesActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(ProccessClassesActivity.this,"vi");
                Intent intent2 = new Intent(ProccessClassesActivity.this,ProccessClassesActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}