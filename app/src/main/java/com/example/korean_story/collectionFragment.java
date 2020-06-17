package com.example.korean_story;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class collectionFragment extends Fragment {  //컬렉션을 눌렀을 때 컬렉션에 담긴 목록을 보여주는 프레그먼트

    String pos;
    List<Integer> collection_list_index = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collection_content_list,container,false);

        Bundle args = getArguments();
        if(args != null){
            pos = args.getString("position");
        }
        String[] tmp = pos.split(",");
        for(int i=0; i<tmp.length; i++){
            collection_list_index.add(Integer.parseInt(tmp[i]));
        }
        System.out.println(collection_list_index);

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);
        ListView listView = view.findViewById(R.id.collection_content_list);

        listView.setAdapter(adapter);

        DBHelper helper = new DBHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title,content from people "+ "order by _id",null);

        while(cursor.moveToNext()) {
            if(collection_list_index.contains(cursor.getPosition())) {
                adapter.add(cursor.getString(0));
            }
        }
        db.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                collectionContentFragment newFragment = new collectionContentFragment();
                Bundle args = new Bundle();
                args.putInt("position", collection_list_index.get(position));
                newFragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_myhome_container, newFragment);
                transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
                transaction.commit();
            }
        });
        return view;
    }
}
