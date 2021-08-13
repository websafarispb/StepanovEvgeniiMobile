package pages;

import static utils.ScreenshotReader.readText;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected AppiumDriver appiumDriver;
    protected WebDriverWait wait;
    protected String platformName;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 20);
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public BasePage(AppiumDriver appiumDriver, String platformName) {
        this.platformName = platformName;
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, 20);
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void fillField(WebElement webElement, String value) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(value);
    }

    public void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public String getElementText(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public MobileDriver getDriver(){
        return appiumDriver;
    }

    public String getScreenText() throws TesseractException {
       return readText(appiumDriver);
    }
}
