package com.haole;

import com.haole.service.AnimalList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }

    public void start() {
        AnimalList animalList = new AnimalList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            if (scanner.hasNext()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                handlingCases(choice, animalList);
            } else {
                System.out.println("Invalid input! Please enter a number from 1 to 6.");
                scanner.nextLine();
                choice = -1;
            }
        } while(choice != 7);
        System.out.println("Thank you for using our system. Goodbye!");
        scanner.close();
    }
    public void displayMenu() {
        System.out.println("\n----Welcome to animal shelter inventory system!----");
        System.out.println("1. Create animal information and insert to the database");
        System.out.println("2. Display all information of the animals");
        System.out.println("3. Delete by matching ID");
        System.out.println("4. Update new information for animals");
        System.out.println("5. Search by names");
        System.out.println("6. Display 3 oldest animals in each species");
        System.out.println("7. To exit");
        System.out.print("Please choose one options (1-7): ");
    }

    public void handlingCases(int choice, AnimalList animalList) {
        switch (choice) {
            case 1 -> animalList.insertMultiple();
            case 2 -> animalList.displayAllAnimal();
            case 3 -> animalList.deleteById();
            case 4 -> animalList.updateById();
            case 5 -> animalList.searchByNameOrSpecies();
            case 6 -> animalList.displayThreeOldest();
            default -> System.out.println("Invalid choice");
        }
    }
}