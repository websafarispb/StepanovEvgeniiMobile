package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebPageObject {

    @FindBy(xpath = "//input[@name='q']")
    public WebElement inputField;

    @FindBy(xpath = "//div[@id='rso']/div")
    public List <WebElement> resultLinks;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
