package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.model.classes;

public class AddClassesActivity extends AppCompatActivity {
    TextView tenkhoa;
    Button themlop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes);
        tenkhoa = findViewById(R.id.classes_name);
        themlop = findViewById(R.id.btn_save_classes);

        themlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classes category = new classes();
                category.setName(tenkhoa.getText().toString());
                khoadao dao = new khoadao(AddClassesActivity.this);
                dao.insert(category);
                Toast.makeText(getApplicationContext(), "Danh mục đã được lưu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ProccessClassesActivity.class);
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(AddClassesActivity.this, AddClassesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(AddClassesActivity.this,"en");
                Intent intent1 = new Intent(AddClassesActivity.this,AddClassesActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(AddClassesActivity.this,"vi");
                Intent intent2 = new Intent(AddClassesActivity.this,AddClassesActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}