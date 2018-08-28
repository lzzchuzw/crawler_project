package com.jingdong.test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

public class JMathPlotTest {
	
	public static void main(String[] args) {
		//Plot2DPanel plot =new Plot2DPanel();
		//drawTest();
		drawTest2();
		//Plot3DPanel plot3d = new Plot3DPanel();
	}
	
	public static void drawTest() {
	/*	//jplot(rand(10,2),-t line', -n data');
		//jplot(rand(10,3),'-t bar',' -n data');
		double[] x = {15.0,13.0};
		double[] y ={15.0,13.0};
		// create your PlotPanel (you can use it as a JPanel)
*/		Plot2DPanel plot =new Plot2DPanel();
				 // add a line plot to the PlotPanel 
		/*plot.addLinePlot("my plot", x, y);
				 // put the PlotPanel in a JFrame, as a JPanel
		JFrame frame =new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);*/
	}
	
	public static void drawTest2() {
		JFrame frame = new JFrame("JMathPlot library in a swing application.");
		JPanel panel = new JPanel();
		double[] x = new double[]{0, 1, 2, 3, 4, 5};
        double[] y = new double[]{10, 11, 12, 14, 15, 16};
        Plot3DPanel plot = new Plot3DPanel("plot3d");
        //plot.addLinePlot("my plot", x, y); // add a line plot to the PlotPanel
        panel.setLayout(new BorderLayout());
        panel.add(plot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocation(150, 150);
        frame.setVisible(true);
	}

}
