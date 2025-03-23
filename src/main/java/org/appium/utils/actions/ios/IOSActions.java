package org.appium.utils.actions.ios;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.appium.utils.actions.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class IOSActions extends AppiumUtils {

    IOSDriver driver;


    public IOSActions(IOSDriver driver) {
        this.driver = driver;
    }

    public void longPressAction(WebElement element, int duration) {
        driver.executeScript("mobile: touchAndHold", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), "duration", duration
        ));
    }

    public void scrollToElementAction(WebElement element) {
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement) element).getId());
        params.put("direction", "down");
        params.put("percent", 1.0);

        Boolean canScrollMore;
        do {
            canScrollMore = (Boolean) driver.executeScript("mobile: scroll", params);
        }
        while (Boolean.TRUE.equals(canScrollMore));
    }


    public void swipeAction(WebElement element, String direction) {
        driver.executeScript("mobile: swipe", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.2
        ));
    }

    public void swipeAction(String direction) {
        driver.executeScript("mobile: swipe", ImmutableMap.of(
                "direction", direction,
                "percent", 0.2
        ));
    }
}
