package tasks.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		//Class.forName("org.mariadb.jdbc.Driver")
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "");

	}
}