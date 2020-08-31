package tools;

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
			Files.copy(from, to, StandardCopyOption.COPY_ATTRIBUTES);
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

	}

	public static boolean directoryProjectExists() {

		return new File(PATH).exists();
	}

	public static void createDirectoryProject() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
