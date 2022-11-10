package de.hsh.dbs2.imdb.factories;

import de.hsh.dbs2.imdb.activerecords.Person;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static de.hsh.dbs2.imdb.util.DBConnection.log_stderr;
import static de.hsh.dbs2.imdb.util.DBConnection.log_stdio;

public class PersonFactory {
    public static List<Person> getPersonsByName(String query) {
        log_stdio("someone requested person with name " + query);

        List<Person> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + Person.table + " WHERE UPPER(" + Person.col_name + ") LIKE UPPER(?)";
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setString(1, query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(Person.col_name);
                char sex = resultSet.getString(Person.col_sex).charAt(0);
                int id = resultSet.getInt(Person.col_personID);

                result.add(new Person(name, sex, id));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("PersonFactory caught SQLException, query: " + query, e);
        }

        log_stdio("found " + result.size());

        return result;
    }

    public static Person getPersonByID(int personID) {
        Person person = null;

        try {
            String sql = "SELECT * FROM " + Person.table + " WHERE " + Person.col_personID + " = ?";
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
            stmt.setInt(1, personID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(Person.col_name);
                char sex = resultSet.getString(Person.col_sex).charAt(0);
                int id = resultSet.getInt(Person.col_personID);
                person = new Person(name, sex, id);
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            log_stderr("PersonFactory::getPersonByID", e);
        }

        return person;
    }
}
