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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TextView temp = (TextView) findViewById(R.id.textTemp);

        final LineChart lchart = (LineChart) findViewById(R.id.temp_linechart);
        final LineChart lchart2 = (LineChart) findViewById(R.id.ecg_linechart);
        final ImageView imageView = (ImageView) findViewById(R.id.position_image);
        final TextView positionText = (TextView) findViewById(R.id.position_text);
        final HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.respiration_linechart);

        imageView.setImageResource(R.drawable.sitting);

        positionText.setText("\t\t\tPosition:\n" + "\t\t\t\tSitting");

        Legend legend = lchart.getLegend();
        legend.setEnabled(false);

        Legend legend2 = lchart2.getLegend();
        legend2.setEnabled(false);

        lchart.setBackgroundColor(Color.BLACK);
        LineData ldata = new LineData();

        ldata.setValueTextColor(Color.GREEN);

        lchart.setData(ldata);

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

        LineDataSet linDtset = new LineDataSet(valuesEntry, "resp");

        ldata.addDataSet(linDtset);
        lchart.setData(ldata);
        lchart.invalidate();

        lchart2.setBackgroundColor(Color.BLACK);
        lchart2.setData(ldata);
        lchart2.invalidate();


        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f, 20f));
        barEntries.add(new BarEntry(1f, 40f));
        barEntries.add(new BarEntry(2f, 60f));
        barEntries.add(new BarEntry(3f, 100f));
        barEntries.add(new BarEntry(4f, 60f));
        barEntries.add(new BarEntry(5f, 40f));
        barEntries.add(new BarEntry(6f, 20f));

        Legend barLegend = barChart.getLegend();
        barLegend.setEnabled(false);

        BarDataSet barSet = new BarDataSet(barEntries, "resp");

        BarData barData = new BarData(barSet);

        barChart.setData(barData);

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
