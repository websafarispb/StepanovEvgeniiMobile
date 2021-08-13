package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;
import utils.TestDataProvider;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened Google homepage and make search",
          dataProvider = "dataForWeb",
          dataProviderClass = TestDataProvider.class)
    public void checkWebPage(String url, String siteName, String keyWord)
        throws InterruptedException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        webPageObject.navigateToUrl(url);

        assert (webPageObject.getTitle().equals(siteName)) : "This is not " + siteName + " homepage";

        webPageObject.makeSearchByWord(keyWord);

        webPageObject.getResultLinks().forEach(l -> {
            assert (l.getText().contains(keyWord)) : String.format("Result doesn't have keyword - %s ", keyWord);
        });
    }
}
