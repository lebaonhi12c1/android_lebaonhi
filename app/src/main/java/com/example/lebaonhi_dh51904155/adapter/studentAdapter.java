package com.example.lebaonhi_dh51904155.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lebaonhi_dh51904155.ProccessClassesActivity;
import com.example.lebaonhi_dh51904155.ProccessStudentActivity;
import com.example.lebaonhi_dh51904155.R;
import com.example.lebaonhi_dh51904155.StudentDetailActivity;
import com.example.lebaonhi_dh51904155.UpdateStudentActivity;
import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.database.sinhviendao;
import com.example.lebaonhi_dh51904155.model.classes;
import com.example.lebaonhi_dh51904155.model.student;

import java.util.List;

public class studentAdapter extends BaseAdapter {
    Activity context;
    List<student> sinhviens;

    public studentAdapter(Activity context, List<student> sinhviens) {
        this.context = context;
        this.sinhviens = sinhviens;
    }

    @Override
    public int getCount() {
        return sinhviens.size();
    }

    @Override
    public Object getItem(int i) {
        return sinhviens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.activity_student_item,null);
        TextView id = view1.findViewById(R.id.id_item);
        TextView name = view1.findViewById(R.id.name_item);
        TextView khoa = view1.findViewById(R.id.classes_item);
        Button sua = view1.findViewById(R.id.btn_update_sinhvien);
        Button xoa = view1.findViewById(R.id.btn_remove_sinhvien);
        Button chitiet = view1.findViewById(R.id.btn_info_details);
        ImageView hinhanh = view1.findViewById(R.id.image_item);
        student sinhvien = sinhviens.get(i);
        id.setText(sinhvien.getId()+"");
        name.setText(sinhvien.getName());
        khoadao khoadao = new khoadao(context);
        khoa.setText(khoadao.getHhoa(sinhvien.getClasses()).getName());
        Bitmap bmImg = BitmapFactory.decodeByteArray(sinhvien.getImage(), 0,sinhvien.getImage().length);
        hinhanh.setImageBitmap(bmImg);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStudentActivity.class);
                intent.putExtra("id",sinhvien.getId());
                context.startActivity(intent);
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                sinhviendao dao = new sinhviendao(context);
                student category = sinhviens.get(i);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa danh mục này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int a) {

                        dao.delete(category.getId());
                        Intent intent = new Intent(context, ProccessStudentActivity.class);
                        context.startActivity(intent);
                        context.finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentDetailActivity.class);
                intent.putExtra("id",sinhviens.get(i).getId());
                context.startActivity(intent);
            }
        });
        return view1;
    }
}
