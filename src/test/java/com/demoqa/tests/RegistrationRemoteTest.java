package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.RegistrationData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("Проверка формы регистрации")
public class RegistrationRemoteTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationData data = new RegistrationData();

    @Test
    @Tag("REGRESS")
    @DisplayName("Заполнение валидными данными всех полей")
    void successfulAllFieldsWithValidDataTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть страницу с формой регистрации", () -> {
            registrationPage.openPage()
                    .removeBlockingElements();
        });

        step("Заполнить все поля формы", () -> {
            registrationPage.setFirstName(data.firstName)
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
                    .setCity(data.city);
        });

        step("Отправить форму", () -> {
            registrationPage.submit();
        });

        step("Проверить, что форма была отправлена", () -> {
            registrationPage
                    .submissionCheckSuccess()
                    .submissionModalMessage("Thanks for submitting the form");
        });

        step("Проверить результат отправки формы", () -> {
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
        });
    }

    @Test
    @Tag("REGRESS")
    @DisplayName("Заполнение валидными данными обязательных полей")
    void successfulRequiredFieldsWithValidDataTest() {
        step("Открыть страницу с формой регистрации", () -> {
            registrationPage.openPage()
                    .removeBlockingElements();
        });

        step("Заполнить данными обязательные поля формы регистрации", () -> {
            registrationPage
                    .setFirstName(data.firstName)
                    .setLastName(data.lastName)
                    .setGender(data.gender)
                    .setNumber(data.phone);
        });

        step("Отправить форму", () -> {
            registrationPage.submit();
        });

        step("Проверить, что форма была отправлена", () -> {
            registrationPage
                    .submissionCheckSuccess()
                    .submissionModalMessage("Thanks for submitting the form");
        });

        step("Проверить результат отправки формы", () -> {
            registrationPage
                    .checkResultTablePairs("Student Name", data.firstName + " " + data.lastName)
                    .checkResultTablePairs("Gender", data.gender)
                    .checkResultTablePairs("Mobile", data.phone);
        });
    }

    @Test
    @Tag("REGRESS")
    @DisplayName("Заполнение обязательных полей формы с невалидным номером телефона (9 символов)")
    void negativeFillRequiredFieldsWithShortPhoneNumberTest() {
        step("Открыть страницу с формой регистрации", () -> {
            registrationPage.openPage()
                    .removeBlockingElements();
        });
        step("Заполнить данными обязательные поля формы регистрации с невалидным номером телефона (9 символов)", () -> {
            registrationPage.setFirstName(data.firstName)
                    .setLastName(data.firstName)
                    .setGender(data.gender)
                    .setNumber(data.shortPhoneInvalid);
        });

        step("Отправить форму", () -> {
            registrationPage.submit();
        });
        step("Проверить, что сообщение с уведомлением об успешной отправке не появилось", () -> {
            registrationPage
                    .submissionCheckFail();
        });

    }
}