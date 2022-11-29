package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.database.sinhviendao;
import com.example.lebaonhi_dh51904155.model.classes;
import com.example.lebaonhi_dh51904155.model.student;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateStudentActivity extends AppCompatActivity {
    final int req_chonhinh = 123;
    final int req_chuphinh = 321;
    TextView id;
    EditText name;
    EditText email;
    EditText phone;
    Spinner khoa;
    ImageView hinhanh;
    Button chuphinh;
    Button chonhinh;
    Button luu;
    List<classes> khoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        addControls();
        getkhoa();
        getthongtin();
        chonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,req_chonhinh);
            }
        });
        chuphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,req_chuphinh);
            }
        });
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinhviendao sinhviendao = new sinhviendao(getApplicationContext());
                sinhviendao.getupdate(new student(
                        Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        email.getText().toString(),
                        phone.getText().toString(),
                        getByetArrayFromImangeView(hinhanh),
                        khoa.getSelectedItemPosition()
                ));
                Intent intent = new Intent(getApplicationContext(),ProccessStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addControls() {
        id = findViewById(R.id.id_update_sinhvien);
        name = findViewById(R.id.name_update_sinhvien);
        email = findViewById(R.id.email_update_sinhvien);
        phone = findViewById(R.id.phone_update_sinhvien);
        khoa = findViewById(R.id.spinner_update_sinhvien);
        hinhanh = findViewById(R.id.image_update_sinhvien);
        chuphinh = findViewById(R.id.take_photo_update);
        chonhinh = findViewById(R.id.choose_photo_update);
        luu = findViewById(R.id.btn_save_update_sinhvien);
        khoas = new ArrayList<>();
    }
    public void getkhoa(){
        khoadao khoadao = new khoadao(this);
        khoas = khoadao.getAll();
        ArrayAdapter<String> classesArrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        for (int i=0; i<khoas.size();i++){
            classesArrayAdapter.add(khoas.get(i).getName());
        }
        khoa.setAdapter(classesArrayAdapter);
    }
    public void getthongtin(){
        Intent intent = getIntent();
        sinhviendao sinhviendao = new sinhviendao(this);
        student sinhvien = sinhviendao.getSinhvien(intent.getIntExtra("id",-1));
        id.setText(sinhvien.getId()+ "");
        name.setText(sinhvien.getName());
        email.setText(sinhvien.getEmail());
        phone.setText(sinhvien.getPhone());
        khoa.setSelection(sinhvien.getClasses());
        Bitmap bmImg = BitmapFactory.decodeByteArray(sinhvien.getImage(), 0,sinhvien.getImage().length);
        hinhanh.setImageBitmap(bmImg);
    }
    private  byte[] getByetArrayFromImangeView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == req_chuphinh && resultCode ==RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            hinhanh.setImageBitmap(bitmap);
        }
        if(requestCode == req_chonhinh && resultCode ==RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                hinhanh.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAbout:
                Intent intent = new Intent(UpdateStudentActivity.this, UpdateStudentActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(UpdateStudentActivity.this,"en");
                Intent intent1 = new Intent(UpdateStudentActivity.this,UpdateStudentActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(UpdateStudentActivity.this,"vi");
                Intent intent2 = new Intent(UpdateStudentActivity.this,UpdateStudentActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}