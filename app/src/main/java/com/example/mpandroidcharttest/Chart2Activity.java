package com.example.mpandroidcharttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chart2Activity extends AppCompatActivity {

    private PieChart piechart;

   // private Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);

        piechart = (PieChart) findViewById(R.id.mPieChart);

        /**
         * 是否使用百分比
         */
        piechart.setUsePercentValues(true);
        /**
         * 描述信息
         */
        //piechart.setDescription("描述信息");

     // tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        //piechart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

        /**
         * 圆环距离屏幕上下上下左右的距离
         */
        piechart.setExtraOffsets(5f, 5.f, 5.f, 5.f);

        /**
         * 是否显示圆环中间的洞
         */
        piechart.setDrawHoleEnabled(false);

        /**
         * 设置圆环透明度及半径
         */
        piechart.setTransparentCircleColor(Color.YELLOW);
        piechart.setTransparentCircleAlpha(60);
        piechart.setTransparentCircleRadius(30f);

        /**
         *触摸是否可以旋转以及松手后旋转的度数
         */
        piechart.setRotationAngle(20);
        // enable rotation of the chart by touch
        piechart.setRotationEnabled(true);

        // piechart.setUnit(" €");
        // piechart.setDrawUnitsInChart(true);

        setData();

        Legend l = piechart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setEnabled(true);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        l.setForm(Legend.LegendForm.CIRCLE);

        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);


    }

    private void setData() {

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(40, "优秀"));
        entries.add(new PieEntry(20, "满分"));
        entries.add(new PieEntry(30, "及格"));
        entries.add(new PieEntry(10, "不及格"));


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //   dd
        //dataSet.setSelectionShift(0f);


        //outside
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.7f);
        dataSet.setValueLinePart2Length(0.3f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        PieData data = new PieData(dataSet);
        //设置百分比
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        //data.setValueTypeface(tf);
        //设置X显示的内容 这里为了满足UI 设置空
     /*   ArrayList<String > a = new ArrayList<>();
        a.add("");*/
        //data.setXVals(a);
        piechart.setData(data);


        // undo all highlights
        piechart.highlightValues(null);

        piechart.invalidate();
    }

}


