package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    private WebElement searchOption;
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchInput;
    @FindBy(xpath = "//input[@type='submit'][@value='Go']")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage(String url) {
        driver.navigate().to(url);
    }

    public void selectOption(String option) {
        Select select = new Select(searchOption);
        select.selectByVisibleText(option);
    }

    public void endSearch(String productText) {
        searchInput.sendKeys(productText);
        searchBtn.click();
    }


}
