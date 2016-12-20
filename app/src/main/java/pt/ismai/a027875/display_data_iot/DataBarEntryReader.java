package pt.ismai.a027875.display_data_iot;


import android.os.AsyncTask;
import android.os.Environment;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBarEntryReader extends AsyncTask<String, Void, List<BarEntry>>{
    @Override
    protected List<BarEntry> doInBackground(String... params) {
        File memory = Environment.getExternalStorageDirectory();
        File file = new File(memory, params[0]);
        List<BarEntry> lista = new ArrayList<BarEntry>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                lista.add(new BarEntry(i, line.length()));
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
