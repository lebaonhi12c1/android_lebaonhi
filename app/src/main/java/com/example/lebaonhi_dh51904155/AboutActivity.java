package com.example.lebaonhi_dh51904155;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {
    ImageButton call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        call = findViewById(R.id.btn_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0927146951"));
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
                Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.MenuExit:
                finishAffinity();
                break;
            case R.id.MenuUsa:
                Util.daNgonNgu(AboutActivity.this,"en");
                Intent intent1 = new Intent(AboutActivity.this,AboutActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.MenuVN:
                Util.daNgonNgu(AboutActivity.this,"vi");
                Intent intent2 = new Intent(AboutActivity.this,AboutActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:

        }

        return super.onOptionsItemSelected(item);
    }
}