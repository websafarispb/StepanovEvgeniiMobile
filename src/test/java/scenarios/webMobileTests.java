package scenarios;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.PageObject;
import pageObjects.WebPageObject;
import setup.BaseTest;
import utils.TestDataProvider;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened Google homepage and make search",
          dataProvider = "dataForWeb",
          dataProviderClass = TestDataProvider.class)
    public void checkWebPage(String url, String title, String keyWord)
        throws InterruptedException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        getDriver().get(url);

        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        assert ((WebDriver) getDriver()).getTitle().equals(title) : "This is not " + title + " homepage";

        WebPageObject webPageObject = (WebPageObject) ((PageObject) getPo()).getActivePageObject();
        webPageObject.inputField.click();
        webPageObject.inputField.sendKeys(keyWord);

        getDriver().getKeyboard().sendKeys(Keys.ENTER);
        getDriver().hideKeyboard();
        webPageObject.resultLinks.forEach(l -> {
            assert (l.getText().contains(keyWord)) : String.format("Result doesn't have keyword - %s ", keyWord);
        });
    }
}
