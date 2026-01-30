package database;

import model.Veterinarian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VetDAO {

    public void insertVeterinarian(Veterinarian veterinarian) {
        String sql = "INSERT INTO veterinarians (id, name, specialty, experience) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, veterinarian.getId());
            statement.setString(2, veterinarian.getName());
            statement.setString(3, veterinarian.getSpecialty());
            statement.setInt(4, veterinarian.getExperience());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting veterinarian: " + e.getMessage());
        }
    }

    public List<Veterinarian> getAllVeterinarians() {
        List<Veterinarian> veterinarians = new ArrayList<>();
        String sql = "SELECT * FROM veterinarians";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Veterinarian vet = new Veterinarian(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty"),
                        resultSet.getInt("experience")
                );
                veterinarians.add(vet);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving veterinarians: " + e.getMessage());
        }
        return veterinarians;
    }

    public Veterinarian getVeterinarianById(int id) {
        String sql = "SELECT * FROM veterinarians WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Veterinarian(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty"),
                        resultSet.getInt("experience")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving veterinarian by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        String sql = "UPDATE veterinarians SET name = ?, specialty = ?, experience = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, veterinarian.getName());
            statement.setString(2, veterinarian.getSpecialty());
            statement.setInt(3, veterinarian.getExperience());
            statement.setInt(4, veterinarian.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating veterinarian: " + e.getMessage());
        }
    }

    public void deleteVeterinarian(int id) {
        String sql = "DELETE FROM veterinarians WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error deleting veterinarian: " + e.getMessage());
        }
    }

    public List<Veterinarian> searchByName(String name) {
        List<Veterinarian> veterinarians = new ArrayList<>();
        String sql = "SELECT * FROM veterinarians WHERE name ILIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Veterinarian vet = new Veterinarian(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty"),
                        resultSet.getInt("experience")
                );
                veterinarians.add(vet);
            }

        } catch (SQLException e) {
            System.out.println("Error searching veterinarians by name: " + e.getMessage());
        }
        return veterinarians;
    }

    public List<Veterinarian> searchByExperience(int minExperience) {
        List<Veterinarian> veterinarians = new ArrayList<>();
        String sql = "SELECT * FROM veterinarians WHERE experience >= ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, minExperience);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Veterinarian vet = new Veterinarian(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty"),
                        resultSet.getInt("experience")
                );
                veterinarians.add(vet);
            }

        } catch (SQLException e) {
            System.out.println("Error searching veterinarians by experience: " + e.getMessage());
        }
        return veterinarians;
    }

    public static class InvalidInputException extends Exception {
      public InvalidInputException(String message) {
        super(message);
      }
    }
}
