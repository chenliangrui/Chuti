package com.example.chuti;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    ListView LV;
    Button Start,Score;
    AlertDialog firstDialog;
    CountDownTimer myCountDownTimer;
    static List<Map<String, Object>> list;
    static Map<String,Object> map;
    int counter,score;
    static SortedSet<Integer> randomset;
    boolean isSelected;
    int resultcode;
    int random_ques[];
    private boolean isFirst = true;
    private SharedPreferences preferences;
    String[] ques;
    Integer[] result;

    String[] ques1={
            "Android广播机制包含广播，广播接收者和意图内容三个基本要素",
            "进程的优先级按从高到低的顺序分为前台进程，可见进程，服务进程，后台进程，空进程",
            "Activity用于提供可视化的用户界面，是Android应用中使用频率最高的组件",
            "Activity的生命周期包括激活或运行状态，开始状态，暂停状态，停止状态",
            "Android中的android.app.Activity类定义了Activity生命周期中所包含的全部方法",
            "不是所有Activity类在定义时都必须继承android.app.Activity",
            "Android中的界面元素主要由视图组件，视图容器，布局管理构成",
            "LinearLayout方向由android：orientation属性控制，属性值默认方向为垂直方向",
            "Toast是具有焦点的，但显示时间有限，过一定的时间会自动消失",
            "Android中提供了两种创建布局的方式：XML布局文件和代码直接实现",
            "将传递的数据存放到Extra属性，可直接调用putExtra（）方法，也可以先将数据封装到Bundle包中",
            "Service的启动方式和绑定方式不可以混合使用，二者完全独立",
            "Bundle传值主要通过putXXX方法将不同数据类型封装到Bundle对象中，再通过getXXX（）方法获取相应数据类型的数据",
            "优先级低的BroadcastReceiver可向优先级高的BroadcastReceiver发送新的广播内容",
            "Service在后台运行，不可以与用户直接交互",
            "Android中通过SQLite数据库实现结构化数据存储",
            "定义一个Service子类需要继承Service类，并实现其生命周期中的方法",
            "ContentProvider（内容提供器）的作用就是使得各个应用程序之间实现数据共享",
            "作为一个嵌套到Activity中的UI片段，Fragment可以独立存在或使用",
            "使用SharedPreferences.Editor的putXXX（）方法保存数据时，需要根据数据类型调用相应的putXXX（）方法",
            "LinearLayout只有垂直和水平两种布局方式",
            "简单游标适配器可以看作SimpleAdapter与数据库的简单结合",
            "ArrayAdapter比SimpleAdapter具备更好的扩展性，可自定义各种各样的布局",
            "LayoutManager是一个抽象类，该类有4种实现类",
            "ListView不可以直接设置监听事件，RecyclerView接口回调的方式实现",
            "EditText是用户与系统之间的文本输入接口，可以把用户输入的数据传给系统"

    };
    Integer result1[]={
            -1,
            -1,
            -1,
            -2,
            -1,
            -2,
            -1,
            -2,
            -2,
            -1,
            -1,
            -2,
            -1,
            -2,
            -1,
            -1,
            -1,
            -1,
            -2,
            -1,
            -1,
            -1,
            -2,
            -2,
            -2,
            -1

    };





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showData();
        Delete();
        Start = findViewById(R.id.button1);
        Score=findViewById(R.id.button2);
        Start.setOnClickListener(this);
        Score.setOnClickListener(this);

        preferences = getSharedPreferences("isFirst", MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirstIn", true);

        DBhelper DBhelper=new DBhelper(this);
        Cursor c = DBhelper.query1();
        ques = new String[c.getCount()];
        result =new Integer[c.getCount()];

        //将数据库里的数据保存到数组,用于Dialog中调用

        while (c.moveToNext()) {
            String D=c.getString(c.getColumnIndexOrThrow("timu"));
            int A=c.getInt(c.getColumnIndex("answer"));
            ques[c.getPosition()]=D;
            result[c.getPosition()]=A;
        }

        if (isFirst) {//如果是第一次启动
            for (int i=0;i<ques1.length;i++){
                ContentValues contentValues=new ContentValues();
                String t=ques1[i];
                int a=result1[i];
                contentValues.put("timu",t);
                contentValues.put("answer",a);
                DBhelper.insert2(contentValues);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();

        }
        else{
            showData();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m1:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, AuthorActivity.class);
                startActivity(intent1);
                return true;
            case R.id.m2:
                finish();
                return true;
            case R.id.m3:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, addActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Dialogshow(int which) {

        DBhelper DBhelper=new DBhelper(this);

        if (counter<11) {
            if (counter!=0&&which==result[random_ques[counter-1]]){
                score+=10;
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            TextView txv=new TextView(this);
            txv.setGravity(Gravity.CENTER);
            txv.setTextSize(40);
            builder.setTitle("快乐答题 gogogo");
            if (counter==10) //判断是否达到最大题目数
            {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                //获得系统时间
                int month = calendar.get(Calendar.MONTH)+1;
                //日
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //小时
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                //分钟
                int minute = calendar.get(Calendar.MINUTE);
                String string=year+"年"+month+"月"+day+"日 "+hour+":"+minute;
                builder.setMessage("你的得分为："+score+"分");
                ContentValues contentValues=new ContentValues();
                contentValues.put("Score", score);
                contentValues.put("Date", string);
                DBhelper.insert(contentValues);
                //保存得分到Score_tab
            }else{// 未达到最大题目数 继续下一题
                builder.setMessage(ques[random_ques[counter]]);
                }
            builder.setView(txv);
            if (counter<10) {
                builder.setPositiveButton("是", this);
                builder.setNegativeButton("不是", this);}
            isSelected=false;
            firstDialog=builder.create();
            firstDialog.show();
            counter++;
            countdown(txv, firstDialog);
            firstDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    firstDialog = null;
                    if (myCountDownTimer!=null) {
                        // 停止countDownTimer
                        myCountDownTimer.cancel();
                        myCountDownTimer=null;}
                    if (isSelected==true) {
                        Dialogshow(resultcode);//计算得分进入下一个问题展示页面
                        isSelected=false;
                    }else {
                        Dialogshow(0);
                    }
                }
            });
        }
    }

    public void countdown(TextView txv, AlertDialog Dialog) {
        final TextView txv_count = txv;
        txv_count.setTextColor(Color.rgb(255, 00, 00));
        final AlertDialog myDialog = Dialog;
        myCountDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txv_count.setText((millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                if (myDialog != null) {
                    myDialog.dismiss();
                }
            }
        };
        myCountDownTimer.start();
    }

    public static void Random_Num(int num_count, int num_range) {
        Random ran =new Random();
        for (int i = 0; i < num_count; i++) {
            randomset.add(ran.nextInt(num_range));
        }
        if (randomset.size() < num_count) {
            Random_Num(10- randomset.size(), num_range);
        }
    }

    private void Delete() {
        final DBhelper helper=new DBhelper(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, final long arg3) {


                LV = findViewById(R.id.listView);
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                Cursor c = helper.query1();
                //创建数组记录显示题号
                int Xh[]=new int[c.getCount()];
                for (int i=0;i<c.getCount();i++){
                    Xh[i]=i+1;
                }
                //显示ListView
                if (c!=null) {
                    while (c.moveToNext()) {
                        Map<String,Object> map = new HashMap<String, Object>();
                        String D=c.getString(c.getColumnIndex("timu"));
                        int S=c.getInt(c.getColumnIndex("_id"));
                        map.put("id", Xh[c.getPosition()]);
                        map.put("timu", D);
                        map.put("_id",S);
                        list.add(map);
                    }
                }
                final long temp = arg3;
                map = list.get((int) arg3);
                int id= (int) map.get("_id");
                builder.setMessage("真的要删除记录吗")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                helper.del1(id);
                                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_LONG).show();
                                showData();
                            }
                        })
                        .setNegativeButton("否", null);
                AlertDialog ad = builder.create();
                ad.show();

                while (c.moveToNext()) {
                    String D=c.getString(c.getColumnIndexOrThrow("timu"));
                    int A=c.getInt(c.getColumnIndex("answer"));
                    ques[c.getPosition()]=D;
                    result[c.getPosition()]=A;
                }

                return true;
            }
        });

    }

    public  void showData(){

        final DBhelper helper = new DBhelper(this);
        LV = findViewById(R.id.listView);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Cursor c = helper.query1();
        //创建数组记录显示题号
        int Xh[]=new int[c.getCount()];
        for (int i=0;i<c.getCount();i++){
            Xh[i]=i+1;
        }
        //显示ListView
        if (c!=null) {
            while (c.moveToNext()) {
                String duicuo = null;
                Map<String,Object> map = new HashMap<String, Object>();
                String D=c.getString(c.getColumnIndex("timu"));
                int A=c.getInt(c.getColumnIndex("answer"));
                if (A==-1){
                    duicuo="√";
                }else {
                    duicuo="×";
                }
                map.put("a",duicuo);
                map.put("id", Xh[c.getPosition()]);
                map.put("timu", D);
//                map.put("_id",S);
                list.add(map);
            }
        }
        String[] from = {"id", "timu","a"};
        int[] to = {R.id.textView1, R.id.textView2,R.id.textView12};
        SimpleAdapter adapter=new SimpleAdapter(this, list, R.layout.vlist, from, to);
        LV.setAdapter(adapter);

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        isSelected=true;
        resultcode=which;

    }

    @Override
    public void onClick(View v) {
        DBhelper helper=new DBhelper(this);

        //用来刷新题库
        Cursor c = helper.query1();
        ques = new String[c.getCount()];
        result =new Integer[c.getCount()];
        //将数据库里的数据保存到数组,用于Dialog中调用
        while (c.moveToNext()) {
            String D=c.getString(c.getColumnIndexOrThrow("timu"));
            int A=c.getInt(c.getColumnIndex("answer"));
            ques[c.getPosition()]=D;
            result[c.getPosition()]=A;
        }


        if (v == Start) {
            randomset = new TreeSet<Integer>();
            Random_Num(10, c.getCount());
            random_ques = new int[10];
            int j = 0;
            for (int i = 0; i < c.getCount(); i++) {
                if (randomset.contains(i)) {
                    random_ques[j] = i;
                    j++;
                }
            }
            counter = 0;
            score = 0;
            Dialogshow(0);
        }
        if (v== Score){
            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        showData();
        super.onResume();
    }


}
