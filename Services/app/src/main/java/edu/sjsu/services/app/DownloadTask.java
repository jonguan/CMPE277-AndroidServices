package edu.sjsu.services.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonguan on 2/21/14.
 */
public class DownloadTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        Log.v(MainActivity.LOG_TAG, "download complete " + result);
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves the web page content as a InputStream, which it returns as a string.
    private String downloadUrl(String path) throws IOException {


        try {
            //this is the file you want to download from the remote server
//            String path ="http://www.fullissue.com/wp-content/uploads/2010/12/Adam-Lambert.jpg";
            //this is the name of the local file you will create

            String FILEPATH = "/data/data/edu.sjsu.services.app/files/";
            int index = path.lastIndexOf("/");
            String targetFileName = FILEPATH + path.substring(index);

            boolean eof = false;

            URL u = new URL(path);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH_op = targetFileName;

            FileOutputStream f = new FileOutputStream(new File(PATH_op));

            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ( (len1 = in.read(buffer)) > 0 ) {
                f.write(buffer,0, len1);
            }

            f.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            Log.v(MainActivity.LOG_TAG, e.getMessage());
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "done " + path;
    }
}

