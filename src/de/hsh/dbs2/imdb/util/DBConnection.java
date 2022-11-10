package de.hsh.dbs2.imdb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final boolean debug = true;
	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {
		try {
			if (conn == null || conn.isClosed()) {
				conn = null;
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:db01", "6s1-n1s-u1", "__WhoLetMeIn__");
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to connect to database: " + e.getMessage());
		}
		return conn;
	}

	public static void log_stderr(String msg, Exception e) {
		if (!debug)
			return;

		System.err.println(msg);
		e.printStackTrace();
	}

	public static void log_stdio(String msg) {
		if (!debug)
			return;

		System.out.println(msg);
	}
}
