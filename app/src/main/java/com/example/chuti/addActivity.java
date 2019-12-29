package com.example.chuti;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class addActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add,del;
    private EditText editText;
    private RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add=findViewById(R.id.button3);
        del=findViewById(R.id.button4);
        editText=findViewById(R.id.editText);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);

        add.setOnClickListener(this);
        del.setOnClickListener(this);
        editText.setOnClickListener(this);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        String text;
        int ans=0;

        DBhelper DBhelper=new DBhelper(this);
        switch (v.getId()){
            case R.id.button3:
                text=editText.getText().toString();
                if (text.length()==0){
                    Toast.makeText(getApplicationContext(),"请输入题目",Toast.LENGTH_SHORT).show();
                }else {
                if (radioButton1.isChecked()){
                    ans=-1;
                }
                if (radioButton2.isChecked()){
                    ans=-2;
                }
                if (ans==0){
                    Toast.makeText(getApplicationContext(),"请选择答案",Toast.LENGTH_SHORT).show();
                }else {
//                    if (a== 0) {
//                        Toast.makeText(getApplicationContext(), "请选择答案", Toast.LENGTH_SHORT).show();
//                    } else {

                        ContentValues contentValues = new ContentValues();
                        String t = text;
//                        int a = ans;
                        contentValues.put("timu", t);
                        contentValues.put("answer", ans);
                        DBhelper.insert2(contentValues);

                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                }
                }
                break;

            case R.id.button4:
                editText.setText("");
                Toast.makeText(getApplicationContext(),"清除成功",Toast.LENGTH_LONG).show();
                break;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m2:
                Intent intent1 = new Intent();
                intent1.setClass(addActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
        return true;
    }

}
