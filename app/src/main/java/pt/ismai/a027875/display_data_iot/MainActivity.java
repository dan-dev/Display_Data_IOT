package pt.ismai.a027875.display_data_iot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LineChart lchartTemp = (LineChart) findViewById(R.id.temp_linechart);
        final LineChart lchartECG = (LineChart) findViewById(R.id.ecg_linechart);
        final ImageView imageView = (ImageView) findViewById(R.id.position_image);
        final TextView positionText = (TextView) findViewById(R.id.position_text);
        final HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.respiration_linechart);

        imageView.setImageResource(R.drawable.sitting);

        positionText.setText("\t\t\tPosition:\n" + "\t\t\t\tSitting");

        /*
        List<Entry> valuesEntry = new ArrayList<Entry>();

        Entry a1 = new Entry(0, 100);
        Entry a2 = new Entry(1, 150);
        Entry a3 = new Entry(2, 125);
        Entry a4 = new Entry(3, 100);
        Entry a5 = new Entry(4, 200);
        Entry a6 = new Entry(5, 125);

        valuesEntry.add(a1);
        valuesEntry.add(a2);
        valuesEntry.add(a3);
        valuesEntry.add(a4);
        valuesEntry.add(a5);
        valuesEntry.add(a6);
        */

        //region #Region - Temperature

        Legend legendTemp = lchartTemp.getLegend();
        legendTemp.setEnabled(false);

        lchartTemp.setBackgroundColor(Color.BLACK);
        LineData ldataTemp = new LineData();

        ldataTemp.setValueTextColor(Color.GREEN);
        lchartTemp.setData(ldataTemp);

        LineDataSet linDtsetTemp = null;
        try {
            linDtsetTemp = new LineDataSet(new DataReader().execute("temp.txt").get(), "Temperature");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ldataTemp.addDataSet(linDtsetTemp);
        lchartTemp.setData(ldataTemp);
        lchartTemp.invalidate();

        //endregion Temperature


        //region #Region - ECG

        Legend legendECG = lchartECG.getLegend();
        legendECG.setEnabled(false);
        lchartECG.setBackgroundColor(Color.BLACK);

        LineData ldataECG = new LineData();
        ldataECG.setValueTextColor(Color.GREEN);

        LineDataSet linDtsetECG = null;
        try {
            linDtsetECG = new LineDataSet(new DataReader().execute("ECG.txt").get(), "ECG");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ldataECG.addDataSet(linDtsetECG);
        lchartECG.setData(ldataECG);
        lchartECG.invalidate();

        //endregion


        //region #Region - Respiration

        Legend barLegend = barChart.getLegend();
        barLegend.setEnabled(false);

        BarDataSet barSet = null;
        try {
            barSet = new BarDataSet(new DataBarEntryReader().execute("Airflow.txt").get(), "Respiration");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BarData barData = new BarData(barSet);
        barChart.setData(barData);

        //endregion

        /*
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 20));
        barEntries.add(new BarEntry(1, 40));
        barEntries.add(new BarEntry(2, 60));
        barEntries.add(new BarEntry(3, 100));
        barEntries.add(new BarEntry(4, 60));
        barEntries.add(new BarEntry(5, 40));
        barEntries.add(new BarEntry(6, 20));
        */


        File memory = Environment.getExternalStorageDirectory();

        File file = new File(memory, "temp.txt");

        StringBuilder data = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while((line = reader.readLine()) != null){
                data.append(line + "\n" + "------------" + "\n");
            }
            reader.close();
        }
        catch (IOException exception){

        }
        //temp.setText(data.toString());

    }
}
