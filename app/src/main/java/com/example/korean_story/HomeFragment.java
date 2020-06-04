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

public class HomeFragment extends Fragment {    //첫화면에 랜덤으로 인물보여주는 페이지

    int randomNum;
    int count = 0;
//    Button commentButton;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){


        String name_text = "";
        String content_text = "";

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        DBHelper helper = new DBHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select title,content from people "+ "order by _id",null);
        count = cursor.getCount();

        randomNum = (int)(Math.random()*count);

        for(int i=0; i<=randomNum; i++){
            cursor.moveToNext();
        }
        name_text = cursor.getString(0);
        content_text = cursor.getString(1);

        TextView name = view.findViewById(R.id.name);
        TextView summary = view.findViewById(R.id.summary);

        name.setText(name_text);
        summary.setText(content_text);

        return view;
    }

//    @Override
//    public void onStart(){
//        super.onStart();
//        commentButton = getView().findViewById(R.id.commentButton);
//        commentButton.setOnClickListener(onClickListener);
//    }

//    View.OnClickListener onClickListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View v){
//            switch (v.getId()){
//                case R.id.commentButton:
//                    commentFragment newFragment = new commentFragment();
//                    Bundle args = new Bundle();
//                    args.putInt("position", randomNum);
//                    newFragment.setArguments(args);
////
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, newFragment);
////                    transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
////                    transaction.commit();
//                    System.out.println(randomNum);
//                    break;
//            }
//        }
//    };
}
