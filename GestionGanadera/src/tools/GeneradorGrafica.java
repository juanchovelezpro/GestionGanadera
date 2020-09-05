package tools;

import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Peso;

public class GeneradorGrafica {

	public static ChartPanel graficaPesos(ArrayList<Peso> pesos) {

		Collections.sort(pesos);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (!pesos.isEmpty()) {

			int counter = 5;

			if (pesos.size() < 5) {
				for (int i = 0; i < pesos.size() && counter > 0; i++, counter--) {

					Peso peso = pesos.get(i);

					dataset.addValue(peso.getPeso(), "PESOS (KG)", peso.getFecha());

				}
			} else {

				for (int i = pesos.size() - 5; i < pesos.size() && counter > 0; i++, counter--) {

					Peso peso = pesos.get(i);

					dataset.addValue(peso.getPeso(), "PESOS (KG)", peso.getFecha());

				}

			}

		}

		JFreeChart grafica = ChartFactory.createLineChart("PESOS", "FECHA", "PESO (KG)", dataset,
				PlotOrientation.VERTICAL, true, true, true);

		return new ChartPanel(grafica);

	}

}
