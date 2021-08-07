package utils;

import org.testng.annotations.DataProvider;

public class AndroidTestDataProvider {

    @DataProvider
    public Object [][] dataForWeb(){
        return new Object[][] {{"https://www.google.com/","Google", "EPAM"}};
    }

    @DataProvider
    public Object [][] dataForNative(){
        return new Object[][] {{"web@mail.com","Ivanov", "12345678"}};
    }
}
