package org.appium.framework;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.appium.pageobjects.ios.HomePage;
import org.appium.utils.actions.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest extends AppiumUtils {

    public IOSDriver driver;
    public HomePage homePage;


    @BeforeClass(alwaysRun = true)
    public void setAppiumDriver() throws IOException {
        Properties properties = getPropertiesData();
        //int port = Integer.parseInt(System.getProperty("port")) != 0 ? Integer.parseInt(System.getProperty("port")) : Integer.parseInt(properties.getProperty("port"));
        AppiumDriverLocalService service = startAppiumServer(
                properties.getProperty("ipAddress"), Integer.parseInt(properties.getProperty("port")));

        //appium capabilities (device settings)
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(properties.getProperty("iosSimulatorName"));
        options.setApp("/Users/lior/Library/Developer/Xcode/DerivedData/UIKitCatalog-ffbfzacbhfcgzecvsxrzacugnuyv/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
        //options.setApp("//Users//lior//IdeaProjects//test1//src//test//java//resources//TestApp 3.app");
        options.setPlatformVersion("18.3");
        //Appium -> webDriver Agent -> ios app (appium talk to the agent)
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        //real device
//        options.setDeviceName("iPhone 13");
//        options.setCapability("xcodeOrgId", "62J96EUJ9N"); //Team ID
//        options.setCapability("xcodeSigningId", "iPhone Developer");
//        options.setUdid("00008110-0005753C1ED0401E"); //Device ID
//        options.setUpdatedWdaBundleId("com.example.apple-samplecode.UICatalog");
//        options.noReset();

        //hybrid app
        //options.setChromedriverExecutable("//Users//lior//Downloads//Lior_Automation//chromedriver-mac-x64//chromedriver");
        //web app
        //options.setCapability("browserName", "Chrome");

        driver = new IOSDriver(service.getUrl(), options);
        //global wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        stopAppiumServer();
    }

    public void launchApp(String bundleId) {
        driver.executeScript("mobile: launchApp", ImmutableMap.of(
                "bundleId", bundleId
        ));
    }

}
