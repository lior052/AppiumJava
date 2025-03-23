package org.appium.tests;

import org.appium.framework.AndroidBaseTest;
import org.appium.framework.ReportListeners;
import org.appium.pageobjects.android.CartPage;
import org.appium.pageobjects.android.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
//temp
@Listeners(ReportListeners.class)
public class GeneralStoreTests extends AndroidBaseTest {

//    String webViewContext = "WEBVIEW_com.androidsample.generalstore";
//    String nativeContext = "NATIVE_APP";

    @DataProvider
    public Object[][] getTestData() {
        return new Object[][]  {{"Brazil", "Jo", "female"}, {"Israel", "Lior", "male"}};
    }

    @DataProvider
    public Object[][] getJsonTestData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/org/appium/testdata/generalStoreData.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }


    @AfterMethod(alwaysRun = true)
    public void setup() {
        loginPage.backToHomePage();
    }

    @Test(dataProvider = "getTestData")
    public void loginSuccessfulTest(String country, String name, String gender) {

        loginPage.setCountry(country);
        loginPage.setNameField(name);
        loginPage.setGender(gender);

        ProductsPage productsPage = loginPage.submit();
        Assert.assertTrue(productsPage.isProductsPageDisplay());
    }

    @Test(dataProvider = "getJsonTestData")
    public void loginSuccessfulJsonDataTest(HashMap<String, String> data) {

        loginPage.setCountry(data.get("country"));
        loginPage.setNameField(data.get("name"));
        loginPage.setGender(data.get("gender"));

        ProductsPage productsPage = loginPage.submit();
        Assert.assertTrue(productsPage.isProductsPageDisplay());
    }

    @Test
    public void loginFailTest() {

        Assert.assertEquals(loginPage.getToastErrorMessage(), "Please enter your name");
    }

    @Test
    public void productsListTest() {

        String productName = "Jordan 6 Rings";
        loginPage.setNameField("Lior");
        ProductsPage productsPage = loginPage.submit();
        productsPage.verifyProductItemExist(productName);

        //now need to click on a button that appear at every row so nothing is unique
        productsPage.addProductToCart(productName);
        CartPage cartPage = productsPage.goToCartPage();

        Assert.assertTrue(cartPage.isTotalAmountExist());
        Assert.assertEquals(cartPage.productNameInCart(), productName);
    }

    @Test
    public void twoProductsAmountInCartTest() {

        loginPage.setNameField("Lior");
        ProductsPage productsPage = loginPage.submit();
        productsPage.addProductToCartByIndex(0);
        productsPage.addProductToCartByIndex(0);
        CartPage cartPage = productsPage.goToCartPage();

        Assert.assertEquals(cartPage.getTotalCartAmount(), cartPage.getProductsSum());
    }

    @Test
    public void termsOfConditionTest() {
        loginPage.setNameField("Lior");
        ProductsPage productsPage = loginPage.submit();

        productsPage.addProductToCartByIndex(0);
        CartPage cartPage = productsPage.goToCartPage();
        cartPage.acceptTerms();

        Assert.assertTrue(cartPage.isProceedExist());
    }



//    @Test
//    public void switchContextTest() throws InterruptedException {
//        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Lior");
//        driver.hideKeyboard();
//        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//        driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='ADD TO CART'])")).click();
//        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.attributeContains(
//                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
//
//        //Hybrid - from this part this is web part so need to switch context
//        Thread.sleep(10000);
//        Set<String> contexts = driver.getContextHandles();
//
//        for (String context : contexts) {
//            System.out.println(context);
//        }
//
//        //switch context
//        driver.context(webViewContext);
//
//        //google site
//        driver.findElement(By.name("q")).sendKeys("Appium test");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//
//        //back to previous page
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//
//        //switch context
//        driver.context(nativeContext);
//    }
//
//    @Test
//    public void mobileWebTest() {
//
//        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
//        driver.findElement(By.xpath("//a[@href='/angularAppdemo/products']")).click();
//        //scroll
//        driver.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//        String text = driver.findElement(By.xpath("//a[@href='/angularAppdemo/products/3']")).getText();
//
//        Assert.assertEquals(text, "Devops");
//
//    }
}
