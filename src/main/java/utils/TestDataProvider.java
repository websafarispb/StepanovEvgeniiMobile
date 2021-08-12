package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    private static final PropertyReader reader = new PropertyReader();

    @DataProvider(name = "dataForWeb")
    public Object[][] dataForWeb() {
        return new Object[][] {{reader.getUrl(), reader.getWebTitle(), reader.getKeyWord()}};
    }

    @DataProvider(name = "dataForNative")
    public Object[][] dataForNative() {
        return new Object[][] {
            {reader.getEmail(), reader.getUserName(), reader.getPassword(), reader.getNativeTitle()}};
    }

    @DataProvider(name = "wrongDataForNative")
    public Object[][] wrongDataForNative() {
        return new Object[][] {{reader.getWrongEmail(), reader.getWrongUserName(), reader.getWrongPassword()}};
    }
}
