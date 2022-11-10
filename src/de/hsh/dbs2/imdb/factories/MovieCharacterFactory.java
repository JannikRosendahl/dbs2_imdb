package de.hsh.dbs2.imdb.factories;

import de.hsh.dbs2.imdb.activerecords.Movie;
import de.hsh.dbs2.imdb.activerecords.MovieCharacter;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dbs2.imdb.util.DBConnection.log_stderr;

public class MovieCharacterFactory {
    public static List<MovieCharacter> getMovieCharactersByMovieId(int id) {
        List<MovieCharacter> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + MovieCharacter.table + " JOIN " + Movie.table + " M on " + MovieCharacter.table + ". " + MovieCharacter.col_movID + " = M." + Movie.col_movieID + " AND M." + Movie.col_movieID + " = ? ORDER BY " + MovieCharacter.col_pos;
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int movCharID = resultSet.getInt(MovieCharacter.col_movcharID);
                String character = resultSet.getString(MovieCharacter.col_char);
                String alias = resultSet.getString(MovieCharacter.col_alias);
                int position = resultSet.getInt(MovieCharacter.col_pos);
                int movieID = resultSet.getInt(MovieCharacter.col_movID);
                int personID = resultSet.getInt(MovieCharacter.col_persID);

                result.add(new MovieCharacter(movCharID, character, alias, position, movieID, personID));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("MovieCharacterFactory::getMovieCharactersByMovieId", e);
        }


        return result;
    }
}
