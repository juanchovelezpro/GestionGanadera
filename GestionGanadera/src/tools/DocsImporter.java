package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db.ResCRUD;
import model.Res;
import view.BarraProgreso;
import view.PotrerosPanel;

public class DocsImporter {

	public static int countRows(XSSFSheet sheet) {

		int carga = 0;

		Iterator<Row> rows = sheet.iterator();
		rows.next();

		while (rows.hasNext()) {

			carga++;
			rows.next();

		}

		return carga;

	}

	public static void importData(FileInputStream file, String potreroNombre, PotrerosPanel panel) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		BarraProgreso progreso = new BarraProgreso(countRows(sheet));

		Iterator<Row> rows = sheet.iterator();
		rows.next();

		new Thread() {

			@Override
			public void run() {

				int value = 0;

				while (rows.hasNext()) {

					Row row = rows.next();

					Iterator<Cell> cells = row.cellIterator();
					int contadorCeldas = 0;
					Res res = new Res();

					while (cells.hasNext()) {

						Cell cell = cells.next();

						switch (contadorCeldas) {

						case 0:
							res.setResID(cell.toString().split("\\.")[0]);
							break;

						case 1:
							res.setTipo(cell.toString().trim());
							break;

						case 2:
							res.setColor(cell.toString().toUpperCase().trim());
							break;

						case 3:

							res.setFecha_nacimiento(cell.toString().trim().replaceAll(" ", "").trim());
							break;

						case 4:
							res.setGenero(cell.toString().trim());
							break;

						case 5:
							res.setMadreID(cell.toString().replace(" ", "").split("\\.")[0]);
							break;

						case 6:
							res.setObservaciones(cell.toString().trim());
							break;

						}

						contadorCeldas++;

					}

					res.setPotreroNombre(potreroNombre.toUpperCase().trim());

					ResCRUD.insert(res);

					value++;
					progreso.getProgreso().setValue(value);

				}
				progreso.dispose();
				JOptionPane.showMessageDialog(null, "El archivo se ha cargado correctamente!", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				panel.refreshTable();

				try {
					workbook.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}.start();

	}

}
