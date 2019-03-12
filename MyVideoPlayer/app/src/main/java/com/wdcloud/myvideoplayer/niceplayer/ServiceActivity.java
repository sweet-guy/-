package com.wdcloud.myvideoplayer.niceplayer;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wdcloud.myvideoplayer.R;

import service.ItentService;

public class ServiceActivity extends AppCompatActivity{
    private Intent intent;
    private Button start_service;
    private Button end_service;
    private Button binder;
    private ItentService.Binder binder1=null;
    private Button unbind;
    ServiceConnection serviceConnection=new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder1 = (ItentService.Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("失败-=-=-=-=-=-=-=-=",name.toString());
        }
    };
    private MyMsgReceiver myMsgReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        myMsgReceiver = new MyMsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wd.communication.RECEIVER");
        registerReceiver(myMsgReceiver,intentFilter);
        start_service = findViewById(R.id.start_service);
        end_service = findViewById(R.id.end_service);
        unbind = findViewById(R.id.unbind);
        binder = findViewById(R.id.binder);
        intent = new Intent(ServiceActivity.this, ItentService.class);
        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent.putExtra("data","传递的数据");
                  startService(intent);
//                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });
        end_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
        binder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binder1!=null)
                {
                    binder1.setData("第二种方式-=-=-=-=-=-传过来了");
                }
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        binder1 = (ItentService.Binder) service;
//
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//        Log.d("失败-=-=-=-=-=-=-=-=",name.toString());
//    }
    public class MyMsgReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String newData = intent.getStringExtra("NewData");
        Log.d("78945678978*-0*-*-*-**-",newData);
    }
}
}
