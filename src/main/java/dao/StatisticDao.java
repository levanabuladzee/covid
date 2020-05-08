package dao;

import error.ApiSQLException;
import model.Statistic;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class StatisticDao {
    public ArrayList<Statistic> getStatistics(int id) {
        DBConnection dbConnection = new DBConnection();
        ArrayList<Statistic> statistics = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM statistics " +
                "WHERE country_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int statisticId = resultSet.getInt("statistic_id");
                        int countryId = resultSet.getInt("country_id");
                        String statisticDate = String.valueOf(resultSet.getDate("statistic_date"));
                        long statisticConfirmed = resultSet.getLong("statistic_confirmed");
                        long statisticDeaths = resultSet.getLong("statistic_deaths");
                        long statisticRecovered = resultSet.getLong("statistic_recovered");

                        Statistic statistic = new Statistic(countryId, statisticDate, statisticConfirmed, statisticDeaths, statisticRecovered);
                        statistic.setStatisticId(statisticId);
                        statistics.add(statistic);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }

    public ArrayList<Statistic> getStatistics(int id, int month) {
        DBConnection dbConnection = new DBConnection();
        ArrayList<Statistic> statistics = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM statistics " +
                "WHERE country_id = ? " +
                "AND " +
                "EXTRACT(MONTH FROM statistic_date) = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, month);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int statisticId = resultSet.getInt("statistic_id");
                        int countryId = resultSet.getInt("country_id");
                        String statisticDate = String.valueOf(resultSet.getDate("statistic_date"));
                        long statisticConfirmed = resultSet.getLong("statistic_confirmed");
                        long statisticDeaths = resultSet.getLong("statistic_deaths");
                        long statisticRecovered = resultSet.getLong("statistic_recovered");

                        Statistic statistic = new Statistic(countryId, statisticDate, statisticConfirmed, statisticDeaths, statisticRecovered);
                        statistic.setStatisticId(statisticId);
                        statistics.add(statistic);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return statistics;
    }

    public Statistic addStatistic(Statistic statistic) {
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO statistics(country_id, statistic_date, statistic_confirmed, statistic_deaths, statistic_recovered) " +
                "VALUES(?,?,?,?,?)";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, statistic.getCountryId());
                preparedStatement.setDate(2, Date.valueOf(statistic.getDate()));
                preparedStatement.setLong(3, statistic.getConfirmed());
                preparedStatement.setLong(4, statistic.getDeaths());
                preparedStatement.setLong(5, statistic.getRecovered());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating statistic failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        statistic.setStatisticId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating statistic failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return statistic;
    }

    public Statistic updateStatistic(Statistic statistic) {
        DBConnection dbConnection = new DBConnection();
        String sql = "UPDATE statistics " +
                "SET country_id = ?, statistic_date = ?, statistic_confirmed = ?, statistic_deaths = ?, statistic_recovered = ? " +
                "WHERE statistic_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, statistic.getCountryId());
                preparedStatement.setDate(2, Date.valueOf(statistic.getDate()));
                preparedStatement.setLong(3, statistic.getConfirmed());
                preparedStatement.setLong(4, statistic.getDeaths());
                preparedStatement.setLong(5, statistic.getRecovered());
                preparedStatement.setInt(6, statistic.getStatisticId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Updating statistic failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        statistic.setStatisticId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Updating statistic failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
        return statistic;
    }

    public void deleteStatistic(int id) {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM statistics " +
                "WHERE statistic_id = ?";

        try (Connection connection = dbConnection.connectDB()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiSQLException(e.getSQLState(), e.getMessage());
        }
    }
}
