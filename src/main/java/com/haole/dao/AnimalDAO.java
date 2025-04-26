package com.haole.dao;

import com.haole.model.Animal;

import java.sql.*;
import java.util.ArrayList;

public class AnimalDAO implements DAOInterface<Animal> {
    DatabaseConnector dbconnector = new DatabaseConnector();

    @Override
    public int insert(Animal animal) {
        String query = "INSERT INTO animals (`species`,\n" +
                "`name`,\n" +
                "`gender`,\n" +
                "`spayed`,\n" +
                "`breed`,\n" +
                "`color`,\n" +
                "`birthday`,\n" +
                "`vaccine_status`,\n" +
                "`identification`,\n" +
                "`adoption_fee`)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, animal.getSpecies());
            statement.setString(2, animal.getName());
            statement.setString(3, animal.getGender());
            statement.setString(4, animal.getSpayed());
            statement.setString(5, animal.getBreed());
            statement.setString(6, animal.getColor());
            statement.setDate(7, animal.getBirthday());
            statement.setString(8, animal.getVaccine_status());
            statement.setString(9, animal.getIdentification());
            statement.setFloat(10, animal.getAdoption_fee());

            int rowAfftected = statement.executeUpdate();
            return rowAfftected;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Animal animal, int choice) {
        String[] fields = {
                "species", "name", "gender", "spayed", "breed",
                "color", "birthday", "vaccine_status", "identification", "adoption_fee"
        };

        if (choice < 1 || choice > fields.length) return 0;

        String update = "UPDATE animals SET " + fields[choice - 1] + " = ? WHERE id = ?";

        try (Connection connection = dbconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);

            switch (choice) {

                case 1 -> statement.setString(1, animal.getSpecies());
                case 2 -> statement.setString(1, animal.getName());
                case 3 -> statement.setString(1, animal.getGender());
                case 4 -> statement.setString(1, animal.getSpayed());
                case 5 -> statement.setString(1, animal.getBreed());
                case 6 -> statement.setString(1, animal.getColor());
                case 7 -> statement.setDate(1, animal.getBirthday());
                case 8 -> statement.setString(1, animal.getVaccine_status());
                case 9 -> statement.setString(1, animal.getIdentification());
                case 10 -> statement.setFloat(1, animal.getAdoption_fee());

            }
            statement.setInt(2, animal.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Animal animal) {
        String remove = "DELETE FROM animals WHERE id = ?";

        try (Connection connection = dbconnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(remove);
        ) {
            statement.setInt(1, animal.getId());
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Animal> selectAll() {
        ArrayList<Animal> animalList = new ArrayList<>();
        String query = "SELECT * FROM animals ORDER BY species";
        try (
                Connection connection = dbconnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Animal newAnimal = mapResultSetToAnimal(resultSet);
                animalList.add(newAnimal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return animalList;
    }

    public ArrayList<Animal> selectThreeOldest() {
        ArrayList<Animal> animalList = new ArrayList<>();

        String query = "SELECT * FROM animals a\n" +
                "WHERE (\n" +
                "    SELECT COUNT(DISTINCT b.birthday)\n" +
                "    FROM animals b\n" +
                "    WHERE b.species = a.species AND b.birthday < a.birthday\n" +
                ") <3\n" +
                "ORDER BY species, birthday;";
        try (
                Connection connection = dbconnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Animal newAnimal = mapResultSetToAnimal(resultSet);
                animalList.add(newAnimal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return animalList;
    }

    @Override
    public Animal selectById(int id) {


        return null;
    }

    public ArrayList<Animal> search(String whichInfo, int choice) {
        ArrayList<Animal> animalList = new ArrayList<>();
        String[] option = {"name", "species"};

        String query = "SELECT * FROM animals WHERE " + option[choice - 1] + " = LOWER(?)";

        try (Connection con = dbconnector.getConnection();
             PreparedStatement statement = con.prepareStatement(query)
        ) {
            switch (choice) {
                case 1 -> statement.setString(1, whichInfo);
                case 2 -> statement.setString(1, whichInfo);
                default -> System.out.println("Invalid choice!");
            }
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Animal newAnimal = mapResultSetToAnimal(result);
                animalList.add(newAnimal);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return animalList;
    }


    public boolean existedById(int id) {
        // COUNT function counting row in the database, check if row is exist in the database
        String query = "SELECT COUNT(*) FROM animals WHERE id = ?";
        try (
                Connection con = dbconnector.getConnection();
                PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next(); //Count always return 1 rows, so move to that row to get the actual value

            int countValue = resultSet.getInt(1);

            return countValue > 0; //return true if COUNT is 1 which id is exist in the database
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Animal mapResultSetToAnimal(ResultSet resultSet) throws SQLException {
        Animal animal = new Animal();
        animal.setId(resultSet.getInt("id"));
        animal.setSpecies(resultSet.getString("species"));
        animal.setName(resultSet.getString("name"));
        animal.setGender(resultSet.getString("gender"));
        animal.setSpayed(resultSet.getString("spayed"));
        animal.setBreed(resultSet.getString("breed"));
        animal.setColor(resultSet.getString("color"));
        animal.setBirthday(resultSet.getDate("birthday"));
        animal.setVaccine_status(resultSet.getString("vaccine_status"));
        animal.setIdentification(resultSet.getString("identification"));
        animal.setAdoption_fee(resultSet.getFloat("adoption_fee"));
        return animal;
    }
}
