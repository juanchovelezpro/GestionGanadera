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
		
		Dimension minDim = new Dimension(15,15);
		Dimension minBtn = new Dimension(30,30);

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
		imagenes.put("VITAMINA", ImageLoader.cargarImagen("imagenes/vitamina.png"));
		imagenes.put("ESTADISTICA", ImageLoader.cargarImagen("imagenes/presentacion.png"));
		imagenes.put("RES",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/icono.png"), minDim));
		imagenes.put("VACUNITA",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/vacuna.png"), minDim));
		imagenes.put("PURGANTICO",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/purgante.png"), minDim));
		imagenes.put("EXPORTAR", ImageLoader.cargarImagen("imagenes/exportar.png"));
		imagenes.put("IMPORTAR", ImageLoader.cargarImagen("imagenes/importar.png"));
		imagenes.put("EXCEL", ImageLoader.cargarImagen("imagenes/excel.png"));
		imagenes.put("BACKUP", ImageLoader.cargarImagen("imagenes/backup.png"));
		imagenes.put("ELIMINAR",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/borrar.png"), minDim));
		imagenes.put("TRASLADADA",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/trasladar.png"), minDim));
		imagenes.put("CREDITOS",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/usuario.png"), minDim));
		imagenes.put("STATS",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/presentacion.png"), minDim));

		imagenes.put("VITAMINITA", ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/vitamina.png"), minDim));
		
		imagenes.put("RES2",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/icono.png"), minBtn));
		imagenes.put("VACUNITA2",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/vacuna.png"), minBtn));
		imagenes.put("PURGANTICO2",
				ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/purgante.png"), minBtn));
		imagenes.put("VITAMINITA2", ImageLoader.resizeToFit(ImageLoader.cargarSprites("imagenes/vitamina.png"), minBtn));

		imagenes.put("CAMPO", ImageLoader.cargarImagen("imagenes/fondocampo.jpg"));

		imagenes.put("CAMPO2", ImageLoader.cargarImagen("imagenes/campo2.jpg"));

		imagenes.put("CAMPO3", ImageLoader.cargarImagen("imagenes/campo3.jpg"));

		imagenes.put("VACAS", ImageLoader.cargarImagen("imagenes/vacas.jpg"));

		imagenes.put("CAMPONO", ImageLoader.cargarImagen("imagenes/campono.jpg"));

		imagenes.put("RESESREPORTE", ImageLoader.cargarImagen("imagenes/resesreporte.jpg"));

		imagenes.put("COPY", ImageLoader.cargarImagen("imagenes/copy.jpg"));

		imagenes.put("DEVELOPER", ImageLoader.cargarImagen("imagenes/developer.jpg"));

		imagenes.put("PAISAJE", ImageLoader.cargarImagen("imagenes/paisaje.jpg"));

		imagenes.put("POTREROS", ImageLoader.cargarImagen("imagenes/potrerosFondo.jpg"));

		imagenes.put("LLANO", ImageLoader.cargarImagen("imagenes/llanoAgregar.jpg"));

		imagenes.put("VAQUITA", ImageLoader.cargarImagen("imagenes/vaquita.png"));

	}

	public static boolean directoryProjectExists() {

		return new File(PATH).exists();
	}

	public static void createDirectoryProject() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
