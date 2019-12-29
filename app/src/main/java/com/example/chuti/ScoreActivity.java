package com.example.chuti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {
    ListView Listview;
    List<Map<String, Object>> list;
    String S,D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Listview = findViewById(R.id.listview);
        final DBhelper helper = new DBhelper(this);
        list=new ArrayList<Map<String,Object>>();
        Cursor c = helper.query();
        if (c!=null) {
            while (c.moveToNext()) {
                Map<String,Object> map = new HashMap<String, Object>();
                S=c.getString(c.getColumnIndex("Score"));
                D=c.getString(c.getColumnIndex("Date"));
                map.put("Score", S);
                map.put("Date", D);
                list.add(map);
            }
        }
       String[] from = {"Score", "Date"};
        int[] to = {R.id.textView8, R.id.textView9};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.vlist1, c, from, to, 1);
        Listview.setAdapter(adapter);
        Delete();
    }

    private void Delete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final DBhelper helper =new DBhelper(this);
        Listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, final long arg3) {
                final long temp = arg3;
                builder.setMessage("真的要删除记录吗")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                helper.del((int) temp);
                                Cursor c = helper.query();
                                if (c!=null) {
                                    while (c.moveToNext()) {
                                        Map<String,Object> map = new HashMap<String, Object>();
                                        S=c.getString(c.getColumnIndex("Score"));
                                        D=c.getString(c.getColumnIndex("Date"));
                                        map.put("Score", S);
                                        map.put("Date", D);
                                        list.add(map);
                                    }
                                }
                                String[] from = {"Score", "Date"};
                                int[] to = {R.id.textView8, R.id.textView9};
                                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.vlist1, c, from, to, 1);
                                Listview.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("否", null);
                AlertDialog ad = builder.create();
                ad.show();
                return true;
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        intent.setClass(ScoreActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
