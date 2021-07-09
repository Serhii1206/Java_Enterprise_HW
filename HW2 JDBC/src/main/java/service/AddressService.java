package service;

import entity.Address;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private static final String SELECT_ADDRESS = "SELECT * FROM address";
    private static final String SAVE_ADDRESS = "INSERT INTO address(city, street) VALUES (?,?)";
    private static final String UPDATE_ADDRESS = "UPDATE address SET sity = ?, street = ?,  WHERE id = ?";
    private static final String DELETE_ADDRESS = "DELETE from address WHERE id = ?";

    public List<Address> getAll() {
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ADDRESS);
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong("id"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                addresses.add(address);
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addresses;
    }

    public void save(Address address) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ADDRESS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Address address) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS)) {
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setInt(3, address.getHouseNumber());
            preparedStatement.setLong(4, address.getId());
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
