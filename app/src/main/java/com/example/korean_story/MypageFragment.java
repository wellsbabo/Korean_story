package com.example.korean_story;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

public class MypageFragment extends Fragment {  //로그아웃 및 나만의 컬렉션

    Button logout;
    Button makeCollection;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_mypage,container,false);

        //logout = (Button) view.findViewById(R.id.logoutButton)
        logout = (Button)view.findViewById(R.id.logoutButton);
        logout.setOnClickListener(onClickListener);

        makeCollection = (Button)view.findViewById(R.id.makeCollectionButton);
        makeCollection.setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //String test = FirebaseInstanceId.getInstance();
        System.out.println(uid);



        return view;
    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    break;
                case R.id.makeCollectionButton:
                    startMakeCollectionActivity();
                    //transaction.replace(R.id.frame_layout,peopleFragment).commitAllowingStateLoss();
                    break;
            }
        }
    };

    private void startSignUpActivity(){
        Intent intent = new Intent(getActivity(),SignUpActivity.class);
        startActivity(intent);
    }

    private void startMakeCollectionActivity(){
        Intent intent = new Intent(getActivity(),makeCollectionActivity.class);
        startActivity(intent);
    }
}