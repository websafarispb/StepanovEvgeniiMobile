package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebElementChecker {

    public static boolean tryFindElement(WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
}
