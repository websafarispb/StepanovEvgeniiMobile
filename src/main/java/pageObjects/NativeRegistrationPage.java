package pageObjects;

import static utils.WebElementChecker.tryFindElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class NativeRegistrationPage extends BasePage{

    protected AppiumDriver appiumDriver;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement emailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement userNameField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement newPasswordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement confirmationPasswordField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.CheckedTextView")
    WebElement agreementsCheck;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView")
    WebElement credentialsErrorMessage;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerButton;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_cancel_button")
    WebElement cancelButton;

    public NativeRegistrationPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void fillAndSubmitRegistrationForm(String email, String name, String password) {
        if (tryFindElement(credentialsErrorMessage)) {
            click(credentialsErrorMessage);
            emailField.clear();
        }
        fillField(emailField, email);
        fillField(userNameField, name);
        fillField(newPasswordField, password);
        fillField(confirmationPasswordField, password);
        click(registerButton);
    }
}
