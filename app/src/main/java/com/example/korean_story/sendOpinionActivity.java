package com.example.korean_story;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sendOpinionActivity extends AppCompatActivity {

    //Button sendOpinion;
    EditText Opinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_opinion);

        //sendOpinion = findViewById(R.id.sendOpinionButton);
        Opinion = findViewById(R.id.editOpinion);

        //sendOpinion.setOnClickListener(onClickListener);
    }

    public void click(View v){
        String opinion = Opinion.getText().toString();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("opinion").push().setValue(opinion);
        startToast("소중한 의견 감사합니다.");
        finish();
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
