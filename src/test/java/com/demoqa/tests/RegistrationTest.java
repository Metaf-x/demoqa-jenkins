package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.RegistrationData;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationData data = new RegistrationData();

    @Test
    void fillAllFieldsWithValidDataTest() {

        registrationPage.openPage()
                .removeBlockingElements()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .setGender(data.gender)
                .setNumber(data.phone)
                .setDateOfBirth(data.birthDay, data.birthMonth, data.birthYear)
                .setSubjects(data.subjects)
                .setHobbies(data.hobbies)
                .setImagePath(data.image)
                .setAddress(data.address)
                .setState(data.state)
                .setCity(data.city)
                .submit();

        registrationPage
                .submissionCheckSuccess()
                .submissionModalMessage("Thanks for submitting the form");

        registrationPage
                .checkResultTablePairs("Student Name", data.firstName + " " + data.lastName)
                .checkResultTablePairs("Student Email", data.email)
                .checkResultTablePairs("Gender", data.gender)
                .checkResultTablePairs("Mobile", data.phone)
                .checkResultTablePairs("Date of Birth", data.birthDay + " " + data.birthMonth + "," + data.birthYear)
                .checkResultTablePairs("Subjects", data.subjects)
                .checkResultTablePairs("Hobbies", data.hobbies)
                .checkResultTablePairs("Picture", data.image)
                .checkResultTablePairs("Address", data.address)
                .checkResultTablePairs("State and City", data.state + " " + data.city);
    }

    @Test
    void fillRequiredFieldsWithValidDataTest() {
        registrationPage
                .openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setNumber(data.phone)
                .submit();

        registrationPage
                .submissionCheckSuccess()
                .submissionModalMessage("Thanks for submitting the form");

        registrationPage
                .checkResultTablePairs("Student Name", data.firstName + " " + data.lastName)
                .checkResultTablePairs("Gender", data.gender)
                .checkResultTablePairs("Mobile", data.phone);
    }

    @Test
    void fillRequiredFieldsWithShortPhoneNumberNegativeTest() {
        registrationPage
                .openPage()
                .setFirstName(data.firstName)
                .setLastName(data.firstName)
                .setGender(data.gender)
                .setNumber(data.shortPhoneInvalid)
                .submit();

        registrationPage
                .submissionCheckFail();

    }
}