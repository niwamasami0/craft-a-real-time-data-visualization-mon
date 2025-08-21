import java.awt.*;
import javax.swing.*;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import org.jfree.chart.axis.*;

public class RealTimeDataVisualizationMonitor {
    private JFrame frame;
    private JFreeChart chart;
    private XYPlot plot;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private XYDataset dataset;
    private Timer timer;

    public RealTimeDataVisualizationMonitor() {
        frame = new JFrame("Real-Time Data Visualization Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dataset = new XYDataset() {
            @Override
            public int getItemCount() {
                return 100;
            }

            @Override
            public Number getX(int index) {
                return index;
            }

            @Override
            public Number getY(int index) {
                return Math.random() * 100;
            }
        };

        xAxis = new NumberAxis("Time");
        yAxis = new NumberAxis("Value");
        plot = new XYPlot(dataset, xAxis, yAxis, new LineAndShapeRenderer(true, true));
        chart = new JFreeChart(plot);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        timer = new Timer(1000, e -> {
            dataset = new XYDataset() {
                @Override
                public int getItemCount() {
                    return 100;
                }

                @Override
                public Number getX(int index) {
                    return index;
                }

                @Override
                public Number getY(int index) {
                    return Math.random() * 100;
                }
            };
            chart.setDataset(dataset);
            chartPanel.repaint();
        });
        timer.start();

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RealTimeDataVisualizationMonitor::new);
    }
}