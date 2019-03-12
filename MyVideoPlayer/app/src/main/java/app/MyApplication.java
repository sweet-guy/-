package app;

import android.app.Application;

/**
 * Created by Umbrella on 2019/2/25.
 */

public class MyApplication extends Application{
    private static MyApplication appinstance;
    @Override
    public void onCreate() {
        super.onCreate();
        appinstance=this;
    }
    public static MyApplication getInstance() {
        return appinstance;
    }
}
