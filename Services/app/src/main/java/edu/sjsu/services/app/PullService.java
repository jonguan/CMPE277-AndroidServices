package edu.sjsu.services.app;

import android.app.Service;
import android.os.Binder;
import android.os.IBinder;
import android.content.Intent;
import android.widget.Toast;
import java.net.URL;
import java.util.Timer;


/**
 * Created by jonguan on 2/20/14.
 */
public class PullService extends Service {

    int counter = 0;
    public URL[] urls;

    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();


    // Class used for the client Binder. Service runs on same thread as client.
    public class LocalBinder extends Binder {
        PullService getService() {
            // Return this instance of PullService
            return PullService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return binder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly stopped, so return sticky.
//        Toast.makeText(this, "PDF Service Started", Toast.LENGTH_LONG).show();
//        LogUtil.appendLog(this, "PDF service started");
//
//
//        String uri = intent.getDataString();
//        LogUtil.appendLog(this, "Downloading Url " + uri );
//        new DownloadTask().execute(uri);
//        Toast.makeText(this, "PDF Service Completed", Toast.LENGTH_LONG).show();
//            new DoBackgroundTask().execute(
//                    new URL("http://hanuinnotech.com/whitepapers/MuraiDataAnalytics(MDAT)-WhitePaper.pdf"),
//                    new URL("http://hanuinnotech.com/whitepapers/MuraiDataAnalytics(MDAT)-WhitePaper.pdf"),
//                    new URL("http://hanuinnotech.com/whitepapers/MuraiDataAnalytics(MDAT)-WhitePaper.pdf"),
//                    new URL("http://hanuinnotech.com/whitepapers/MuraiDataAnalytics(MDAT)-WhitePaper.pdf"));

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


    public void downloadUrls(String... urls) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        LogUtil.appendLog(this, "Service started");


        for (String uri : urls) {
            LogUtil.appendLog(this, "Downloading Url " + uri );
            new DownloadTask().execute(uri);
        }


        Toast.makeText(this, "Service Completed", Toast.LENGTH_LONG).show();
    }


}

