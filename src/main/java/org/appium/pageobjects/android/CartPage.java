package org.appium.pageobjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.actions.android.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {

    private AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    //locators

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productsPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement cartPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsBtn;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Total Purchase Amount:  \"]")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productName;

    //actions

    public String getTotalCartAmount() {

        return cartPrice.getText();
    }

    public String getProductsSum() {

        double amount = 0.0;

        for (WebElement productItem : productsPrice) {
            String strPrice = productItem.getText();
            amount += Double.parseDouble(strPrice.substring(1));
        }
        String price = convertDoubleToString(amount);
        return "$ " + price;
    }

    public void acceptTerms() {
        longPressAction(termsBtn);
        acceptBtn.click();
    }

    public boolean isProceedExist() {
        return proceedBtn.isDisplayed();
    }

    public boolean isTotalAmountExist() {
        return totalAmount.isDisplayed();
    }

    public String productNameInCart() {
        return productName.getText();
    }
}
