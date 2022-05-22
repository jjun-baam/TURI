package com.example.turi_main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SubActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private FirebaseUser mUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imgcr, imgprofile;
    ImageButton imgbMain;
    TextView tvCB,tvCM, tvDB, tvUP, tvLUV, tvCS, tvMyname, tvLV, tvEmail;
    ConstraintLayout clMypage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        imgprofile = findViewById(R.id.img_profile);
        imgbMain = findViewById(R.id.imgb_main);
        tvCB = findViewById(R.id.tv_CB);
        tvCM = findViewById(R.id.tv_CM);
        tvDB = findViewById(R.id.tv_DB);
        tvUP = findViewById(R.id.tv_UP);
        tvLUV = findViewById(R.id.tv_LUV);
        tvCS = findViewById(R.id.tv_CS);
        tvMyname = findViewById(R.id.tv_myname);
        tvLV = findViewById(R.id.tv_Lv);
        tvEmail = findViewById(R.id.tv_email);
        clMypage = findViewById(R.id.cl_mypage);
        imgcr = findViewById(R.id.img_cr);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();

        if(mUser!=null)
        {
            tvEmail.setText(mFirebaseAuth.getCurrentUser().getEmail());
            db.collection("users").document(mUser.getUid()).get().addOnCompleteListener(task -> {
                if(task.getException() != null) {
                    return;
                }
                DocumentSnapshot snapshot = task.getResult();
                String name = snapshot.getString("name");
                tvMyname.setText(name);
            });
        }


        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SubActivity.this, CameraActivity.class);
                startActivity(mIntent);
            }
        });
        imgbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(mIntent);
            }
        });

        clMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SubActivity.this, MypageActivity.class);
                startActivity(mIntent);

            }
        });

        tvCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(SubActivity.this, MyPostPageActivity.class);
                startActivity(mIntent);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    String profilePath = data.getStringExtra("profilePath");
                    Log.e("로그:", "profilePath" + profilePath);
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    imgprofile.setImageBitmap(bmp);
                }
                break;
            }
        }
    }


}