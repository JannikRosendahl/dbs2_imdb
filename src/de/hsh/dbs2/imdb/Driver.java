package de.hsh.dbs2.imdb;

import de.hsh.dbs2.imdb.factories.GenreFactory;
import de.hsh.dbs2.imdb.util.DBConnection;

public class Driver {
    public static void main(String[] args) {
        System.out.println(GenreFactory.getGenresByName("Action"));
        System.out.println(GenreFactory.getGenresByName("Comedy"));
        System.out.println(GenreFactory.getGenresByName("Drama"));
        System.out.println(GenreFactory.getGenresByName("Horror"));

    }
}
