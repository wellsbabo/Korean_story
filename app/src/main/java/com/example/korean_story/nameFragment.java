package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class nameFragment extends Fragment{
    public nameFragment() {
        // Required empty public constructor
    }

    public interface onWordSelectedListener{
        public void onWordSelected(int position);
    }
//
    //onWordSelectedListener mWordSelListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name,container,false);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,Data.names);

        ListView listView = view.findViewById(R.id.word_list);
        //listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Data.names));
        listView.setAdapter(adapter);

        //mWordSelListener = (nameFragment)getActivity();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listView의 아이템이 클릭되면, onWordSelected로 넘어와서 실행됨
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mWordSelListener.onWordSelected(position);
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contentFragment newFragment = new contentFragment();
                Bundle args = new Bundle();
                args.putInt("position", position);
                newFragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
                transaction.commit();


                //System.out.println(position);

            }
        });

        return view;
    }
}
