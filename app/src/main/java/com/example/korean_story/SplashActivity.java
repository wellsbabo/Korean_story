package com.example.korean_story;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    static public Content content;

    private final String tableName = "people";

    SQLiteDatabase sampleDB = null;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

        DBHelper helper = new DBHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM people");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                content = dataSnapshot.getValue(Content.class);

                db.execSQL("INSERT INTO " + tableName + " (title, content)  Values ('" + content.getTitle() + "', '" + content.getContent() + "');");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
