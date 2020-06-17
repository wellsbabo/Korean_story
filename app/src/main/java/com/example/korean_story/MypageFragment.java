package com.example.korean_story;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class MypageFragment extends Fragment {  //로그아웃 및 나만의 컬렉션

    Button logout;
    Button makeCollection;
    Button sendOpinionButton;
    private FirebaseAuth mAuth;
    List<String> collection_list = new ArrayList();

    public MypageFragment(){}

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_mypage,container,false);

        logout = (Button)view.findViewById(R.id.logoutButton);
        logout.setOnClickListener(onClickListener);

        makeCollection = (Button)view.findViewById(R.id.makeCollectionButton);
        makeCollection.setOnClickListener(onClickListener);

        sendOpinionButton = (Button)view.findViewById(R.id.sendOpinionButton);
        sendOpinionButton.setOnClickListener(onClickListener);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        System.out.println(uid);

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);
        ListView listView = view.findViewById(R.id.collection_list);

        listView.setAdapter(adapter);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("User").child(uid).child("collection").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Collection collection = dataSnapshot.getValue(Collection.class);
                collection_list.add(collection.getContent());
                adapter.add(collection.getName());
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                collectionFragment newFragment = new collectionFragment();
                Bundle args = new Bundle();
                args.putString("position", collection_list.get(position));
                newFragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_myhome_container, newFragment);
                transaction.addToBackStack(null);   //기존의 프레그먼트는 백스택에 넣음
                transaction.commit();
            }
        });
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startLoginActivity();
                    break;
                case R.id.makeCollectionButton:
                    startMakeCollectionActivity();
                    break;
                case R.id.sendOpinionButton:
                    startSendOpinionActivity();
                    break;
            }
        }
    };

    private void startLoginActivity(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
    }

    private void startMakeCollectionActivity(){
        Intent intent = new Intent(getActivity(),makeCollectionActivity.class);
        startActivity(intent);
    }

    private void startSendOpinionActivity(){
        Intent intent = new Intent(getActivity(),sendOpinionActivity.class);
        startActivity(intent);
    }
}