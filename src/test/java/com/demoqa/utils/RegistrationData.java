package com.demoqa.utils;

import com.github.javafaker.Faker;

public class RegistrationData {

    private static final String[] GENDERS = {"Male", "Female", "Other"};
    private static final String[] SUBJECTS = {"Computer Science", "Social Studies", "English", "Chemistry"};
    private static final String[] HOBBIES = {"Sports", "Reading", "Music"};
    private static final String[] IMAGES = {"test-img.jpg", "2.jpg", "3.jpg"};

    private final String phoneFormat = "79########";

    private final int minimumStudentAge = 18;
    private final int maximumStudentAge = 65;

    Faker faker = new Faker();
    RandomBirthday randomBirthday = new RandomBirthday(minimumStudentAge, maximumStudentAge);
    RandomStateAndCity randomStateAndCity = new RandomStateAndCity();

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            address = faker.address().fullAddress(),
            gender = faker.options().option(GENDERS),
            subjects = faker.options().option(SUBJECTS),
            hobbies = faker.options().option(HOBBIES),
            birthDay = randomBirthday.getFormattedDay(),
            birthMonth = randomBirthday.getFormattedMonth(),
            birthYear = randomBirthday.getFormattedYear(),
            phone = faker.numerify(phoneFormat),
            image = faker.options().option(IMAGES),
            state = randomStateAndCity.getRandomState(),
            city = randomStateAndCity.getRandomCity(state);

    public String shortPhoneInvalid = faker.numerify("79#######");
}
