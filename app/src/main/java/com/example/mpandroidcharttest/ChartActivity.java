package com.example.mpandroidcharttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        final ArrayList<String> mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add((i+1)+"月");
        }

        //-------------------------------------
        LineChart mLineChart = (LineChart) findViewById(R.id.lineChart);
        //显示边界
        mLineChart.setDrawBorders(true);
        //设置数据
        XAxis xAxis =  mLineChart.getXAxis();
        YAxis leftYAxis = mLineChart.getAxisLeft();
        YAxis rightYAxis = mLineChart.getAxisRight();

        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(100f);

        rightYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMaximum(100f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "%";
            }
        });

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));

        }


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        //设置x轴显示数量
        xAxis.setLabelCount(12,false);
        //xAxis.setAxisMaximum(20f);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        LineData data = new LineData(lineDataSet);
        mLineChart.setData(data);


        //-------------------------------------
        //BarChart barChart = (BarChart)findViewById(R.id.barChart);
        BarChart barChart1 = (BarChart) findViewById(R.id.bar_chart1);
        BarChart barChart2 = (BarChart) findViewById(R.id.bar_chart2);

        BarChartManager barChartManager1 = new BarChartManager(barChart1);
        BarChartManager barChartManager2 = new BarChartManager(barChart2);

        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");

        //创建多条折线的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(1), colours.get(3));
        barChartManager2.showBarChart(xValues, yValues, names, colours);
        barChartManager2.setXAxis(11f, 0f, 11);



    }
}
