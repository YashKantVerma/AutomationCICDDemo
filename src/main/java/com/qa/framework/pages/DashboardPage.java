package com.qa.framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Dynamic locator (button inside product card)
    By addToCartBtn = By.cssSelector(".card-body button:last-of-type");

    // 🔹 PageFactory elements
    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = "#toast-container")
    private WebElement toastMessage;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;
    
    private By spinnerBy = By.cssSelector(".ngx-spinner-overlay");
    
    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartLink;

    @FindBy(css = ".cartSection")
    private WebElement cartSection;

    @FindBy(css = "[routerlink*='myorders']")
    private WebElement ordersLink;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // ------------------- Actions -------------------

    public List<WebElement> getProductList() {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText()
                                .equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + productName));
    }

    public CartPage addProductToCart(String productName) {

        WebElement product = getProductByName(productName);
        product.findElement(addToCartBtn).click();

        // wait for toast
        wait.until(ExpectedConditions.visibilityOf(toastMessage));

        // wait for spinner to disappear (robust)
        wait.until(ExpectedConditions.or(
            ExpectedConditions.invisibilityOf(spinner),
            ExpectedConditions.numberOfElementsToBe(spinnerBy, 0)
        ));

        // EXTRA safety for Angular
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));

        cartLink.click();

        // wait for cart page
        wait.until(ExpectedConditions.visibilityOf(cartSection));

        return new CartPage(driver, wait);
    }

    public OrdersPage goToOrdersPage() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink)).click();
        return new OrdersPage(driver, wait);
    }
}
