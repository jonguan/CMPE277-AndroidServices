package edu.sjsu.services.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.lang.Object;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;


/**
 * Created by jonguan on 2/20/14.
 */
public class LogUtil extends Activity {


    public static synchronized void appendLog(Context con, String text)
    {
//        File dataDir = Environment.getDataDirectory();
        File dataDir = con.getFilesDir();



        File logFile = new File(dataDir, "log.txt");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            String timestamp = new Date().toString();
            String className = con.getClass().getSimpleName();
            buf.append(timestamp + " " + className + " " + text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
