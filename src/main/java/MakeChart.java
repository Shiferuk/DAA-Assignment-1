import features.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MakeChart {

    public static void main(String[] args) {
        String csvFile = "results.csv";
        String plotsFolder = "plots";

        DefaultCategoryDataset timeDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset depthDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset ratioDataset = new DefaultCategoryDataset();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                String algorithm = parts[0].trim();
                String input = parts[1].trim();
                String nStr = parts[2].trim();
                long n = Long.parseLong(nStr);
                double timeMs = Double.parseDouble(parts[3].trim());
                double comparisons = Double.parseDouble(parts[4].trim());
                double maxDepth = Double.parseDouble(parts[5].trim());

                String seriesName = algorithm + " (" + input + ")";

                timeDataset.addValue(timeMs, seriesName, nStr);

                depthDataset.addValue(maxDepth, seriesName, nStr);

                double ratio;
                if (algorithm.equalsIgnoreCase("QuickSelect")) {
                    ratio = comparisons / n;
                } else {
                    double log2n = Math.log(n) / Math.log(2);
                    ratio = comparisons / (n * log2n);
                }
                ratioDataset.addValue(ratio, seriesName, nStr);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        saveChart("Time vs n", "n", "Time (ms)", timeDataset, plotsFolder + "/time_vs_n.png");
        saveChart("Max recursion depth vs n", "n", "Max depth", depthDataset, plotsFolder + "/depth_vs_n.png");
        saveChart("Ratio vs n", "n", "Ratio", ratioDataset, plotsFolder + "/ratio_vs_n.png");

        System.out.println("Все 3 графика успешно сохранены в папку 'plots'!");
    }

    private static void saveChart(String title, String categoryAxisLabel, String valueAxisLabel,
                                  DefaultCategoryDataset dataset, String outputPath) {
        JFreeChart chart = ChartFactory.createLineChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        try {
            ChartUtils.saveChartAsPNG(new File(outputPath), chart, 900, 600);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения " + outputPath + ": " + e.getMessage());
        }
    }
}