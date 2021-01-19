package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;

public class TC01 extends BaseTest {
    @Test(dataProvider = "test-data")
    public void searchBook(String url, String searchKey, String firstProductExpectName) {
        getDriver().manage().window().maximize();

        HomePage homePage = new HomePage(getDriver());
        homePage.navigateToHomePage(url);
        homePage.selectOption("Books");
        homePage.endSearch(searchKey);

        ResultPage resultPage = new ResultPage(getDriver());
        String firstProductActualName = resultPage.getFirstProductName();

        Assert.assertEquals(firstProductActualName, firstProductExpectName);
    }

    @DataProvider(name = "test-data")
    public Object[][] getData() throws Exception {
        return ExcelUtils.getDataArray("data.xlsx", "1", 1, 1, 3, 3);
    }
}
