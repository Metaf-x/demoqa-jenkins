package com.demoqa.pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PageUtils {
    public void removeAds() {
        executeJavaScript("$('#fixedban').remove()");
    }
    public void removeFooter() {
        executeJavaScript("$('footer').remove()");
    }
}
