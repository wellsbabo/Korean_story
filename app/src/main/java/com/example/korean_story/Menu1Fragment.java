package com.example.korean_story;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Menu1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_menu1,container,false);

        //Intent intent = getIntent();


        TextView name = view.findViewById(R.id.name);
        TextView summary = view.findViewById(R.id.summary);
        //System.out.println(Data.names[0]);
        name.setText(Data.names[0]);
        summary.setText(Data.summary[0]);

        //return inflater.inflate(R.layout.fragment_menu1,container,false);
        return view;
    }
}
