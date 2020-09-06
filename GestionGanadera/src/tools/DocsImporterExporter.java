package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db.PotreroCRUD;
import db.ResCRUD;
import model.Res;
import view.BarraProgresoDialog;
import view.PotrerosPanel;

public class DocsImporterExporter {

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

		BarraProgresoDialog progreso = new BarraProgresoDialog(countRows(sheet));

		Iterator<Row> rows = sheet.iterator();
		rows.next();

		new Thread() {

			@Override
			public void run() {

				int value = 0;
				ArrayList<Res> reses = new ArrayList<>();

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

						case 7:
							String vivo = cell.toString().split("\\.")[0];
							if (vivo.equals("1"))
								res.setVivo(1);
							else
								res.setVivo(0);
							break;
						}

						contadorCeldas++;

					}

					res.setPotreroNombre(potreroNombre.toUpperCase().trim());
					System.out.println(res);
					reses.add(res);

					value++;
					progreso.getProgreso().setValue(value);

				}

				ResCRUD.insertMultiple(reses);

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

	public static void exportPotrero(String potreroNombre, String destino) {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(potreroNombre);

		ArrayList<Res> reses = PotreroCRUD.selectRes(potreroNombre);

		Row firstRow = sheet.createRow(0);
		firstRow.createCell(0).setCellValue("No");
		firstRow.createCell(1).setCellValue("GENERO");
		firstRow.createCell(2).setCellValue("TIPO");
		firstRow.createCell(3).setCellValue("COLOR");
		firstRow.createCell(4).setCellValue("VIVO");
		firstRow.createCell(5).setCellValue("FECHA NACIMIENTO");
		firstRow.createCell(6).setCellValue("EMBARAZADA");
		firstRow.createCell(7).setCellValue("FECHA EMBARAZO");
		firstRow.createCell(8).setCellValue("MADRE");
	

		for (int i = 1; i < reses.size(); i++) {
			Row myRow = sheet.createRow(i);
			for (int j = 0; j < 10; j++) {

				Cell myCell = myRow.createCell(j);

				switch (j) {

				case 0:
					myCell.setCellValue(reses.get(i).getResID());
					break;

				case 1:
					myCell.setCellValue(reses.get(i).getGenero());
					break;
				case 2:
					myCell.setCellValue(reses.get(i).getTipo());
					break;
				case 3:
					myCell.setCellValue(reses.get(i).getColor());
					break;
				case 4:
					myCell.setCellValue(reses.get(i).getVivo());
					break;
				case 5:
					myCell.setCellValue(reses.get(i).getFecha_nacimiento());
					break;
				case 6:
					if (reses.get(i).getGenero().equals("H"))
						myCell.setCellValue(reses.get(i).getEmbarazada());
					else
						myCell.setCellValue("NO APLICA");
					break;
				case 7:
					if (reses.get(i).getGenero().equals("H"))
						myCell.setCellValue(reses.get(i).getFecha_embarazo());
					else
						myCell.setCellValue("");
					break;
				case 8:
					myCell.setCellValue(reses.get(i).getMadreID());
					break;
				

				}

			}
		}

		try {
			FileOutputStream output = new FileOutputStream(destino);
			wb.write(output);
			wb.close();
			output.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void exportarPartos(String destino) {

		ArrayList<Res> resesPartos = ResCRUD.reportePartos();

		XSSFWorkbook wb = new XSSFWorkbook();

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "-" + mes + "-" + anio;
		XSSFSheet sh = wb.createSheet("Reporte de partos  " + "" + fecha_Convertida);

		Row firstRow = sh.createRow(0);
		firstRow.createCell(0).setCellValue("NUMERO");
		firstRow.createCell(1).setCellValue("FECHA EMBARAZO");
		firstRow.createCell(2).setCellValue("TIEMPO DE EMBARAZO");
		firstRow.createCell(3).setCellValue("POTRERO");

		for (int i = 1; i < resesPartos.size(); i++) {
			Row myRow = sh.createRow(i);
			for (int j = 0; j < 4; j++) {

				Cell myCell = myRow.createCell(j);

				switch (j) {

				case 0:
					myCell.setCellValue(resesPartos.get(i).getResID());
					break;

				case 1:
					myCell.setCellValue(resesPartos.get(i).getFecha_embarazo());
					break;
				case 2:
					myCell.setCellValue(ResCRUD.calcDate(resesPartos.get(i).getFecha_embarazo()));
					break;
				case 3:
					myCell.setCellValue(resesPartos.get(i).getPotreroNombre());
					break;

				}
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(destino);
			wb.write(output);
			wb.close();
			output.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void exportarDestete(String destino) {

		ArrayList<Res> resesDestete = ResCRUD.reporteDestete();

		XSSFWorkbook wb = new XSSFWorkbook();

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "-" + mes + "-" + anio;

		XSSFSheet sh = wb.createSheet("Reporte de destete  " + fecha_Convertida);

		Row firstRow = sh.createRow(0);
		firstRow.createCell(0).setCellValue("NUMERO");
		firstRow.createCell(1).setCellValue("NACIMIENTO");
		firstRow.createCell(2).setCellValue("TIEMPO DE CRIA");
		firstRow.createCell(3).setCellValue("MADRE");
		firstRow.createCell(4).setCellValue("POTRERO");

		for (int i = 1; i < resesDestete.size(); i++) {
			Row myRow = sh.createRow(i);
			for (int j = 0; j < 5; j++) {

				Cell myCell = myRow.createCell(j);

				switch (j) {

				case 0:
					myCell.setCellValue(resesDestete.get(i).getResID());
					break;

				case 1:
					myCell.setCellValue(resesDestete.get(i).getFecha_nacimiento());
					break;
				case 2:
					myCell.setCellValue(ResCRUD.calcDate(resesDestete.get(i).getFecha_nacimiento()));
					break;
				case 3:
					myCell.setCellValue(resesDestete.get(i).getPotreroNombre());
					break;

				}
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(destino);
			wb.write(output);
			wb.close();
			output.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void exportarVacunas(String destino) {

		ArrayList<Res> resesVacuna = ResCRUD.reporteVacunaNotificaciones();

		XSSFWorkbook wb = new XSSFWorkbook();

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "-" + mes + "-" + anio;

		XSSFSheet sh = wb.createSheet("Reporte de vacunas  " + fecha_Convertida);

		Row firstRow = sh.createRow(0);
		firstRow.createCell(0).setCellValue("NUMERO");
		firstRow.createCell(1).setCellValue("VACUNA");
		firstRow.createCell(2).setCellValue("ULTIMA FECHA");
		firstRow.createCell(3).setCellValue("POTRERO");

		for (int i = 1; i < resesVacuna.size(); i++) {
			Row myRow = sh.createRow(i);
			for (int j = 0; j < 4; j++) {

				Cell myCell = myRow.createCell(j);

				switch (j) {

				case 0:
					myCell.setCellValue(resesVacuna.get(i).getResID());
					break;

				case 1:
					myCell.setCellValue(resesVacuna.get(i).getVacunas().peek().getNombre());
					break;
				case 2:
					myCell.setCellValue(resesVacuna.get(i).getVacunas().peek().getFecha());
					break;
				case 3:
					myCell.setCellValue(resesVacuna.get(i).getPotreroNombre());
					break;

				}
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(destino);
			wb.write(output);
			wb.close();
			output.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void exportarPurgantes(String destino) {

		ArrayList<Res> resesVacuna = ResCRUD.reportePurgado();

		XSSFWorkbook wb = new XSSFWorkbook();

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "-" + mes + "-" + anio;
		XSSFSheet sh = wb.createSheet("Reporte de purgantes  " + fecha_Convertida);

		Row firstRow = sh.createRow(0);
		firstRow.createCell(0).setCellValue("NUMERO");
		firstRow.createCell(1).setCellValue("PURGANTE");
		firstRow.createCell(2).setCellValue("ULTIMA FECHA");
		firstRow.createCell(3).setCellValue("POTRERO");

		for (int i = 1; i < resesVacuna.size(); i++) {
			Row myRow = sh.createRow(i);
			for (int j = 0; j < 4; j++) {

				Cell myCell = myRow.createCell(j);

				switch (j) {

				case 0:
					myCell.setCellValue(resesVacuna.get(i).getResID());
					break;

				case 1:
					myCell.setCellValue(resesVacuna.get(i).getPurgantes().peek().getNombre());
					break;
				case 2:
					myCell.setCellValue(resesVacuna.get(i).getPurgantes().peek().getFecha());
					break;
				case 3:
					myCell.setCellValue(resesVacuna.get(i).getPotreroNombre());
					break;

				}
			}
		}

		try {
			FileOutputStream output = new FileOutputStream(destino);
			wb.write(output);
			wb.close();
			output.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// 1 destete - 2 partos 3- purgantes 4-vacuna
	public static void exportarReporte(int valor, String destino) {

		switch (valor) {
		case 1:

			exportarDestete(destino);

			break;

		case 2:
			exportarPartos(destino);

			break;

		case 3:

			exportarVacunas(destino);

			break;

		case 4:

			exportarPurgantes(destino);

			break;

		}
	}

	public static void exportarTodo(String destino) {

	}

}
