package com.example.korean_story;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    //4개의 메뉴에 들어갈 Fragment들
    private Menu1Fragment menu1Fragment = new Menu1Fragment();
    private Menu2Fragment menu2Fragment = new Menu2Fragment();
    private Menu3Fragment menu3Fragment = new Menu3Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignUpActivity();
        }

        //findViewById(R.id.logoutButton).setOnClickListener(onClickListener);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        //첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,menu1Fragment).commitAllowingStateLoss();

        //bottomNavigationView의 메뉴가 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.home:{
                        transaction.replace(R.id.frame_layout,menu1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.collection:{
                        transaction.replace(R.id.frame_layout,menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.people:{
                        transaction.replace(R.id.frame_layout,menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.logoutButton: {
                        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
                        break;
                    }
                }
                return false;
            }
        });

    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    break;
            }
        }
    };

    private void startSignUpActivity(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }


}