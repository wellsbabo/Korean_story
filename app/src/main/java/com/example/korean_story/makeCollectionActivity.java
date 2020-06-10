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
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class makeCollectionActivity extends AppCompatActivity {

    //Button makeCollection;
    ListView listView;
    ArrayAdapter adapter;
    List<Integer> collection = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_collection);

        collection.clear();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,android.R.id.text1);
        listView = findViewById(R.id.content_list);

        listView.setAdapter(adapter);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title,content from people "+ "order by _id",null);

        while(cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
        }
        db.close();

        //makeCollection = (Button)findViewById(R.id.makeButton);


    }

    public void click(View v){
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        int count = adapter.getCount();

        for(int i=0; i <count; i++){
            if(checkedItems.get(i)){
                collection.add(i);
            }
        }
        System.out.println(collection);
        listView.clearChoices();
    }
}
