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

	public void createDatabase() {

		try {
			statement.executeUpdate("CREATE TABLE \"potreros\" ( \"nombre\" TEXT NOT NULL UNIQUE, PRIMARY KEY(\"nombre\") )");
			statement.executeUpdate("CREATE TABLE \"usuarios\" ( \"nombre\" TEXT, \"password\" TEXT, \"ubicacion\" TEXT, \"nombreFinca\" TEXT )");
			statement.executeUpdate("CREATE TABLE \"res\" ( \"numero\" TEXT NOT NULL UNIQUE, \"tipo\" TEXT, \"genero\" TEXT NOT NULL, \"color\" TEXT NOT NULL, \"fecha_nacimiento\" TEXT, \"observaciones\" TEXT, \"vivo\" INTEGER NOT NULL, \"embarazada\" INTEGER NOT NULL, \"fecha_embarazo\" TEXT, \"fecha_ultima_purgado\" TEXT, \"fecha_ultima_vacunado\" TEXT, \"madreID\" TEXT, PRIMARY KEY(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"vacunas\" ( \"id\" INTEGER, \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"purgantes\" ( \"id\" INTEGER, \"nombre\" TEXT NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"potreros_tiene_res\" ( \"id\" INTEGER UNIQUE, \"potreroNombre\" TEXT, \"resID\" TEXT, FOREIGN KEY(\"potreroNombre\") REFERENCES \"potreros\"(\"nombre\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\"), PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_crias\" ( \"id\" INTEGER UNIQUE, \"madreID\" TEXT, \"criaID\" TEXT, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"madreID\") REFERENCES \"res\"(\"numero\"), FOREIGN KEY(\"criaID\") REFERENCES \"res\"(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_pesos\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"peso\" REAL, \"fecha\" TEXT, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_purgantes\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"purganteNombre\" TEXT, \"fecha\" TEXT, FOREIGN KEY(\"purganteNombre\") REFERENCES \"purgantes\"(\"nombre\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\"), PRIMARY KEY(\"id\" AUTOINCREMENT) )");
			statement.executeUpdate("CREATE TABLE \"res_tiene_vacunas\" ( \"id\" INTEGER UNIQUE, \"resID\" TEXT, \"vacunaNombre\" TEXT, \"fecha\" TEXT, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"vacunaNombre\") REFERENCES \"vacunas\"(\"id\"), FOREIGN KEY(\"resID\") REFERENCES \"res\"(\"numero\") )");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
