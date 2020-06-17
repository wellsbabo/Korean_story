package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainPeopleFragment extends Fragment {  //

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.main_fragment_people,container,false);
        nameFragment wordfragment = new nameFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container,wordfragment).commit();

        return view;
    }


}