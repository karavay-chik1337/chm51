
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SimpleGUI extends JFrame {

    private static final int RENDER_QUALITY = 200;

    private static final JButton button = new JButton("Построить");
    private static final JTextField inputA = new JTextField("0.6", 10);
    private static final JTextField inputB = new JTextField("10", 10);
    private static final JTextField inputN = new JTextField("1000", 10);
    private static final JLabel labelA = new JLabel("Левый конец отрезка(a): ");
    private static final JLabel labelB = new JLabel("Правый конец отрезка(b): ");
    private static final JLabel labelN = new JLabel("Количество разбиений(n): ");
    private static final JLabel text = new JLabel("");

    private String graphType;

    public SimpleGUI() {

        super("CHM5");
        this.setBounds(100, 100, 400, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        buttonGroup1.add(inputSeparation1);
//        buttonGroup1.add(inputSeparation2);
//
//        buttonGroup2.add(inputMethod1);
//        buttonGroup2.add(inputMethod2);
//
//        inputSeparation1.doClick();
//        inputMethod1.doClick();

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(9, 1, 2, 2));
        container.add(labelA);
        container.add(inputA);
        container.add(labelB);
        container.add(inputB);
        container.add(labelN);
        container.add(inputN);
//        container.add(labelSeparation);
        container.add(text);
//        container.add(inputSeparation1);
//        container.add(inputSeparation2);
        container.add(text);
//        container.add(labelMethod);
//        container.add(inputMethod1);
//        container.add(inputMethod2);

        button.addActionListener(e -> {
                    Section section = new Section(
                            Double.parseDouble(inputA.getText()),
                            Double.parseDouble(inputB.getText()),
                            Integer.parseInt(inputN.getText())
                    );
                    Function function = new Function();
                    TypeMethod typeMethod = new TypeMethod(function);
                    JFreeChart[] graphs = createGraphs(section, function, typeMethod);
                    displayGraphs(graphs);
                }
        );
        container.add(button);
    }

    private void displayGraphs(JFreeChart[] graphs) {
        JFrame graphFrame = new JFrame(graphType);
        graphFrame.setSize(1500, 800);
        graphFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        graphFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel graphPanel = new JPanel(new GridLayout(1, 3));
        scrollPane.setViewportView(graphPanel);

        for (JFreeChart graph : graphs) {
            ChartPanel chartPanel = new ChartPanel(graph, true);
            graphPanel.add(chartPanel);
        }
        graphFrame.setVisible(true);
    }

    private JFreeChart[] createGraphs(Section section, Function function, TypeMethod typeMethod) {

        XYSeries sourceFunction = new XYSeries("f(x)");
        XYSeries rungeFunction = new XYSeries("runge");
        XYSeries eulerFunction = new XYSeries("euler");
        double[] xValues = section.uniformPart();
        double[] yValueR = typeMethod.runge(section.a, section.b, section.n);
        double[] yValueE = typeMethod.euler(section.a, section.b, section.n);
        for (int i = 0; i < section.n; i++) {
            sourceFunction.add(xValues[i], function.CorrectF(xValues[i]));
            rungeFunction.add(xValues[i], yValueR[i]);
            eulerFunction.add(xValues[i], yValueE[i]);
        }
//        System.out.println(function.fTochnoe(xValues[9]) + " " + yValueR[9] + " " + yValueE[9]);
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");

        xAxis.setAutoRange(false);
        yAxis.setAutoRange(false);

        xAxis.setRange(xValues[0] - 1, xValues[xValues.length-1] + 1);
        yAxis.setRange(function.y0 - 1, function.CorrectF(xValues[xValues.length-1]) + 1);

        XYSeriesCollection bothFunc = new XYSeriesCollection();
        bothFunc.addSeries(sourceFunction);
        bothFunc.addSeries(rungeFunction);
        bothFunc.addSeries(eulerFunction);
        // Создаем графики
        JFreeChart chartF = ChartFactory.createXYLineChart("f(x)", "X", "Y",
                new XYSeriesCollection(sourceFunction), PlotOrientation.VERTICAL, true, true, false);

        JFreeChart chartRunge = ChartFactory.createXYLineChart("Графики", "X", "Y",
                bothFunc, PlotOrientation.VERTICAL, true, true, false);

        JFreeChart chartEuler = ChartFactory.createXYLineChart("euler", "X", "Y",
                bothFunc, PlotOrientation.VERTICAL, true, true, false);

        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setSeriesLinesVisible(0, true);
        renderer1.setSeriesShapesVisible(0, true);
        renderer1.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer1.setSeriesPaint(0, Color.RED);

        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesLinesVisible(0, true);
        renderer2.setSeriesShapesVisible(0, true);
        renderer2.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer2.setSeriesPaint(0, Color.GREEN);

        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
        renderer3.setSeriesLinesVisible(0, true);
        renderer3.setSeriesShapesVisible(0, true);
        renderer3.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer3.setSeriesPaint(0, Color.BLUE);

        chartF.getXYPlot().setDomainAxis(xAxis);
        chartF.getXYPlot().setRangeAxis(yAxis);
        chartF.getXYPlot().setRenderer(renderer1);

        chartRunge.getXYPlot().setDomainAxis(xAxis);
        chartRunge.getXYPlot().setRangeAxis(yAxis);
        chartRunge.getXYPlot().setRenderer(renderer2);

        chartEuler.getXYPlot().setDomainAxis(xAxis);
        chartEuler.getXYPlot().setRangeAxis(yAxis);
        chartEuler.getXYPlot().setRenderer(renderer3);


        return new JFreeChart[] {
//                chartF,
                chartRunge
//                chartEuler
        };
    }
}
