package org.appium.pageobjects.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.appium.utils.actions.ios.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewPage extends IOSActions {

    private IOSDriver driver;

    public AlertViewPage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //locators
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")
    private WebElement txtEntryMenu;

    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    private WebElement txtFieldPopUp;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'Confirm' AND type == 'XCUIElementTypeStaticText'")
    private WebElement confirmCancelMenu;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'A message'")
    private WebElement confirmMessage;

    @iOSXCUITFindBy(accessibility = "Confirm")
    private WebElement confirmBtn;


    //actions

    public void openTextEntry() {
        txtEntryMenu.click();
    }

    public void fillPopUpText(String text) {
        txtFieldPopUp.sendKeys(text);
    }

    public void approvedPopUp() {
        okBtn.click();
    }

    public void confirm() {
        confirmBtn.click();
    }

    public void openConfirmCancel() {
        confirmCancelMenu.click();
    }

    public String getConfirmMessage() {
        return confirmMessage.getText();
    }
}
