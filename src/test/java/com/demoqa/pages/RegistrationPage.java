package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ModalComponent;
import com.demoqa.pages.components.TableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbies = $("#hobbiesWrapper");
    private final SelenideElement imageUpload = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement stateField = $("#state");
    private final SelenideElement stateDropdown = $("#stateCity-wrapper");
    private final SelenideElement cityField = $("#city");
    private final SelenideElement cityDropdown = $("#stateCity-wrapper");
    private final SelenideElement submitBtn = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    TableComponent tableComponent = new TableComponent();
    ModalComponent modalComponent = new ModalComponent();
    PageUtils pageUtils = new PageUtils();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }
    public RegistrationPage removeBlockingElements() {
        pageUtils.removeAds();
        pageUtils.removeFooter();
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.scrollToPopup();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbies.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressTab();
        return this;
    }

    public RegistrationPage setImagePath(String value) {
        imageUpload.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    public RegistrationPage stateFieldAlignTop() {
        stateField.scrollIntoView(true);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateField.click();
        stateDropdown.scrollIntoView(true);
        stateDropdown.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityField.click();
        cityDropdown.scrollIntoView(true);
        cityDropdown.$(byText(value)).click();
        return this;
    }

    public void submit() {
        submitBtn.click();
    }

    public RegistrationPage submissionCheckSuccess() {
        modalComponent.isVisible();
        return this;
    }

    public void submissionCheckFail() {
        modalComponent.isHidden();
    }

    public void submissionModalMessage(String value) {
        modalComponent.getTitle(value);
    }

    public RegistrationPage checkResultTablePairs(String key, String value) {
        tableComponent.getCellValueByKey(key, value);
        return this;
    }
}
