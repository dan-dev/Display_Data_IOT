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

public class DataReader extends AsyncTask<String, Void, List<Entry>>{

    @Override
    protected List<Entry> doInBackground(String... params) {
        File memory = Environment.getExternalStorageDirectory();
        File file = new File(memory, params[0]);
        List<Entry> lista = new ArrayList<Entry>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                String split[] = line.split(": ");
                if(params[0].equals("ECG.txt")){
                    String splitECG[] = split[1].split(" ");
                    Entry entry = new Entry(i, Float.parseFloat(splitECG[1].toString()));
                    lista.add(entry);
                }
                else{
                    Entry entry = new Entry(i, Float.parseFloat(split[1].toString()));
                    lista.add(entry);
                }
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