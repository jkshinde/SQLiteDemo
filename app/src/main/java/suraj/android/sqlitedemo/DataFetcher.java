package suraj.android.sqlitedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Tata Strive on 10/9/2017.
 */
public class DataFetcher extends IntentService
{
    public DataFetcher()
    {
        super("intent service");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {

    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("Service Created");
    }

    @Override
    public void onStart(Intent intent, int startid)
    {
        super.onStart(intent, startid);
    }

    @Override
    public void onDestroy()
    {
        System.out.println("Service Destroyed");
        super.onDestroy();
    }
}
