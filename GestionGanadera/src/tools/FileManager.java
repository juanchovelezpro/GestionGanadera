package tools;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class FileManager {

	public static final String PATH = System.getProperty("user.home") + "/App Ganado/db/";

	public static final HashMap<String, Image> imagenes = new HashMap<>();

	public static void saveFile(File selectedFile, String target) {

		Path from = Paths.get(selectedFile.toURI());

		
		Path to = Paths.get(target);

		try {
			Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Data saved.");
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

	public static void cargarRecursos() {

		
		imagenes.put("ICONO", ImageLoader.cargarImagen("imagenes/icono.png"));
		imagenes.put("VACA", ImageLoader.cargarImagen("imagenes/vaca.png"));
		imagenes.put("FINCA", ImageLoader.cargarImagen("imagenes/finca.png"));
		imagenes.put("BORRAR", ImageLoader.cargarImagen("imagenes/borrar.png"));
		imagenes.put("BUSCAR", ImageLoader.cargarImagen("imagenes/buscar.png"));
		imagenes.put("CALENDAR", ImageLoader.cargarImagen("imagenes/calendar.png"));
		imagenes.put("REPORTE", ImageLoader.cargarImagen("imagenes/reporte.png"));
		imagenes.put("USUARIO", ImageLoader.cargarImagen("imagenes/usuario.png"));
		imagenes.put("TRASLADAR", ImageLoader.cargarImagen("imagenes/trasladar.png"));
		imagenes.put("VACUNA", ImageLoader.cargarImagen("imagenes/vacuna.png"));
		imagenes.put("PURGANTE", ImageLoader.cargarImagen("imagenes/purgante.png"));
		imagenes.put("ESTADISTICA", ImageLoader.cargarImagen("imagenes/presentacion.png"));
		imagenes.put("RES",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/icono.png"), new Dimension(15, 15)));
		imagenes.put("VACUNITA",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/vacuna.png"), new Dimension(15, 15)));
		imagenes.put("PURGANTICO",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/purgante.png"), new Dimension(15, 15)));
		imagenes.put("EXPORTAR", ImageLoader.cargarImagen("imagenes/exportar.png"));
		imagenes.put("IMPORTAR", ImageLoader.cargarImagen("imagenes/importar.png"));
		imagenes.put("EXCEL", ImageLoader.cargarImagen("imagenes/excel.png"));
		imagenes.put("BACKUP", ImageLoader.cargarImagen("imagenes/backup.png"));
		imagenes.put("ELIMINAR",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/borrar.png"), new Dimension(15, 15)));
		imagenes.put("TRASLADADA",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/trasladar.png"), new Dimension(15, 15)));
		imagenes.put("CREDITOS",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/usuario.png"), new Dimension(15, 15)));
		imagenes.put("STATS",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/presentacion.png"), new Dimension(15, 15)));
		//imagenes.put("CAMPO",ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/fondocampo.jpg"), new Dimension(1200,400)));

		imagenes.put("CAMPO", ImageLoader.cargarImagen("imagenes/fondocampo.jpg"));
		
		imagenes.put("CAMPO2", ImageLoader.cargarImagen("imagenes/campo2.jpg"));
		
		imagenes.put("CAMPO3", ImageLoader.cargarImagen("imagenes/campo3.jpg"));




	}

	public static boolean directoryProjectExists() {

		return new File(PATH).exists();
	}

	public static void createDirectoryProject() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
