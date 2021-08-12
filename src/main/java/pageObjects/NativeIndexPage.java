package pageObjects;

import static utils.WebElementChecker.tryFindElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeIndexPage extends BasePage {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginMailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement registerAccountButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView")
    WebElement credentialsErrorMessage;

    public NativeIndexPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public NativeRegistrationPage goToRegistrationPage() {
        if (tryFindElement(credentialsErrorMessage)) {
            click(credentialsErrorMessage);
            loginMailField.clear();
        }
        wait.until(ExpectedConditions.elementToBeClickable(registerAccountButton));
        registerAccountButton.click();
        return new NativeRegistrationPage(appiumDriver);
    }

    public NativeBudgetPage loginAndGoToNativeNativeBudgetPage(String email, String password) {
        if (tryFindElement(credentialsErrorMessage)) {
            click(credentialsErrorMessage);
            loginMailField.clear();
        }
        fillField(loginMailField, email);
        fillField(passwordField, password);
        click(signInBtn);
        return new NativeBudgetPage(this.appiumDriver);
    }
}
