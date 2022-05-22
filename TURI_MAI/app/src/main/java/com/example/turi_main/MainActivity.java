package com.example.turi_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageButton imgbMyt, imgbCh1, imgbCh2, imgbCh3, imgbCh4, imgbEdit;
    ImageView imgBoard, imgGongji;
    EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbMyt = findViewById(R.id.imgb_myt);
        imgbCh1 = findViewById(R.id.imgb_ch1);
        imgbCh2 = findViewById(R.id.imgb_ch2);
        imgbCh3 = findViewById(R.id.imgb_ch3);
        imgbCh4 = findViewById(R.id.imgb_ch4);
        imgbEdit = findViewById(R.id.imgb_edit);
        imgBoard = findViewById(R.id.img_board);
        imgGongji = findViewById(R.id.img_gongji);
        edSearch = findViewById(R.id.ed_search);

        imgbMyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(mIntent);
            }
        });


        imgbEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, WritePostActivity.class);
                startActivity(mIntent);
            }
        });

        imgbCh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, GooglemapActivity.class);
                startActivity(mIntent);
            }
        });

        edSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String sSearch = edSearch.getText().toString();
                    Intent mIntent = new Intent(MainActivity.this, GooglemapActivity.class);
                    mIntent.putExtra("sSearch", sSearch);
                    startActivity(mIntent);
                    return true;
                }
                return false;
            }
        });


    }


}




