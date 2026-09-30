
package com.qapractice.framework.pages;

import com.qapractice.framework.data.ShippingDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** "Shipping Details" form + order confirmation message. */
public class CheckoutPage extends BasePage {

    private static final By PHONE = By.id("phone");
    private static final By STREET = By.name("street");
    private static final By CITY = By.name("city");
    private static final By COUNTRY = By.id("countries_dropdown_menu");
    private static final By SUBMIT_ORDER = By.id("submitOrderBtn");
    private static final By CONFIRMATION = By.id("message");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage waitUntilDisplayed() {
        waitForVisible(PHONE);
        return this;
    }

    /** Fills every field for which a value is supplied; empty/null values leave the field blank. */
    public CheckoutPage fillShippingDetails(ShippingDetails details) {
        type(PHONE, details.phone(), "Phone number");
        type(STREET, details.street(), "Street");
        type(CITY, details.city(), "City");

        if (details.country() != null && !details.country().isEmpty()) {
            selectByVisibleText(COUNTRY, details.country(), "Country");
        }

        return this;
    }

    public CheckoutPage submitOrder() {
        click(SUBMIT_ORDER, "Submit Order");
        return this;
    }

    public boolean isOrderConfirmed() {
        try {
            WebElement message = waitForVisible(CONFIRMATION);
            String text = message.getText().trim().toLowerCase();

            return text.contains("congrats!") && text.contains("order");
        } catch (Exception e) {
            return false;
        }
    }

    public String confirmationMessage() {
        return getText(CONFIRMATION);
    }
}

