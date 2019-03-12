package service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Umbrella on 2019/2/25.
 */

public class ItentService extends Service{
    public ItentService() {
    }
    private String data="123456";
    private Intent intent = new Intent("com.wd.communication.RECEIVER");
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
    public class Binder extends android.os.Binder{
        public void setData(String data)
        {
            ItentService.this.data=data;
            Log.d("setData*-*第二种方式数据*-*-*-",data);
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("onCreate*-*第二种方式数据",data);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        String data = intent.getStringExtra("data");
        Log.d("onStartCommand*Service",data);
        startsend();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startsend() {
        intent.putExtra("NewData","广播传递的数据");
        sendBroadcast(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
