package com.haole.model;

import java.sql.Date;

public class Animal {
    private int id;
    private String species;
    private String name;
    private String gender;
    private String spayed;
    private String breed;
    private String color;
    private Date birthday;
    private String vaccine_status;
    private String identification;
    private float adoption_fee;

    public Animal() {}

    public Animal(int id, String species, String name, String gender, String spayed, String breed, String color, Date birthday, String vaccine_status, String identification, float adoption_fee) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.gender = gender;
        this.spayed = spayed;
        this.breed = breed;
        this.color = color;
        this.birthday = birthday;
        this.vaccine_status = vaccine_status;
        this.identification = identification;
        this.adoption_fee = adoption_fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpayed() {
        return spayed;
    }

    public void setSpayed(String spayed) {
        this.spayed = spayed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getVaccine_status() {
        return vaccine_status;
    }

    public void setVaccine_status(String vaccine_status) {
        this.vaccine_status = vaccine_status;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public float getAdoption_fee() {
        return adoption_fee;
    }

    public void setAdoption_fee(float adoption_fee) {
        this.adoption_fee = adoption_fee;
    }

    @Override
    public String toString() {
        return "\n" +
                String.format("ID: %08d", id) + '\n' +
                "Species: " + species + '\n' +
                "Name: " + name + '\n' +
                "Gender: " + gender + '\n' +
                "Spayed: " + spayed + '\n' +
                "Breed: " + breed + '\n' +
                "Color: " + color + '\n' +
                "Birthday: " + birthday + '\n' +
                "Vaccine Status: " + vaccine_status + '\n' +
                "Identification: " + identification + '\n' +
                "Adoption Fee: $" + adoption_fee;
    }
}
