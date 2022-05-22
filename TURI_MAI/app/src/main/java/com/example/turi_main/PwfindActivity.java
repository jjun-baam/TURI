package com.example.turi_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PwfindActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    Button bPwfind;
    EditText edMyemail2, edMyid;
    TextView tvJoin3, tvIdfind2, tvLogin2;
    ImageButton imgbLoginpage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwfind);


        bPwfind = findViewById(R.id.b_pwfind);
        edMyemail2 = findViewById(R.id.ed_myemail2);

        edMyid = findViewById(R.id.ed_myid);
        tvJoin3 = findViewById(R.id.tv_join3);
        tvIdfind2 = findViewById(R.id.tv_idfind2);
        tvLogin2 = findViewById(R.id.tv_login2);
        imgbLoginpage2 = findViewById(R.id.imgb_loginpage3);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.b_pwfind).setOnClickListener(onClickListener);

        imgbLoginpage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(PwfindActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });

        tvLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(PwfindActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });

        tvIdfind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(PwfindActivity.this, IdActivity.class);
                startActivity(mIntent);
            }
        });

        tvJoin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(PwfindActivity.this, JoinusActivity.class);
                startActivity(mIntent);
            }
        });


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_pwfind:
                    send();
                    break;
            }
        }
    };
    // 비밀번호 재설정정
    private void send() {
        String email = ((EditText) findViewById(R.id.ed_myid)).getText().toString();

        if (email.length() > 0) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                starToast("이메일을 보냈습니다.");
                            }
                        }
                    });
        } else {
            starToast("이메일을 입력해 주세요.");
        }
    }
    private void starToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}