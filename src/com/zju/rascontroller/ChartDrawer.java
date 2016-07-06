package com.zju.rascontroller;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class ChartDrawer {
     private GraphicalView myGraphicalView;
     private XYMultipleSeriesDataset myMultipleSeriesDataset;// ���ݼ�����
     private XYMultipleSeriesRenderer myMultipleSeriesRenderer;// ��Ⱦ������
     private XYSeries mySeries;// �����������ݼ�
     private XYSeriesRenderer myRenderer;// ����������Ⱦ��
     private Context context;
     public ChartDrawer(Context context) {
         this.context = context;
     }
 
     /**
      * ��ȡͼ��
      * 
      * @return
      */
     public GraphicalView getGraphicalView() {
         myGraphicalView = ChartFactory.getLineChartView(context,
                 myMultipleSeriesDataset, myMultipleSeriesRenderer);
         return myGraphicalView;
     }
     /**
      * ��ȡ���ݼ�����xy����ļ���
      * 
      * @param curveTitle
      */
     public void setXYMultipleSeriesDataset(String curveTitle) {
         myMultipleSeriesDataset = new XYMultipleSeriesDataset();
         mySeries = new XYSeries(curveTitle);
         myMultipleSeriesDataset.addSeries(mySeries);
     }
 
     /**
      * ��ȡ��Ⱦ��
      * 
      * @param maxX
      *            x�����ֵ
      * @param maxY
      *            y�����ֵ
      * @param chartTitle
      *            ���ߵı���
      * @param xTitle
      *            x�����
      * @param yTitle
      *            y�����
      * @param axeColor
      *            ��������ɫ
      * @param labelColor
      *            ������ɫ
      * @param curveColor
      *            ������ɫ
      * @param gridColor
      *            ������ɫ
      */
     public void setXYMultipleSeriesRenderer(double maxX, double maxY,
             String chartTitle, String xTitle, String yTitle, int axeColor,
             int labelColor, int curveColor, int gridColor) {
         myMultipleSeriesRenderer = new XYMultipleSeriesRenderer();
         if (chartTitle != null) {
             myMultipleSeriesRenderer.setChartTitle(chartTitle);//�������߱���
         }
         myMultipleSeriesRenderer.setXTitle(xTitle);//x���ǩ
         myMultipleSeriesRenderer.setYTitle(yTitle);//y���ǩ
         myMultipleSeriesRenderer.setRange(new double[] { 0, maxX, 0, maxY });//xy��ķ�Χ
         myMultipleSeriesRenderer.setLabelsColor(labelColor);//xy���ǩ�����߱������ɫ
         myMultipleSeriesRenderer.setXLabels(12);//����X���ǩ�ĸ���
         myMultipleSeriesRenderer.setYLabels(15);//����Y���ǩ�ĸ���
         myMultipleSeriesRenderer.setXLabelsAlign(Align.LEFT);//�����ֱ�ǩλ��
         myMultipleSeriesRenderer.setYLabelsAlign(Align.RIGHT);
         myMultipleSeriesRenderer.setAxisTitleTextSize(20);
         myMultipleSeriesRenderer.setChartTitleTextSize(30);
         myMultipleSeriesRenderer.setLabelsTextSize(20);
         myMultipleSeriesRenderer.setLegendTextSize(20);
         myMultipleSeriesRenderer.setPointSize(2f);//�������ߴ�
         myMultipleSeriesRenderer.setFitLegend(true);//Sets if the legend should size to fit. 
         myMultipleSeriesRenderer.setMargins(new int[] { 50, 45, 40, 10 });
         //the margin size values, in this order: top, left, bottom, right
         myMultipleSeriesRenderer.setShowGrid(true);
         myMultipleSeriesRenderer.setZoomEnabled(true, true);//X��Y����
         myMultipleSeriesRenderer.setZoomButtonsVisible(true);//��ʾ���Ź���
         myMultipleSeriesRenderer.setPanLimits(new double[] {0, 26, 0, 100});
         myMultipleSeriesRenderer.setZoomLimits(new double[] {0, 26, 0, 100});
         myMultipleSeriesRenderer.setAxesColor(axeColor);
         myMultipleSeriesRenderer.setGridColor(gridColor);
         myMultipleSeriesRenderer.setBackgroundColor(Color.WHITE);//����ɫ
         myMultipleSeriesRenderer.setMarginsColor(Color.BLACK);//�߾౳��ɫ��Ĭ�ϱ���ɫΪ��ɫ�������޸�Ϊ��ɫ
         myRenderer = new XYSeriesRenderer();
         myRenderer.setColor(curveColor);
         myRenderer.setPointStyle(PointStyle.CIRCLE);//����񣬿���ΪԲ�㣬���ε�ȵ�
//         mRenderer.setFillPoints(true);//��������Ƿ�Ϊʵ��
         myMultipleSeriesRenderer.addSeriesRenderer(myRenderer);
     }
 
     /**
      * �����¼ӵ����ݣ��������ߣ�ֻ�����������߳�
      * 
      * @param x
      *            �¼ӵ��x����
      * @param y
      *            �¼ӵ��y����
      */
     public void updateChart(double x, double y) {
         mySeries.add(x, y);
         myGraphicalView.repaint();//�˴�Ҳ���Ե���invalidate()
     } 
     
     /**
      * ����µ����ݣ����飬�������ߣ�ֻ�����������߳�
      * @param xList
      * @param yList
      */
     public void updateChart(List<Double> xList, List<Double> yList) {
    	 mySeries.clear();
         for (int i = 0; i < xList.size(); i++) {
             mySeries.add(xList.get(i), yList.get(i));
         }
         myGraphicalView.repaint();//�˴�Ҳ���Ե���invalidate()
     }
}
