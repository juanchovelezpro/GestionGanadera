package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

		PotreroCRUD.insert(potreroNombre);

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
					res.setResID(cell.toString());
					break;

				case 1:
					res.setTipo(cell.toString());
					break;

				case 2:
					res.setColor(cell.toString().toUpperCase().trim());
					break;

				case 3:

					res.setFecha_nacimiento(cell.toString());
					break;

				case 4:
					res.setGenero(cell.toString());
					break;

				case 5:
					res.setMadreID(cell.toString());
					break;

				case 6:
					res.setObservaciones(cell.toString());
					break;

				}

				contadorCeldas++;

			}

			res.setPotreroNombre(potreroNombre.toUpperCase().trim());
			
			ResCRUD.insert(res);

		}

		workbook.close();
		
		System.out.println("FINISH");

	}

//	public static void main(String[] args) {
//
//		JFrame frame = new JFrame();
//		JButton but = new JButton("Import");
//
//		but.addActionListener(e -> {
//
//			JFileChooser fileChooser = new JFileChooser();
//			fileChooser.showOpenDialog(null);
//			fileChooser.isVisible();
//
//			FileInputStream fs = null;
//
//			try {
//				if (fileChooser.getSelectedFile() != null) {
//					fs = new FileInputStream(fileChooser.getSelectedFile());
//
//				} else {
//
//					JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo", "Error",
//							JOptionPane.ERROR_MESSAGE);
//
//				}
//
//				if (fs != null) {
//
//					// Cargar definitivamente el excel al programa y realizar todos los calculos y
//					// procesos.
//
//					importData(fs, "YERBABUENA JULIO");
//					JOptionPane.showMessageDialog(null, "El archivo se ha cargado correctamente!", "Info",
//							JOptionPane.INFORMATION_MESSAGE);
//					fs.close();
//
//				}
//
//			} catch (Exception ex) {
//
//				ex.printStackTrace();
//			}
//
//		});
//
//		frame.getContentPane().add(but);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//	}

}
