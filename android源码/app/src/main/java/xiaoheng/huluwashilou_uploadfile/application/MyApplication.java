package xiaoheng.huluwashilou_uploadfile.application;

import android.app.*;
import android.content.*;

public class MyApplication extends Application
{
    /*系统上下文*/
    private static Context appcontext;

    @Override
    public void onCreate()
	{
        super.onCreate();
        appcontext = getApplicationContext();
    }

    /**获取系统上下文：用于工具类*/
    public static Context getAppContext()
    {
        return appcontext;
    }
}
