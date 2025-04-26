package com.haole.service;

import com.haole.dao.AnimalDAO;
import com.haole.model.Animal;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalList {
    Scanner scanner = new Scanner(System.in);
    private AnimalDAO animalData = new AnimalDAO();

    public Animal insertUserInput() {
        System.out.print("Insert species: ");
        String species = scanner.nextLine();

        System.out.print("Insert name: ");
        String name = scanner.nextLine();

        System.out.print("Insert gender (M/F): ");
        String gender = scanner.nextLine();

        System.out.print("Insert spayed (Yes/No): ");
        String spayed = scanner.nextLine();

        System.out.print("Insert breed: ");
        String breed = scanner.nextLine();

        System.out.print("Insert colour: ");
        String color = scanner.nextLine();

        System.out.print("Insert birthday (format YYYY-MM-DD): ");
        String birthdayStr = scanner.nextLine();

        System.out.print("Insert vaccine status: ");
        String vaccineStatus = scanner.nextLine();

        System.out.print("Insert identification (eg: bar code or microchip): ");
        String identification = scanner.nextLine();

        float adoptionFee = adoptionFeeCal(birthdayStr);

        Animal insertAnimal = new Animal();

        insertAnimal.setSpecies(species);
        insertAnimal.setName(name);
        insertAnimal.setGender(gender);
        insertAnimal.setSpayed(spayed);
        insertAnimal.setBreed(breed);
        insertAnimal.setColor(color);
        insertAnimal.setBirthday(Date.valueOf(birthdayStr));
        insertAnimal.setVaccine_status(vaccineStatus);
        insertAnimal.setIdentification(identification);
        insertAnimal.setAdoption_fee(adoptionFee);

        return insertAnimal;
    }

    private float adoptionFeeCal(String birthdayStr) {
        // calculate price of adoption based on the age of the animal
        LocalDate birthDate;
        LocalDate currentDate = LocalDate.now();
        try {
            birthDate  = LocalDate.parse(birthdayStr.trim());
        } catch (DateTimeException e) {
            throw new DateTimeException("Birthday must be YYYY-MM-DD", e);
        }
        int ageOfAnimal = Period.between(birthDate, currentDate).getYears();
        float price;

        if (ageOfAnimal < 1) {
            price = 300;
        } else if (ageOfAnimal > 10) {
            price = 100;
        } else {
            price = 200;
        }
        return price;
    }


    public void insertMultiple() {
        ArrayList<Animal> animalList = new ArrayList<>();

        System.out.print("Please Indicate number of Animals that you want to Input: ");
        int n = scanner.nextInt();
        scanner.nextLine();


        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Animal #" + (i + 1) + " ---");
            animalList.add(insertUserInput());
        }

        int totalInsertedRow = 0;

        for (Animal animal : animalList) {
            totalInsertedRow += animalData.insert(animal);
        }

        if (totalInsertedRow > 0) {
            System.out.println("Inserted " + totalInsertedRow + " animals successfully.");
        } else {
            System.out.println("Inserted failed!");
        }

    }

    public void displayAllAnimal() {
        ArrayList<Animal> animalList = animalData.selectAll(); //return as a queried list

        if (animalList.isEmpty()) {
            System.out.println("\nThere is no Information in the Database currently!");
        } else {
            System.out.println("\n" +
                    "Animal information: ");
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
            }
        }
    }

    public void deleteById() {
        Animal toDelete = new Animal();
        System.out.print("Choose ID to Delete: ");
        int id = scanner.nextInt();

        if (animalData.existedById(id)) {
            toDelete.setId(id);
            int res = animalData.delete(toDelete);
            if (res > 0) {
                System.out.println("Delete successful!");
            } else {
                System.out.println("Error please try again!");
            }
        } else {
            System.out.println("\nNo animal with ID " + id + " exists in the database!");
        }
    }

    public void searchByNameOrSpecies() {
        System.out.print("1. Name" + "\n2. Species" +
                "\nPlease choose the info that you want to search (1-2): ");
        ArrayList<Animal> animalList;

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.print("Type Name: ");
                String name = scanner.nextLine();
                animalList = animalData.search(name, choice);

            }
            case 2 -> {
                System.out.print("Type species: ");
                String species = scanner.nextLine();
                animalList = animalData.search(species, choice);

            }
            default -> {
                System.out.println("Invalid choice!");
                return;
            }
        }

        if (animalList.isEmpty()) {
            System.out.println("No matching animals found.");
        } else {
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
            }
        }
    }

    public void displayThreeOldest() {
         ArrayList<Animal> animalList = animalData.selectThreeOldest();
         String currentSpecies = null;

         if (animalList.isEmpty()) {
             System.out.println("No data yet, please choose 1 to insert into the database");
         } else {
             for (Animal animal:animalList) {
                 String species = animal.getSpecies();

                 if (!species.equalsIgnoreCase(currentSpecies)) {
                     currentSpecies = species;
                     System.out.println("\n=== " + currentSpecies.toUpperCase() + " ===");
                 }

                 System.out.println("\n" +
                         String.format(
                                 "Name: " + animal.getName() + '\n' +
                                         "Gender: " + animal.getGender() + '\n' +
                                         "Spayed: " + animal.getSpayed() + '\n' +
                                         "Breed: " + animal.getBreed() + '\n' +
                                         "Color: " + animal.getColor() + '\n' +
                                         "Birthday: " + animal.getBirthday() + '\n' +
                                         "Vaccine Status: " + animal.getVaccine_status() + '\n' +
                                         "Identification: " + animal.getIdentification() + '\n' +
                                         "Adoption Fee: $" + animal.getAdoption_fee()));
             }
         }
    }

    public void updateById() {
        System.out.print("Choose ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!animalData.existedById(id)) {
            System.out.println("\nAnimal with ID " + id + " not found!\n");
            return;
        }

        Animal targetAnimal = new Animal();
        targetAnimal.setId(id);

        System.out.print(
                "=== Animal Attribute Menu ===\n" +
                        "1. species\n" +
                        "2. name\n" +
                        "3. gender\n" +
                        "4. spayed\n" +
                        "5. breed\n" +
                        "6. color\n" +
                        "7. birthday\n" +
                        "8. vaccine_status\n" +
                        "9. identification\n" +
                        "10. adoption fee\n" +
                        "Choose a field that you want to update (1-10): "
        );
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please Update new Information for the animal: ");
        String newValue = scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> targetAnimal.setSpecies(newValue);
                case 2 -> targetAnimal.setName(newValue);
                case 3 -> targetAnimal.setGender(newValue);
                case 4 -> targetAnimal.setSpayed(newValue);
                case 5 -> targetAnimal.setBreed(newValue);
                case 6 -> targetAnimal.setColor(newValue);
                case 7 -> targetAnimal.setBirthday(Date.valueOf(newValue));
                case 8 -> targetAnimal.setVaccine_status(newValue);
                case 9 -> targetAnimal.setIdentification(newValue);
                case 10 -> targetAnimal.setAdoption_fee(Float.valueOf(newValue));
                default -> {
                    System.out.println("Invalid choice, Update cancelled.");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid choice" + e.getMessage());
        }

        int affectedField = animalData.update(targetAnimal, choice);

        if (affectedField > 0) {
            System.out.println("Updated Successfully!");
        } else {
            System.out.println("Failed to update!");
        }
    }
}
