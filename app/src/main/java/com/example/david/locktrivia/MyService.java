package com.meir.david.locktrivia;


        import android.app.Service;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.IBinder;




public class MyService extends Service {

    BroadcastReceiver bd;
    public MyService() {
    }

    class ScreenReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            Intent startupIntent = new Intent(context, ScreenJump.class);
            startupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startupIntent);
        }
        public ScreenReceiver()
        {

        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_USER_PRESENT);
        bd = new ScreenReceiver();
        registerReceiver(bd, filter);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}