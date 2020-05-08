package dao;

import error.ApiSQLException;
import model.Country;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CountryDao {
    public ArrayList<Country> getCountries() {
        DBConnection dbConnection = new DBConnection();
        ArrayList<Country> countries = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM countries " +
                "ORDER BY country_name ASC";

        try (Connection connection = dbConnection.connectDB()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        int countryId = resultSet.getInt("country_id");
                        String countryName = resultSet.getString("country_name");
                        String countryCode = resultSet.getString("country_code");

                        Country country = new Country(countryId, countryName, countryCode);
                        countries.add(country);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return countries;
    }
    
    public Country getCountry(int id) {
        DBConnection dbConnection = new DBConnection();
        Country country = null;

        String sql = "SELECT * " +
                "FROM countries " +
                "WHERE country_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int countryId = resultSet.getInt("country_id");
                        String countryName = resultSet.getString("country_name");
                        String countryCode = resultSet.getString("country_code");

                        country = new Country(countryId, countryName, countryCode);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return country;
    }

    public Country addCountry(Country country) {
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO countries(country_name, country_code) " +
                "VALUES(?,?)";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, country.getCountryName());
                preparedStatement.setString(2, country.getCountryCode());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating country failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        country.setCountryId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating country failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return country;
    }

    public Country updateCountry(Country country) {
        DBConnection dbConnection = new DBConnection();
        String sql = "UPDATE countries " +
                "SET country_name = ?, country_code = ? " +
                "WHERE country_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, country.getCountryName());
                preparedStatement.setString(2, country.getCountryCode());
                preparedStatement.setInt(3, country.getCountryId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Updating country failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        country.setCountryId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Updating country failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return country;
    }

    public void deleteCountry(int id) {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM countries " +
                "WHERE country_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
    }
}
