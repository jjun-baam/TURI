package com.example.turi_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MyPostPageActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private FirebaseUser mUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvpost1;
    ImageView imgbback1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_post_page);

        tvpost1 = findViewById(R.id.tv_post_1);
        imgbback1 = findViewById(R.id.imgb_back1);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();

        if(mUser!=null) {
            db.collection("posts").whereEqualTo("publisher", mUser.getUid()).get().addOnCompleteListener(task -> {
                if (task.getException() != null) {
                    return;
                }
                QuerySnapshot querySnapshot = task.getResult();
                for (DocumentSnapshot snapshot : querySnapshot) {
                    String title = snapshot.getString("title");
                    tvpost1.setText(title);
                }
            });
        }

        tvpost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MyPostPageActivity.this, PostActivity.class);
                startActivity(mIntent);
            }
        });

        imgbback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MyPostPageActivity.this, SubActivity.class);
                startActivity(mIntent);
            }
        });
    }
}