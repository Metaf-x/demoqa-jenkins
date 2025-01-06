package com.demoqa.pages.components;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {

    public void isVisible() {
        $(".modal-dialog").shouldBe(visible);
    }

    public void isHidden() {
        $(".modal-dialog").shouldNotBe(visible);
    }

    public void getTitle(String value) {
        $(".modal-header .modal-title").shouldHave(text(value));
    }
}
