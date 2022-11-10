package de.hsh.dbs2.imdb.factories;

import de.hsh.dbs2.imdb.activerecords.Genre;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dbs2.imdb.util.DBConnection.log_stderr;

public class GenreFactory {
    public static List<Genre> getAllGenres() {
        List<Genre> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + Genre.table + " ORDER BY " + Genre.col_genre;
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(Genre.col_genre);
                int id = resultSet.getInt(Genre.col_genreID);

                result.add(new Genre(name, id));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("GenreFactory::getAllGenres", e);
        }

        return result;
    }

    public static List<Genre> getGenresByName(String query) {
        List<Genre> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + Genre.table + " WHERE UPPER(" + Genre.col_genre + ") LIKE UPPER(?)";
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setString(1, "%" +query + "%");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(Genre.col_genre);
                int id = resultSet.getInt(Genre.col_genreID);

                result.add(new Genre(name, id));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("GenreFactory::getGenresByName caught SQLException, query: " + query, e);
        }

        return result;
    }

    public static List<Genre> getGenresByMovieID(int movieId) {
        List<Genre> result = new ArrayList<>();

        try {
            String sql = "SELECT GENRE.* FROM GENRE JOIN HASGENRE H on GENRE.GENREID = H.GENREID JOIN MOVIE M on H.MOVIEID = M.MOVIEID WHERE M.MOVIEID = ?";
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setInt(1, movieId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String genre = resultSet.getString(Genre.col_genre);
                int id = resultSet.getInt(Genre.col_genreID);

                result.add(new Genre(genre, id));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("GenreFactory::getGenresByMovieID", e);
        }

        return result;
    }
}
