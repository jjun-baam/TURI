package com.example.turi_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class PostActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private FirebaseUser mUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvtitle, tvcontent;
    ImageView imgback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tvtitle = findViewById(R.id.tv_title);
        tvcontent = findViewById(R.id.tv_content);
        imgback2 = findViewById(R.id.imgb_back2);
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
                    tvtitle.setText(title);
                }
            });

            db.collection("posts").whereEqualTo("publisher", mUser.getUid()).get().addOnCompleteListener(task -> {
                if (task.getException() != null) {
                    return;
                }
                QuerySnapshot querySnapshot = task.getResult();
                for (DocumentSnapshot snapshot : querySnapshot) {
                    String content = snapshot.getString("contents");
                    tvcontent.setText(content);
                }
            });

        }

        imgback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(PostActivity.this, MyPostPageActivity.class);
                startActivity(mIntent);
            }
        });

    }
}