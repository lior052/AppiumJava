package org.appium.pageobjects.ios;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.appium.utils.actions.ios.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends IOSActions {

    private IOSDriver driver;

    public HomePage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //locators

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViewsBtn;


    //actions

    public void backToHomePage() throws InterruptedException {
        driver.executeScript("mobile: terminateApp", ImmutableMap.of(
                "bundleId", "com.example.apple-samplecode.UICatalog"));
        Thread.sleep(2000); // Small delay before restarting
        driver.executeScript("mobile: launchApp", ImmutableMap.of(
                "bundleId", "com.example.apple-samplecode.UICatalog"));
    }

    public AlertViewPage openAlertViews() {
        alertViewsBtn.click();

        return new AlertViewPage(driver);
    }
}
