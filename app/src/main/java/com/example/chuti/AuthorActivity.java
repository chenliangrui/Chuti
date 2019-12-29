package com.example.chuti;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class AuthorActivity extends AppCompatActivity implements View.OnClickListener {

    Button button,btn_show;
    private ImageView imageView;
    private Button open;
    private Bitmap bitmap;
    private String Path="http://n.sinaimg.cn/sinacn15/133/w640h1093/20180412/e5bf-fyzeyqc0653546.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button=findViewById(R.id.button);
        btn_show=findViewById(R.id.button5);
        imageView=findViewById(R.id.imageView);
        button.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        open=findViewById(R.id.button7);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        intent.setClass(AuthorActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String phoneNumber="18306497109";
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(dialIntent);
            break;
            case R.id.button5:
//                ImageView imageView=new ImageView(getApplicationContext());
//                imageView.setBackgroundResource(R.id.imageView);

                ImgLoadTask imgLoadTask=new ImgLoadTask(imageView);
                imgLoadTask.execute("http://n.sinaimg.cn/sinacn15/133/w640h1093/20180412/e5bf-fyzeyqc0653546.jpg");
//                getWindow().getDecorView().setBackgroundDrawable(new BitmapDrawable(imgLoadTask.doInBackground()));
                DonwloadSaveImg.donwloadImg(AuthorActivity.this,Path);//iPath
        }
    }
}
