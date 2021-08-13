package utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotReader {

    public static String readText(WebDriver driver) throws TesseractException {
        TakesScreenshot ts = (TakesScreenshot)  driver;
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        File source = ts.getScreenshotAs(OutputType.FILE);
        return  tesseract.doOCR(source);
    }
}
