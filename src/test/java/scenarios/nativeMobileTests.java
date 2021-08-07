package scenarios;

import io.appium.java_client.android.AndroidDriver;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.NativePageObject;
import pageObjects.PageObject;
import pageObjects.WebPageObject;
import setup.BaseTest;

import java.io.File;
import java.time.Duration;
import utils.AndroidTestDataProvider;

import static utils.ScreenshotReader.readText;
import static utils.WebElementChecker.tryFindElement;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Check registration and sign in android application",
          dataProvider = "dataForNative",
          dataProviderClass = AndroidTestDataProvider.class)
    public void checkRegistrationAndSignIn(String email, String name, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        if(tryFindElement(getPo().getWelement("emailTextView"))){
            getPo().getWelement("emailTextView").click();
        }

        getPo().getWelement("registerAccountButton").click();
        getDriver().hideKeyboard();
        getPo().getWelement("emailField").sendKeys(email);
        getPo().getWelement("userNameField").sendKeys(name);
        getPo().getWelement("newPasswordField").sendKeys(password);
        getPo().getWelement("confirmationPasswordField").sendKeys(password);
        getPo().getWelement("agreementsCheck").click();
        getPo().getWelement("registerButton").click();

        if(tryFindElement(getPo().getWelement("emailTextView"))){
            getPo().getWelement("emailTextView").click();
        }

        getPo().getWelement("loginMailField").clear();
        getPo().getWelement("loginMailField").sendKeys(email);
        getPo().getWelement("passwordField").sendKeys(password);
        getPo().getWelement("signInBtn").click();

        assert (getPo().getWelement("budgetTitle").getText().equals("BudgetActivity")) : "Error!!!";

    }

    @Test(groups = {"native"}, description = "Check appearance AutoCompleteTextView",
          dataProvider = "dataForNative",
          dataProviderClass = AndroidTestDataProvider.class)
    public void checkIncorrectAppearAutoCompleteTextView (String email, String name, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException,
        TesseractException {

        if(tryFindElement(getPo().getWelement("emailTextView"))){
            getPo().getWelement("emailTextView").click();
        }

        getDriver().hideKeyboard();
        getPo().getWelement("loginMailField").sendKeys(email);
        getPo().getWelement("passwordField").sendKeys(password);
        getPo().getWelement("signInBtn").click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.withTimeout(Duration.ofMillis(10000));
        String result = readText(getDriver());

        assert (result.contains("Incorrect email or password")) : "Do not see error !!!";
    }
}
