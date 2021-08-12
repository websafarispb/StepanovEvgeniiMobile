package scenarios;

import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.NativeBudgetPage;
import pageObjects.NativeIndexPage;
import pageObjects.NativeRegistrationPage;
import pageObjects.PageObject;
import setup.BaseTest;

import java.time.Duration;
import utils.TestDataProvider;

import static utils.ScreenshotReader.readText;
import static utils.WebElementChecker.tryFindElement;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Check registration and sign in android application",
          dataProvider = "dataForNative",
          dataProviderClass = TestDataProvider.class)
    public void checkRegistrationAndSignIn(String email, String name, String password, String title)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        NativeRegistrationPage nativeRegistrationPage = nativeIndexPage.goToRegistrationPage();
        nativeRegistrationPage.fillAndSubmitRegistrationForm(email, name, password);
        NativeBudgetPage nativeBudgetPage = nativeIndexPage.loginAndGoToNativeNativeBudgetPage(email, password);

        assert (nativeBudgetPage.getTitle().contains(title)) : "Error!!!";
    }

    @Test(groups = {"native"}, description = "Check appearance AutoCompleteTextView",
          dataProvider = "wrongDataForNative",
          dataProviderClass = TestDataProvider.class)
    public void checkIncorrectAppearAutoCompleteTextView(String email, String name, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException,
        TesseractException {

        NativeBudgetPage nativeBudgetPage = nativeIndexPage.loginAndGoToNativeNativeBudgetPage(email, password);
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.withTimeout(Duration.ofMillis(10000));
        String result = readText(getDriver());

        assert (result.contains("Incorrect email or password")) : "Do not see error !!!";
    }
}
