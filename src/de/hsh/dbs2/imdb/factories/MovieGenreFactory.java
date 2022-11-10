package de.hsh.dbs2.imdb.factories;

import de.hsh.dbs2.imdb.activerecords.Movie;
import de.hsh.dbs2.imdb.activerecords.MovieGenre;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dbs2.imdb.util.DBConnection.log_stderr;

public class MovieGenreFactory {
    public static List<MovieGenre> getMovieGenresByMovieId(int id) {
        List<MovieGenre> result = new ArrayList<>();

        try {
            String sql = "SELECT H.* FROM " + Movie.table + " JOIN " + MovieGenre.table + " H on " + Movie.table + ".MOVIEID = H." + Movie.col_movieID + " WHERE H." + Movie.col_movieID + " = ?";
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int genreId = resultSet.getInt(MovieGenre.col_genreID);
                int movieId = resultSet.getInt(MovieGenre.col_movieID);
                result.add(new MovieGenre(genreId, movieId));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("MovieGenreFactory::getMovieGenresByMovieId", e);
        }

        return result;
    }
}
