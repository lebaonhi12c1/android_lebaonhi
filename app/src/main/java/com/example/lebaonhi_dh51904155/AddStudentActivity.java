package com.example.lebaonhi_dh51904155;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lebaonhi_dh51904155.adapter.classesAdapter;
import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.database.sinhviendao;
import com.example.lebaonhi_dh51904155.model.classes;
import com.example.lebaonhi_dh51904155.model.student;

import org.xml.sax.Parser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    final int req_chonhinh = 123;
    final int req_chuphinh = 321;
    EditText id;
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
        setContentView(R.layout.activity_add_student);
        addControls();
        getkhoa();
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
                sinhviendao.insert(new student(Integer.parseInt(id.getText().toString()),name.getText().toString(),email.getText().toString(),phone.getText().toString(),getByetArrayFromImangeView(hinhanh),getIdkhoa()));
                Intent intent = new Intent(getApplicationContext(),ProccessStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public int getIdkhoa(){
        khoadao khoadao = new khoadao(this);
        for (int i =0; i<khoadao.getAll().size();i++){
            if (khoa.getSelectedItem().toString().equals(khoadao.getAll().get(i).getName())){
                return khoadao.getAll().get(i).getId();
            }
        }
        return -1;
    }

    private void addControls() {
        id = findViewById(R.id.id_add_student);
        name = findViewById(R.id.name_add_student);
        email = findViewById(R.id.email_add_student);
        phone = findViewById(R.id.phone_add_student);
        khoa = findViewById(R.id.spinner_classes);
        hinhanh = findViewById(R.id.image_add_student);
        chuphinh = findViewById(R.id.take_photo_add);
        chonhinh = findViewById(R.id.choose_add_photo);
        luu = findViewById(R.id.btn_save_add_sinhvien);
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
}