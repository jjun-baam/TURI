package com.example.turi_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    ImageButton imgbMycate, imgbKakaobtn;
    TextView tvJoin1, tvIdfind1, tvPwfind1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.imgb_loginbtn).setOnClickListener(onClickListener);

        imgbMycate = findViewById(R.id.imgb_mycate);
        imgbKakaobtn = findViewById(R.id.imgb_kakaobtn);
        tvJoin1 = findViewById(R.id.tv_join1);
        tvIdfind1 = findViewById(R.id.tv_idfind1);
        tvPwfind1 = findViewById(R.id.tv_pwfind1);

        imgbMycate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(LoginActivity.this, SubActivity.class);
                startActivity(mIntent);
            }
        });

        tvJoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(LoginActivity.this, JoinusActivity.class);
                startActivity(mIntent);

            }
        });

        tvIdfind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(LoginActivity.this, IdActivity.class);
                startActivity(mIntent);

            }
        });

        tvPwfind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(LoginActivity.this, PwfindActivity.class);
                startActivity(mIntent);

            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgb_loginbtn:
                    Sign();
                    break;


            }
        }
    };
    //로그아웃 후 로그인 화면 이동 후 뒤로가기 바로 종료
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void Sign() {
        String email = ((EditText)findViewById(R.id.ed_Id)).getText().toString();
        String password = ((EditText)findViewById(R.id.ed_password)).getText().toString();

        if(email.length() > 0 && password.length() > 0 ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                showToast(LoginActivity.this, "로그인에 성공하였습니다.");
                                myStartActivity(MainActivity.class);
                                finish();
                            } else {
                                if (task.getException()!=null){
                                    showToast(LoginActivity.this, "아이디 또는 비밀번호가 틀렸습니다.");
                                }
                            }
                        }
                    });

            }else {
            starToast("이메일 또는 비밀번호를 입력해 주세요. ");
        }

    }

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    private void starToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}