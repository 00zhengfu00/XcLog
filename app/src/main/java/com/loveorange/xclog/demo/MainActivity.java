package com.loveorange.xclog.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loveorange.xclog.XcFileLog;

public class MainActivity extends AppCompatActivity {

    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onClick(View view){
        XcFileLog.getInstace().i("MainActivity", i+" 测试文件写入操作 ");
        i++;
        if(i==4){
            createEx(0);
        }
        else if(i==10){
            createEx2(0);
        }
    }

    private void createEx(int i){
        try{
            int c = 3/i;
        } catch (Exception e){
            XcFileLog.getInstace().e("MainActivity", e);
        }
    }

    private void createEx2(int i){
        int c = 3/i;
    }

}
