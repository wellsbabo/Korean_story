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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class nameFragment extends Fragment{

    Content content = SplashActivity.content;

    public nameFragment() {
        // Required empty public constructor
    }

    public interface onWordSelectedListener{
        public void onWordSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name,container,false);



        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);
        ListView listView = view.findViewById(R.id.word_list);

        listView.setAdapter(adapter);


        DBHelper helper = new DBHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title,content from people "+ "order by _id",null);

        while(cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
        }
        db.close();
        //

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


            }
        });

        return view;
    }

}
