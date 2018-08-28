package com.jingdong.test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.math.plot.Plot2DPanel;

public class MyPlot {
	private JFrame frame = new JFrame("JMathPlot library in a swing application.");
    private JPanel panel = new JPanel();

    public MyPlot() {
        double[] x = new double[]{0, 1, 2, 3, 4, 5};
        double[] y = new double[]{10, 11, 12, 14, 15, 16};
        Plot2DPanel plot = new Plot2DPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 200);
            }
        };
        plot.addLinePlot("my plot", x, y); // add a line plot to the PlotPanel
        panel.setLayout(new BorderLayout());
        panel.add(plot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocation(150, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyPlot();
            }
        });
    }
}
