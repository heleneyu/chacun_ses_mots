package fr.formation.projet.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFactory {
	private static Connection connection;

	public static Connection createConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chacun_ses_mots?serverTimezone=UTC", "root", "");
			}

			catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return connection;

	}

	public static Statement createStatement() {
		try {
			return createConnection().createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static PreparedStatement createStatement(String query) {
		try {
			return createConnection().prepareStatement(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet createResultSet(String query) {
		try {
			return createStatement().executeQuery(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement prepareStatement(String query, Object... values) {
		try {
			PreparedStatement myStatement = createStatement(query);
			for (int i = 1; i <= values.length; i++) {
				myStatement.setObject(i, values[i-1]);
			}
			return myStatement;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static ResultSet createResultSet(String query, Object... values) {
		try {
			return prepareStatement(query, values).executeQuery();
		}

		catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static boolean execute(String query, Object... values) {
		try {

			return prepareStatement(query, values).execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static <T> T execute2(String query, Class<T> cls, Object... values) {
		try {
			if (cls == ResultSet.class) {
				return (T) prepareStatement(query, values).executeQuery();
			}
			return (T)new Boolean(prepareStatement(query, values).execute());
		}
		catch (SQLException e) {

			e.printStackTrace();

			return (T)new Boolean(false);
		}
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();

			}

		}

	}
}
