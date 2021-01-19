package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductDetailPage extends BasePage {
    @FindBy(xpath = "//p[@id='product_reference']/preceding-sibling::h1[@itemprop='name']")
    private WebElement productTitle;
    @FindBy(id = "our_price_display")
    private WebElement productPrice;
    @FindBy(xpath = "//p[@id=\"add_to_cart\"]/button")
    private WebElement btnAddToCart;
    @FindBy(xpath = "//div[@id='layer_cart']//a/span[contains(text(), 'Proceed to checkout')]")
    private WebElement btnProceedToCheckout;
    @FindBy(xpath = "//div[@id='layer_cart']//h2/i/..")
    private WebElement textConfirm;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public double getProductPrice() throws NumberFormatException {
        String priceText = productPrice.getText().substring(1);
        return Double.parseDouble(priceText);
    }

    public void clickButtonAddToCart() {
        btnAddToCart.click();
    }

    public void clickButtonProceedToCheckout() {
        btnProceedToCheckout.click();
    }

    public String getAddToCartConfirmationText() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement popupConfirmAddToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        return textConfirm.getText();
    }
}
