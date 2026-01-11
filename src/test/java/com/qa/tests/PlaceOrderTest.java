package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.framework.base.BaseClass;
import com.qa.framework.pages.CartPage;
import com.qa.framework.pages.CheckoutPage;
import com.qa.framework.pages.DashboardPage;
import com.qa.framework.pages.LoginPage;
import com.qa.framework.pages.ThanksPage;
import com.qa.framework.utils.DataReader;

public class PlaceOrderTest extends BaseClass {

    @DataProvider
    public Object[][] getOrderData() throws IOException {
        List<HashMap<String, String>> data =
                DataReader.getJsonDataToMap(
                        System.getProperty("user.dir") +
                        "/src/test/resources/LoginDataProductData.json");

        return data.stream().map(e -> new Object[] { e }).toArray(Object[][]::new);
    }

    @Test(dataProvider = "getOrderData")
    public void placeOrder(HashMap<String, String> input) {

        DashboardPage dashboard =
                new LoginPage(driver, wait)
                        .openUrl(bm.getWebUrl())
                        .loginpageActions(input.get("userid"), input.get("password"));

        CartPage cart = dashboard.addProductToCart(input.get("productName"));
        Assert.assertTrue(cart.verifyCartProductDisplay(input.get("productName")));

        CheckoutPage checkout = cart.checkOutPage();
        ThanksPage thanks = checkout.selectCountry("India").submitOrder();

      
        Assert.assertTrue(
        	    thanks.getConfirmationMessage()
        	          .equalsIgnoreCase("Thankyou for the order."),
        	    "Order confirmation message mismatch"
        	);
    }
}
