package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class contentFragment extends Fragment {

    public contentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmnet_content, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        Bundle args = getArguments();
        if(args != null){
            int pos = args.getInt("position");
            updateDefinitionView(pos);
        }
    }

    public void updateDefinitionView(int position){

        TextView name = (TextView)getView().findViewById(R.id.name);
        TextView summary = (TextView)getView().findViewById(R.id.summary);
        //System.out.println(Data.names[0]);
        name.setText(Data.names[position]);
        summary.setText(Data.summary[position]);

        //TextView def = (TextView)getView().findViewById(R.id.content);
        //def.setText(Data.summary[position]);
    }

}
