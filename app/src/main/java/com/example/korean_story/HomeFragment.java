package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    int randomNum;
    int from = 0;
    int to = 6; //데이터의 마지막 인덱스

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //Intent intent = getIntent();
        randomNum = (int)(Math.random()*(Math.abs(to-from)+1)) + Math.min(from,to);

        //System.out.println(randomNum);

        TextView name = view.findViewById(R.id.name);
        TextView summary = view.findViewById(R.id.summary);
        //System.out.println(Data.names[0]);
        name.setText(Data.names[randomNum]);
        summary.setText(Data.summary[randomNum]);

        //return inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
}
