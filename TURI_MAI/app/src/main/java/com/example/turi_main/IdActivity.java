package com.example.turi_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class IdActivity extends AppCompatActivity {
    Button bCFC1, bCF1, bIdfind;
    EditText edMyemail1, edEmailcode1;
    TextView tvJoin2, tvPwfind2, tvLogin1;
    ImageButton imgbLoginpage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idfind);

        bCFC1 = findViewById(R.id.b_certification1);
        bCF1 = findViewById(R.id.b_Confirm1);
        bIdfind = findViewById(R.id.b_idfind);
        edMyemail1 = findViewById(R.id.ed_myemail1);
        edEmailcode1 = findViewById(R.id.ed_emailcode1);
        tvJoin2 = findViewById(R.id.tv_join2);
        tvPwfind2 = findViewById(R.id.tv_pwfind2);
        tvLogin1 = findViewById(R.id.tv_login1);
        imgbLoginpage1 = findViewById(R.id.imgb_loginpage2);

        imgbLoginpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(IdActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });

        tvLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(IdActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });

        tvPwfind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(IdActivity.this, PwfindActivity.class);
                startActivity(mIntent);
            }
        });

        tvJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(IdActivity.this, JoinusActivity.class);
                startActivity(mIntent);
            }
        });
    }
}