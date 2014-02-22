package edu.sjsu.services.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;

public class ImageDownloadActivity extends ActionBarActivity {

    PDFPullService pullService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_image_download, container, false);
            return rootView;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PDFPullService.class);
        // Bind to LocalService
        bindService(intent, mConnection, Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            PDFPullService.LocalBinder binder = (PDFPullService.LocalBinder) service;
            pullService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void downloadImages(View view) {
        // Button clicked
        LogUtil.appendLog(this, "Button clicked to download images");

        // Set intent
//        Intent intent = new Intent(this, PDFPullService.class);
        String uri1 = ((EditText)findViewById(R.id.imageUrl1)).getText().toString();
        String uri2 = ((EditText)findViewById(R.id.imageUrl2)).getText().toString();
        String uri3 = ((EditText)findViewById(R.id.imageUrl3)).getText().toString();
        String uri4 = ((EditText)findViewById(R.id.imageUrl4)).getText().toString();
        String uri5 = ((EditText)findViewById(R.id.imageUrl5)).getText().toString();
        String uri6 = ((EditText)findViewById(R.id.imageUrl6)).getText().toString();
        String uri7 = ((EditText)findViewById(R.id.imageUrl7)).getText().toString();
        String uri8 = ((EditText)findViewById(R.id.imageUrl8)).getText().toString();
        String uri9 = ((EditText)findViewById(R.id.imageUrl9)).getText().toString();
        String uri10 = ((EditText)findViewById(R.id.imageUrl10)).getText().toString();
//        intent.setData(Uri.parse(uri1));


        String[] s = {uri1, uri2, uri3, uri4, uri5, uri6, uri7, uri8, uri9, uri10 };
        LogUtil.appendLog(this, "Downloading text Urls ");
//        pullService.onStartCommand(intent, 0, 0);
        pullService.downloadUrls(s);
    }

}
