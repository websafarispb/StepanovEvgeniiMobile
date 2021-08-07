package scenarios;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.PageObject;
import pageObjects.WebPageObject;
import setup.BaseTest;
import utils.AndroidTestDataProvider;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened Google homepage and make search",
          dataProvider = "dataForWeb",
          dataProviderClass = AndroidTestDataProvider.class)
    public void checkWebPage(String url, String siteName, String keyWord) throws InterruptedException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        getDriver().get(url);

        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        assert ((WebDriver) getDriver()).getTitle().equals(siteName) : "This is not " + siteName + " homepage";

        WebPageObject webPageObject = (WebPageObject)((PageObject)getPo()).getSomePageObject();
        webPageObject.inputField.click();
        webPageObject.inputField.sendKeys(keyWord);

        getDriver().getKeyboard().sendKeys(Keys.ENTER);
        getDriver().hideKeyboard();

        assert (!webPageObject.resultLinks.isEmpty()) : "Result of searching ie empty";
    }
}
