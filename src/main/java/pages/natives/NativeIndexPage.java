package pages.natives;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class NativeIndexPage extends BasePage {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='user@example.com']")
    WebElement loginMailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Required']")
    WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Sign In']")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Register new account']")
    WebElement registerAccountButton;

    public NativeIndexPage(AppiumDriver appiumDriver, String platformName) {
        super(appiumDriver, platformName);
    }

    public NativeRegistrationPage goToRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        registerAccountButton.click();
        return new NativeRegistrationPage(this.appiumDriver, this.platformName);
    }

    public NativeBudgetPage loginAndGoToNativeNativeBudgetPage(String email, String password) {
        if(platformName.equals("Android")){
            getDriver().hideKeyboard();
        }
        fillField(loginMailField, email);
        fillField(passwordField, password);
        click(signInBtn);
        return new NativeBudgetPage(this.appiumDriver);
    }
}
