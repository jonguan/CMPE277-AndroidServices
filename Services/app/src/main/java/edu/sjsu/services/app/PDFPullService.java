package edu.sjsu.services.app;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jonguan on 2/20/14.
 */
public class PDFPullService extends IntentService {

    public PDFPullService (){
        super("PDFPullService");
    }

    @Override
    protected void onHandleIntent (Intent workIntent) {
        String uri = workIntent.getDataString();
        LogUtil.appendLog(this, "Downloading Url " + uri );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "pdf service starting", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }
}
