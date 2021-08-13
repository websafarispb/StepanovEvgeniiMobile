package setup;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.natives.NativeBudgetPage;
import pages.natives.NativeIndexPage;
import pages.natives.NativeRegistrationPage;
import pages.WebPageObject;

public class BaseTest {

    private static AppiumDriver appiumDriver; // singleton
    protected NativeIndexPage nativeIndexPage;
    protected WebPageObject webPageObject;

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity",
        "bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platformName, String appType, @Optional("") String deviceName, @Optional("") String udid,
                      @Optional("") String browserName, @Optional("") String app, @Optional("") String appPackage,
                      @Optional("") String appActivity, @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        switch (appType) {
            case "web":
                webPageObject = new WebPageObject(appiumDriver, platformName);
                break;
            case "native":
                nativeIndexPage = new NativeIndexPage(appiumDriver, platformName);
                break;
            default:
                throw new Exception("Can't create a page object for " + appType);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName, String app,
                                 String appPackage, String appActivity, String bundleId) throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("bundleId", bundleId);
        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        try {
            appiumDriver = new AppiumDriver(
                new URL(System.getProperty("ts.appium")), capabilities);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
