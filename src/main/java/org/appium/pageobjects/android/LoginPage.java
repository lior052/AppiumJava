package org.appium.pageobjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.actions.android.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AndroidActions {

    private AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //locators

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement applyBtn;

    //actions

    public void backToHomePage() {
        driver.executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
        ));
    }

    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.toLowerCase().contains("female")) {
            femaleOption.click();
        }
        else maleOption.click();
    }

    public void setCountry(String country) {
        countryBox.click();
        scrollToElementAction(country);
        //This is dynamic locator so we will create it here not in the locators section
        driver.findElement(AppiumBy.xpath(
                "//android.widget.TextView[@text='"+ country +"']")).click();
    }

    public ProductsPage submit() {
        applyBtn.click();

        return new ProductsPage(driver);
    }

    public String getToastErrorMessage() {
        applyBtn.click();
        //toast class in Android is always: android.widget.Toast
        return driver.findElement(AppiumBy.xpath(
                "(//android.widget.Toast)[1]")).getDomAttribute("name");

    }
}
