package com.example.turi_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class WritePostActivity extends AppCompatActivity {
    private static final String TAG = "WritePostActivity";
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            StartSignUpActivity();
        }

        findViewById(R.id.check).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ((v.getId())){
                case R.id.check:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate(){
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();

        if (title.length() > 0 && contents.length() > 0 ) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            WirteInfo wirteInfo = new WirteInfo(title,contents,user.getUid());
            uploader(wirteInfo);
            showToast(WritePostActivity.this, "게시물 등록이 완료되었습니다.");
            myStartActivity(MainActivity.class);
        }else{
            showToast(WritePostActivity.this, "제목과 내용을 작성해주세요.");
        }
    }

    private void uploader(WirteInfo wirteInfo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(wirteInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
    private void StartSignUpActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

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