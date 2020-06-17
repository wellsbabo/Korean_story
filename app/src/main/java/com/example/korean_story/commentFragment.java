package com.example.korean_story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class commentFragment extends Fragment {

    Button inputButton;
    EditText commentEdit;
    ListView commentList;
    int pos;



    public commentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        inputButton = (Button) getView().findViewById(R.id.inputComment);
        commentEdit = (EditText)getView().findViewById(R.id.editComment);
        commentList = (ListView)getView().findViewById(R.id.commentList);
        Bundle args = getArguments();
        if(args != null){
            pos = args.getInt("position");
            System.out.println(pos);
            comment(pos);
        }
        inputButton.setOnClickListener(onClickListener);
    }

    public void comment(int position){

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);
        commentList.setAdapter(adapter);
        int pos = position+1;

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("message").child(Integer.toString(pos)).child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMsg chatMsg = dataSnapshot.getValue(ChatMsg.class);
                adapter.add(chatMsg.getChat());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            int position = pos+1;
            switch (v.getId()){
                case R.id.inputComment:
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    ChatMsg chatMsg = new ChatMsg(commentEdit.getText().toString());
                    //database.child("message").child(Integer.toString(pos)).child("chat").push().setValue(chatMsg);
                    database.child("message").child(Integer.toString(position)).child("chat").push().setValue(chatMsg);
                    commentEdit.setText(null);
                    startToast("댓글이 등록되었습니다");
                    break;
            }
        }
    };

    private void startToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
