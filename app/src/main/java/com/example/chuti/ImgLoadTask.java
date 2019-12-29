package com.example.chuti;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgLoadTask extends AsyncTask<String,Integer, Bitmap> {

    private ImageView imageView;

    //为什么要加一个构造方法--有传值的需求
    public ImgLoadTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public Bitmap doInBackground(String... strings) {

        //加载网络图片，最后获取到一个Bitmap对象，返回Bitmap对象
        Bitmap bm = null;
        try {
            //创建URL对象
            URL url = new URL(strings[0]);
            //通过URL对象得到HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//这边需要强制转换
            //得到输入流
            InputStream inputStream = connection.getInputStream();
            //把输入流转换成Bitmap类型对象
            bm = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);

    }
}
