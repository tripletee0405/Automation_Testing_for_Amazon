package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(xpath = "//table[@id='cart_summary']//tbody/tr//td[@class='cart_description']/p/a")
    private List<WebElement> productDescriptionList;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getIndexOfProductExisted(String productDescriptionText) {
        for (int i = 0; i < productDescriptionList.size(); i++) {
            WebElement textProductDescription = productDescriptionList.get(i);
            if (textProductDescription.getText().trim().equalsIgnoreCase(productDescriptionText.trim())) {
                return i;
            }
        }
        return -1;
    }

    public double getProductPriceInCart(int productIndex)
            throws NumberFormatException {
        WebElement priceTd = driver.findElements(By.xpath("//table[@id='cart_summary']//tbody/tr/td[@class='cart_unit']")).get(productIndex);
        WebElement priceSpanContainer = priceTd.findElement(By.className("price"));
        WebElement priceSpan = priceSpanContainer.findElement(By.className("price"));
        String priceText = priceSpan.getText().substring(1);
        return Double.parseDouble(priceText);
    }
}
