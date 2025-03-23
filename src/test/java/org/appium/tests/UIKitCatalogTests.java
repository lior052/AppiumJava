package org.appium.tests;

import org.appium.framework.IOSBaseTest;
import org.appium.framework.ReportListeners;
import org.appium.pageobjects.ios.AlertViewPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Listeners(ReportListeners.class)
public class UIKitCatalogTests extends IOSBaseTest {


    @DataProvider
    public Object[][] getJsonTestData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/org/appium/testdata/uikitCatalogData.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

    @AfterMethod(alwaysRun = true)
    public void setup() throws InterruptedException {
        homePage.backToHomePage();
    }

    @Test(dataProvider = "getJsonTestData", groups = {"Smoke"})
    public void appiumBasicTest(HashMap<String, String> data) {

        AlertViewPage alertViewPage = homePage.openAlertViews();
        alertViewPage.openTextEntry();
        alertViewPage.fillPopUpText(data.get("PopUpText"));
        alertViewPage.approvedPopUp();
        alertViewPage.openConfirmCancel();
        String text = alertViewPage.getConfirmMessage();

        Assert.assertEquals(text, "A message should be a short, complete sentence.");

        alertViewPage.confirm();
    }
}
