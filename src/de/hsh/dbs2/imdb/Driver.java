package de.hsh.dbs2.imdb;

import de.hsh.dbs2.imdb.factories.GenreFactory;
import de.hsh.dbs2.imdb.util.DBConnection;
import de.hsh.dbs2.imdb.util.IMDBException;

import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws IMDBException, SQLException {
        System.out.println(GenreFactory.getGenresByName("Action"));
        System.out.println(GenreFactory.getGenresByName("Comedy"));
        System.out.println(GenreFactory.getGenresByName("Drama"));
        System.out.println(GenreFactory.getGenresByName("Horror"));

    }
}
