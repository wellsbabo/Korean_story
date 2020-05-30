package com.example.korean_story;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class contentFragment extends Fragment {

    DBHelper helper;
    SQLiteDatabase db;

    Cursor cursor;
    public contentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        helper = new DBHelper(getActivity());
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("select title,content from people "+ "order by _id",null);
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

        String name_text = "";
        String content_text = "";

        for(int i=0; i<=position; i++){
            cursor.moveToNext();
        }

        name_text = cursor.getString(0);
        content_text = cursor.getString(1);

        TextView name = (TextView)getView().findViewById(R.id.name);
        TextView summary = (TextView)getView().findViewById(R.id.summary);

        name.setText(name_text);
        summary.setText(content_text);
    }

}
