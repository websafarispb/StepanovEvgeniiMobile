package scenarios;

import static utils.ScreenshotReader.readText;
import static utils.WebElementChecker.tryFindElement;

import java.time.Duration;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.natives.NativeBudgetPage;
import pages.natives.NativeRegistrationPage;
import setup.BaseTest;
import utils.TestDataProvider;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Check registration and sign in application",
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
        String screenText = nativeBudgetPage.getScreenText();
        assert (screenText.contains("Incorrect")) : "Do not see error !!!";
    }
}
