package com.example.korean_story;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class collectionContentFragment extends Fragment { //선택한 인물 보여주는 페이지

    DBHelper helper;
    SQLiteDatabase db;
    Button commentButton;
    int pos;

    Cursor cursor;
    public collectionContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        helper = new DBHelper(getActivity());
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("select title,content from people "+ "order by _id",null);


        return inflater.inflate(R.layout.fragmnet_collection_content, container, false);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.commentButton:
                    //System.out.println("fuck");
                    commentFragment newFragment = new commentFragment();
                    Bundle args = new Bundle();
                    args.putInt("position", pos);
                    newFragment.setArguments(args);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_myhome_container, newFragment);
                    transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
                    transaction.commit();
                    break;
            }
        }
    };

    @Override
    public void onStart(){
        super.onStart();
        commentButton = getView().findViewById(R.id.commentButton);
        Bundle args = getArguments();
        if(args != null){
            pos = args.getInt("position");
            updateDefinitionView(pos);
        }
        commentButton.setOnClickListener(onClickListener);
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
