package dao;

import model.Country;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CountryDao {
    public ArrayList<Country> getCountries() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        ArrayList<Country> countries = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM countries " +
                "ORDER BY country_name ASC";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int countryId = resultSet.getInt("country_id");
                String countryName = resultSet.getString("country_name");
                String countryCode = resultSet.getString("country_code");

                Country country = new Country(countryId, countryName, countryCode);
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return countries;
    }
    
    public Country getCountry(int id) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        Country country = null;

        String sql = "SELECT * " +
                "FROM countries " +
                "WHERE country_id = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int countryId = resultSet.getInt("country_id");
                String countryName = resultSet.getString("country_name");
                String countryCode = resultSet.getString("country_code");

                country = new Country(countryId, countryName, countryCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return country;
    }

    public Country addCountry(Country country) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO countries(country_name, country_code) " +
                "VALUES(?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return country;
    }

    public Country updateCountry(Country country) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "UPDATE countries SET country_name = ?, country_code = ? WHERE country_id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
                    throw new SQLException("Creating country failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return country;
    }

    public void deleteCountry(int countryId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "DELETE FROM countries WHERE country_id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, countryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
