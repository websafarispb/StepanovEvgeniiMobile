package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebPageObject extends BasePage {

    @FindBy(xpath = "//input[@name='q']")
    WebElement inputField;

    @FindBy(xpath = "//div[@id='rso']/div")
    List<WebElement> resultLinks;

    public WebPageObject(AppiumDriver appiumDriver, String platformName) {
        super(appiumDriver, platformName);
    }

    public void navigateToUrl(String url) {
        appiumDriver.get(url);
        wait.until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public String getTitle() {
        return appiumDriver.getTitle();
    }

    public void makeSearchByWord(String keyWord) {
        click(inputField);
        fillField(inputField, keyWord);
        if (platformName.equals("Android")) {
            inputField.sendKeys(Keys.ENTER);
        } else {
            inputField.submit();
        }
    }

    public List<WebElement> getResultLinks() {
        wait.until(ExpectedConditions.visibilityOfAllElements(resultLinks));
        return resultLinks;
    }
}
