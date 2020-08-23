package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db.PotreroCRUD;
import db.ResCRUD;
import model.Res;

public class DocsImporter {

	public static void importData(FileInputStream file, String potreroNombre) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();
		rows.next();

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

		}

		workbook.close();

	}

}
