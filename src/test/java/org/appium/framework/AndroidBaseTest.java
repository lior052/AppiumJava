package org.appium.framework;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.appium.pageobjects.android.LoginPage;
import org.appium.utils.actions.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setAppiumDriver() throws IOException {
        Properties properties = getPropertiesData();
        AppiumDriverLocalService service = startAppiumServer(
                properties.getProperty("ipAddress"), Integer.parseInt(properties.getProperty("port")));

        //appium capabilities (device settings)
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("androidEmulatorName"));
        //web apps testing
        options.setChromedriverExecutable("//Users//lior//Downloads//Lior_Automation//chromedriver-mac-x64//chromedriver");
        //options.setCapability("browserName", "Chrome");

        //android apps
        options.setApp("//Users//lior//IdeaProjects//AppiumFramework//src//test//resources//General-Store.apk");
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60));

        //Android driver
        driver = new AndroidDriver(service.getUrl(), options);
        //global wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //This is the first page of the app
        loginPage = new LoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        stopAppiumServer();
    }

    public void startActivity(String packageName, String activityName) {
        driver.executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", packageName + "/" + activityName
        ));
    }

}
