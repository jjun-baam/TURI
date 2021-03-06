package com.example.turi_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class JoinusActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "JoinusActivity";

    ImageButton imgbLoginpage1;
    EditText edIdget,  edMyname, edMybrth, edMyphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.b_Join).setOnClickListener(onClickListener);

//        bJoin = findViewById(R.id.b_Join);
        imgbLoginpage1 = findViewById(R.id.imgb_loginpage1);
        edIdget = findViewById(R.id.ed_idget);

        edMyname = findViewById(R.id.ed_myname);
        edMybrth = findViewById(R.id.ed_mybrth);
        edMyphone = findViewById(R.id.ed_myphone);

        imgbLoginpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(JoinusActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_Join:
                    signUp();

                    break;
            }

        }
    };



    //???????????? ?????????, ????????????
    private void signUp() {
        String email = ((EditText)findViewById(R.id.ed_idget)).getText().toString();
        String password = ((EditText)findViewById(R.id.ed_pw)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.ed_pwCheck)).getText().toString();
        String name = ((EditText)findViewById(R.id.ed_myname)).getText().toString();
        String birthday = ((EditText)findViewById(R.id.ed_mybrth)).getText().toString();
        String hpNumber = ((EditText)findViewById(R.id.ed_myphone)).getText().toString();

        if(name.length() > 0 && hpNumber.length() > 9 && birthday.length()>5){
            FirebaseUser user = mAuth.getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            UserInfo userInfo = new UserInfo(name, birthday, hpNumber);
            if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
                if(password.equals(passwordCheck)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        db.collection("users").document(task.getResult().getUser().getUid()).set(userInfo);
                                        showToast(JoinusActivity.this, "??????????????? ?????????????????????.");
                                        myStartActivity(MainActivity.class);

                                    } else {
                                        if (task.getException()!=null){
                                            starToast(task.getException().toString());
                                        }


                                    }
                                }
                            });
                }else{
                    showToast(JoinusActivity.this, "??????????????? ???????????? ????????????.");
                }
            }else {
                showToast(JoinusActivity.this, "????????? ?????? ??????????????? ????????? ?????????.");
                }

        }else {
            starToast("????????? 1??? ??????, ????????? ?????? 10??? ??????, ???????????? 6?????? ??????????????????. ");
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


