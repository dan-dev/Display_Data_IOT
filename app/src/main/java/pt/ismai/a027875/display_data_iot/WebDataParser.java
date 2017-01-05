package pt.ismai.a027875.display_data_iot;


import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebDataParser extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String str = "";
        URL url = null;
        Scanner scanner;
        try {
            url = new URL("http://192.168.1.100/data.html");
            scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                str += scanner.nextLine();
                Log.d("line:", str);
            }
            scanner.close();
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
