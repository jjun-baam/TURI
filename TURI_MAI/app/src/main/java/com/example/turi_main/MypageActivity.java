package com.example.turi_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MypageActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private FirebaseUser mUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageButton imgbback;
    ImageView imguserpicture;
    TextView tvusername, tvemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            StartSignUpActivity();
        }
        findViewById(R.id.bt_logout).setOnClickListener(onClickListener);


        imgbback = findViewById(R.id.imgb_back1);
        imguserpicture = findViewById(R.id.img_user_picture);
        tvusername = findViewById(R.id.tv_user_name);
        tvemail = findViewById(R.id.tv_email);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();

        if(mUser!=null)
        {
            tvemail.setText(mFirebaseAuth.getCurrentUser().getEmail());
            db.collection("users").document(mUser.getUid()).get().addOnCompleteListener(task -> {
                if(task.getException() != null) {
                    return;
                }
                DocumentSnapshot snapshot = task.getResult();
                String name = snapshot.getString("name");
                tvusername.setText(name);
            });
        }

        imgbback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MypageActivity.this, SubActivity.class);
                startActivity(mIntent);
            }
        });


    }
    //메인화면에서 로그인시 뒤로가지 않게 하는방법(로그아웃)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_logout:
                    FirebaseAuth.getInstance().signOut();
                    starToast("로그아웃 되었습니다.");
                    StartSignUpActivity();
                    break;
            }
        }
    };
    private void StartSignUpActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private void starToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}






