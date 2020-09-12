package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import tools.FileManager;

public class SQLConnection {

	private static SQLConnection instance;
	private Statement statement;
	private Connection connection;

	private SQLConnection() {

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

	public Statement getStatement() {

		return statement;

	}

	public Connection getConnection() {

		return connection;

	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void createDatabase() {

		try {
			statement.executeUpdate(
					"CREATE TABLE \"usuarios\" ( \"nombre\" TEXT, \"password\" TEXT, \"ubicacion\" TEXT, \"nombreFinca\" TEXT, \"email\" TEXT, \"serialNumber\" TEXT, \"fechaLimite\" TEXT )");
			statement.executeUpdate(
					"CREATE TABLE \"potreros\" ( \"nombre\" TEXT NOT NULL UNIQUE, PRIMARY KEY(\"nombre\") )");
			statement.executeUpdate(
					"CREATE TABLE \"res\" ( \"numero\" TEXT NOT NULL UNIQUE, \"tipo\" TEXT, \"genero\" TEXT NOT NULL, \"color\" TEXT NOT NULL, \"fecha_nacimiento\" TEXT, \"observaciones\" TEXT, \"vivo\" INTEGER NOT NULL, \"embarazada\" INTEGER NOT NULL, \"fecha_embarazo\" TEXT, \"fecha_ultima_purgado\" TEXT, \"fecha_ultima_vacunado\" TEXT, \"madreID\" TEXT, \"potreroNombre\" TEXT, FOREIGN KEY(\"potreroNombre\") REFERENCES \"potreros\"(\"nombre\"), PRIMARY KEY(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"vacunas\" ( \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"nombre\") )");
			statement.executeUpdate("CREATE TABLE \"purgantes\" ( \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"nombre\") )");
			statement.executeUpdate(
					"CREATE TABLE \"res_tiene_pesos\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"peso\" REAL, \"fecha\" TEXT, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");
			statement.executeUpdate(
					"CREATE TABLE \"res_tiene_purgantes\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"purganteNombre\" TEXT, \"fecha\" TEXT, FOREIGN KEY(\"purganteNombre\") REFERENCES \"purgantes\"(\"nombre\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\"), PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate(
					"CREATE TABLE \"res_tiene_vacunas\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"vacunaNombre\" TEXT, \"fecha\" TEXT, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"vacunaNombre\") REFERENCES \"vacunas\"(\"nombre\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");

			// Para activar errores de "Foreign key constraint"
			statement.executeUpdate("PRAGMA foreign_keys = ON; ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
