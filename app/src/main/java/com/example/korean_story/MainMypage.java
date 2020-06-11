package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainMypage extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //return inflater.inflate(R.layout.fragment_mypage,container,false);
        view = inflater.inflate(R.layout.main_fragment_mypage,container,false);
        MypageFragment mypageFragment = new MypageFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fragment_myhome_container,mypageFragment).commit();

        return view;
    }
}
