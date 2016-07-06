package com.zju.rascontroller;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class QueryChartActivity extends Activity {
	 private LinearLayout oxygenCurveLayout;//���ͼ��Ĳ�������
	 private GraphicalView chartView;//ͼ��
	 private ChartDrawer oxygenChart;
	 
	 public static void actionStart(Context context,String data)
	 {
		 Intent intent = new Intent(context,QueryChartActivity.class);
		 intent.putExtra("date", data);
		 context.startActivity(intent);
	 }

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_chart);
		Intent intent = getIntent();
		String dateQuery = intent.getStringExtra("date");
		oxygenCurveLayout = (LinearLayout) findViewById(R.id.query_curve);
		oxygenChart = new ChartDrawer(this);
		oxygenChart.setXYMultipleSeriesDataset("����ֵ����ϵ��");
		oxygenChart.setXYMultipleSeriesRenderer(24, 15, "����ֵ��ѯ����ͼ("+dateQuery+")", "ʱ��(hour)", "����ֵ(mg/L)",
				Color.BLACK, Color.BLUE, Color.RED, Color.GRAY);
		chartView = oxygenChart.getGraphicalView();
		//��ͼ����ӵ�����������
		oxygenCurveLayout.addView(chartView, new LayoutParams(
		            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		Uri uri = Uri.parse("content://com.zju.rascontroller.provider/oxygen");
		Cursor cursor = getContentResolver().query(uri, new String[]{"timeX","oxygen"}, 
				 "dateQ=?", new String[]{dateQuery}, "timeX");
		List<Double> xData = new ArrayList<Double>();
		List<Double> yData = new ArrayList<Double>();
		if(cursor!=null)
		 {
			 while(cursor.moveToNext())
			 {
				 xData.add(cursor.getDouble(cursor.getColumnIndex("timeX")));
				 yData.add(cursor.getDouble(cursor.getColumnIndex("oxygen")));
				 
			 }
			 cursor.close();
		 }
		 oxygenChart.updateChart(xData,yData);
	 }
	@Override
	protected void onDestroy() {
			super.onDestroy();
	} 
}
