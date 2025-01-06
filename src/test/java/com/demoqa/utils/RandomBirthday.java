package com.demoqa.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RandomBirthday {

    private final int minAge;
    private final int maxAge;
    private final Faker faker;
    private final LocalDate birthDate;
 
    public RandomBirthday(int minAge, int maxAge) {
        if (minAge > maxAge) {
            throw new IllegalArgumentException("Start age must be before end age");
        }
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.faker = new Faker();
        this.birthDate = this.getDate();
    }

    private LocalDate getDate() {
        return this.faker.date().birthday(this.minAge, this.maxAge).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    public String getFormattedYear() {
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH));
    }

    public String getFormattedMonth() {
        return birthDate.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
    }

    public String getFormattedDay() {
        return birthDate.format(DateTimeFormatter.ofPattern("dd", Locale.ENGLISH));
    }
}
