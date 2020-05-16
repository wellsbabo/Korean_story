package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PeopleFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //return inflater.inflate(R.layout.fragment_mypage,container,false);
        view = inflater.inflate(R.layout.fragment_people,container,false);
        nameFragment wordfragment = new nameFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container,wordfragment).commit();

        return view;
    }

//    public void onWordSelected(int position) {
//           //portrait 모드 체크
//            contentFragment newFragment = new contentFragment();
//            Bundle args = new Bundle();
//            args.putInt("position", position);
//            newFragment.setArguments(args);
//
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragment_container, newFragment);
//            transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
//            transaction.commit();
//
//    }

}