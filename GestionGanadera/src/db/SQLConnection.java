package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

import tools.FileManager;

public class SQLConnection {

	private static SQLConnection instance;
	private Statement statement;

	private SQLConnection() {

		Connection connection = null;
		try {
		
			FileManager.createDirectoryProject();
			connection = DriverManager.getConnection("jdbc:sqlite:" + FileManager.PATH + "database.db");
			statement = connection.createStatement();
		} catch (SQLException e) {

			System.err.println(e.getMessage());
		}

	}

	public static synchronized SQLConnection getInstance() {
		if (instance == null) {
			instance = new SQLConnection();
		}
		return instance;
	}

	public void createDatabase() {

		try {
			statement.executeUpdate("CREATE TABLE \"usuarios\" ( \"nombre\" TEXT, \"password\" TEXT, \"ubicacion\" TEXT, \"nombreFinca\" TEXT )");
			statement.executeUpdate("CREATE TABLE \"vacunas\" ( \"id\" INTEGER, \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"potreros\" ( \"nombre\" TEXT NOT NULL UNIQUE, PRIMARY KEY(\"nombre\") )");		
			statement.executeUpdate("CREATE TABLE \"purgantes\" ( \"id\" INTEGER, \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"res\" ( \"numero\" INTEGER NOT NULL UNIQUE, \"genero\" TEXT NOT NULL, \"color\" TEXT NOT NULL, \"fecha_nacimiento\" TEXT, \"observaciones\" TEXT, \"vivo\" INTEGER NOT NULL, \"embarazada\" INTEGER NOT NULL, \"fecha_embarazo\" INTEGER, \"fecha_ultima_purgado\" TEXT, \"fecha_ultima_vacunado\" TEXT, \"madreID\" INTEGER, PRIMARY KEY(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"potreros_tiene_res\" ( \"id\" INTEGER UNIQUE, \"potreroNombre\" TEXT, \"resID\" INTEGER, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\"), FOREIGN KEY(\"potreroNombre\") REFERENCES \"potreros\"(\"nombre\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_pesos\" ( \"id\" INTEGER, \"resID\" INTEGER NOT NULL, \"peso\" REAL NOT NULL, \"fecha\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_vacunas\" ( \"id\" INTEGER, \"resID\" INTEGER, \"vacunaID\" INTEGER, \"fecha\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"vacunaID\") REFERENCES \"vacunas\"(\"id\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_purgantes\" ( \"id\" INTEGER, \"resID\" INTEGER, \"purganteID\" INTEGER, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\"), FOREIGN KEY(\"purganteID\") REFERENCES \"purgante\"(\"id\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_crias\" ( \"id\" INTEGER, \"madreID\" INTEGER, \"criaID\" INTEGER, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"madreID\") REFERENCES \"res\"(\"numero\"), FOREIGN KEY(\"criaID\") REFERENCES \"res\"(\"numero\") )");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void consulta(Statement statement) throws SQLException {

		ResultSet rs = statement.executeQuery("select * from person");
		String result = "";
		while (rs.next()) {

			// read the result set
			result += "name = " + rs.getString("name") + "\n";
			result += "id = " + rs.getInt("id") + "\n";
		}

		JOptionPane.showMessageDialog(null, result);

	}

	public void agregar(Statement statement) throws SQLException {

		Random ran = new Random();
		String name = JOptionPane.showInputDialog("Introduzca un nombre");
		statement.executeUpdate("insert into person values(" + ran.nextInt(1000) + ",'" + name + "')");

	}

}
