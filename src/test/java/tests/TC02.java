package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.StorePage;

import java.util.concurrent.TimeUnit;

public class TC02 extends BaseTest {
    @BeforeClass
    public void beforeClass() {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "test-data")
    public void addProductToCart(String expectedProductTitle, double expectedProductPrice) throws InterruptedException {
        getDriver().manage().window().maximize();
        StorePage storePage = new StorePage(getDriver());
        storePage.navigateToStore("http://automationpractice.com/index.php");
        storePage.inputProductSearch(expectedProductTitle);
        storePage.submitSearchButton();
        storePage.firstSearchProductTitle();
        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        String actualProductTitle = productDetailPage.getProductTitle();
        double actualProductPrice = productDetailPage.getProductPrice();
        Assert.assertEquals(actualProductTitle.trim().toLowerCase(), expectedProductTitle.trim().toLowerCase());
        Assert.assertEquals(actualProductPrice, expectedProductPrice);

        productDetailPage.clickButtonAddToCart();

        String expectAddToCartSuccessfullyConfirmationText = "Product successfully added to your shopping cart";
        String actualAddToCartSuccessfullyConfirmationText = productDetailPage.getAddToCartConfirmationText();
        Assert.assertEquals(actualAddToCartSuccessfullyConfirmationText.trim().toLowerCase(), expectAddToCartSuccessfullyConfirmationText.trim().toLowerCase());
        productDetailPage.clickButtonProceedToCheckout();
        CartPage cartPage = new CartPage(getDriver());
        int indexOfProductInCart = cartPage.getIndexOfProductExisted(expectedProductTitle);
        Assert.assertTrue(indexOfProductInCart >= 0);
        double actualPrice = cartPage.getProductPriceInCart(indexOfProductInCart);
        Assert.assertEquals(actualPrice, expectedProductPrice);
    }

    @DataProvider(name = "test-data")
    public Object[][] getData() throws Exception {
        return ExcelUtils.getDataArray("data.xlsx", "2", 1, 1, 3, 2);
    }
}
