package de.hsh.dbs2.imdb;

import javax.swing.SwingUtilities;

import de.hsh.dbs2.imdb.gui.SearchMovieDialog;
import de.hsh.dbs2.imdb.gui.SearchMovieDialogCallback;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Starter {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws SQLException {
		try {
			DBConnection.getConnection();

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Starter().run();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getConnection().close();
		}

	}
	
	public void run() {
		SearchMovieDialogCallback callback = new SearchMovieDialogCallback();
		SearchMovieDialog sd = new SearchMovieDialog(callback);
		sd.setVisible(true);
	}

}
