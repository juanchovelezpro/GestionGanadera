package tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManager {

	public static final String PATH = System.getProperty("user.home") + "/App Ganado/db/";

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
	
	public static boolean directoryProjectExists() 
	{
		
		return new File(PATH).exists();
	}

	public static void createDirectoryProject() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
