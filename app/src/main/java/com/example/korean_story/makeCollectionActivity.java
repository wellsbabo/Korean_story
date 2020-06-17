package com.example.korean_story;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class makeCollectionActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    List<Integer> collection = new ArrayList<Integer>();
    private FirebaseAuth mAuth;
    String uid;
    EditText collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_collection);

        collection.clear();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,android.R.id.text1);
        listView = findViewById(R.id.content_list);

        listView.setAdapter(adapter);

        collectionName = (EditText)findViewById(R.id.collection_name);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title,content from people "+ "order by _id",null);

        while(cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
        }
        db.close();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

    }

    public void click(View v){
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        int count = adapter.getCount();
        String collection_name = collectionName.getText().toString();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        for(int i=0; i <count; i++){
            if(checkedItems.get(i)){
                collection.add(i);
            }
        }

        String collection_content = collection.toString();
        collection_content = collection_content.replace("[","");
        collection_content = collection_content.replace("]","");
        collection_content = collection_content.replace(" ","");

        Collection collection_pack  = new Collection(collection_name,collection_content);


        database.child("User").child(uid).child("collection").push().setValue(collection_pack);
        startToast("컬렉션 생성 완료");
        System.out.println(collection_pack);
        listView.clearChoices();
        adapter.notifyDataSetChanged();
        finish();
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

