package service;

import entity.Contact;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private static final String SELECT_ALL_CONTACTS = "SELECT * FROM contact";
    private static final String SAVE_CONTACT = "INSERT INTO contact(name, age) VALUES (?,?)";
    private static final String UPDATE_CONTACT = "UPDATE contact SET name = ?, age = ? WHERE id = ?";
    private static final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?";

    public static List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CONTACTS);
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setName(resultSet.getString("name"));
                contact.setAge(resultSet.getInt("age"));
                contacts.add(contact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    public void save(Contact contact) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setInt(2, contact.getAge());
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Contact contact) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setInt(2, contact.getAge());
            preparedStatement.setLong(3, contact.getId());
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
