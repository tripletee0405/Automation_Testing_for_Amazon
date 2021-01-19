package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class StorePage extends BasePage {
    @FindBy(id = "search_query_top")
    private WebElement inputSearch;
    @FindBy(name = "submit_search")
    private WebElement submitBtnSearch;
    @FindBy(xpath = "//h5[@itemprop='name']/a[@class='product-name'][1]")
    private WebElement firstProductTitle;

    public StorePage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void navigateToStore(String url) {
        driver.navigate().to(url);
    }

    public void inputProductSearch(String productTitle) {
        inputSearch.clear();
        inputSearch.sendKeys(productTitle);
    }

    public void submitSearchButton() {
        submitBtnSearch.click();
    }

    public void firstSearchProductTitle() {
        firstProductTitle.click();
    }
}
