package com.example.lebaonhi_dh51904155;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.database.sinhviendao;
import com.example.lebaonhi_dh51904155.model.student;

public class StudentDetailActivity extends AppCompatActivity {
    TextView id;
    TextView name;
    TextView email;
    TextView phone;
    TextView khoa;
    ImageView image;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        addControls();
        getdata();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateStudentActivity.class);
                intent.putExtra("id",Integer.parseInt(id.getText().toString()));
                startActivity(intent);
                finish();
            }
        });

    }
    public void addControls(){
        id = findViewById(R.id.id_details);
        name = findViewById(R.id.name_details);
        email = findViewById(R.id.email_details);
        phone = findViewById(R.id.phone_details);
        khoa = findViewById(R.id.classes_details);
        image = findViewById(R.id.image_details);
        update = findViewById(R.id.btn_update_info);
    }
    public void getdata(){
        Intent intent = getIntent();
        sinhviendao sinhviendao = new sinhviendao(this);
        khoadao khoadao = new khoadao(this);
        student sinhvien = sinhviendao.getSinhvien(intent.getIntExtra("id",-1));
        id.setText(String.valueOf(sinhvien.getId()));
        name.setText(sinhvien.getName());
        email.setText(sinhvien.getEmail());
        phone.setText(sinhvien.getPhone());
        khoa.setText(khoadao.getHhoa(sinhvien.getClasses()).getName());
        Bitmap bmImg = BitmapFactory.decodeByteArray(sinhvien.getImage(), 0,sinhvien.getImage().length);
        image.setImageBitmap(bmImg);
    }
}