package org.appium.pageobjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.actions.android.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends AndroidActions {

    private AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    //locators

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> productsInPage;

//    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
//    private List<WebElement> productAddCartBtn;
//
    //actions

    public boolean isProductsPageDisplay() {
        return cartBtn.isDisplayed();
    }
    public void addProductToCartByIndex(int index) {
        addToCartBtn.get(index).click();
    }

    public CartPage goToCartPage() {
        cartBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.attributeContains(
//                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        return new CartPage(driver);
    }

    public void verifyProductItemExist(String productName) {
        scrollToElementAction(productName);
    }

    public void addProductToCart(String productName) {
        int countItems = productsInPage.size();

        for (int i = 0; i < countItems; i++) {
            String text = productsInPage.get(i).getText();
            if (text.equalsIgnoreCase(productName)) {
                addToCartBtn.get(i).click();
                break;
            }
        }
    }
}
