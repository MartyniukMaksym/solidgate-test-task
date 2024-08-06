import backend.requests.entities.CreatePaymentPageRequestBody;
import backend.requests.entities.OrderStatusRequestBody;
import frontend.pages.PaymentPage;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static globalEntities.enums.TransactionStatus.SUCCESS;


@Log
public class PaymentTests extends TestBase {

    private static PaymentPage paymentPage;
    private String testOrderId;
    private int testAmount;
    private String testCurrency;

    @BeforeMethod
    public void initPages() {
        paymentPage = new PaymentPage();
    }

    @Test
    public void cretePaymentPageTest() {

        testOrderId = UUID.randomUUID().toString();
        testAmount = 1299;
        testCurrency = "EUR";

        log.info("Creating Payment page request body.");
        CreatePaymentPageRequestBody requestBody = CreatePaymentPageRequestBody.setDefaultValuesFromJson();

        requestBody.getOrder().setAmount(testAmount);
        requestBody.getOrder().setOrderId(testOrderId);
        requestBody.getOrder().setCurrency(testCurrency);

        log.info("Getting created payment page response.");
        var paymentPageResponse = createPaymentPage(requestBody);
        var paymentPageUrl = paymentPageResponse.getUrl();

        paymentPage.openPaymentPage(paymentPageUrl);
        var displayedPrice = paymentPage.getDisplayedPrice();
        paymentPage.fillPaymentData("4067429974719265", "1225", "123", "Test Testovich");
        paymentPage.submitPaymentAndWaitForRedirect(requestBody.getOrder().getSuccessUrl());
        paymentPage.closePaymentPage();

        log.info("Asserting test results.");
        Assert.assertEquals(trimPrice(displayedPrice), testAmount,
                "Amount on UI mismatch expectations.");
    }

    @Test(dependsOnMethods = "cretePaymentPageTest")
    public void checkOrderStatusTest() {

        log.info("Creating order status request body.");
        OrderStatusRequestBody orderStatusRequestBody = new OrderStatusRequestBody();
        orderStatusRequestBody.setOrderId(testOrderId);

        log.info("Getting order status response.");
        var orderStatusResponse = getOrderStatus(orderStatusRequestBody);
        var orderAmount = orderStatusResponse.getOrder().getAmount();
        var orderCurrency = orderStatusResponse.getOrder().getCurrency();

        var transactionMap = orderStatusResponse.getTransactions();
        var orderTransactionStatus = transactionMap.entrySet().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There are no transactions in response."))
                .getValue()
                .getStatus();

        log.info("Asserting test results.");
        Assert.assertEquals(orderTransactionStatus, SUCCESS.getStatusName(), "Order status is not success.");
        Assert.assertEquals(orderAmount, testAmount, "Amount in order status response mismatch expectations.");
        Assert.assertEquals(orderCurrency, testCurrency, "Currency in order status response mismatch expectations.");
    }

    private int trimPrice(String price) {
        log.info("Removing price character and delimiter from price.");
        String priceWithoutSymbol = price.substring(1);
        String cleanedPrice = priceWithoutSymbol.replace(".", "");
        return Integer.parseInt(cleanedPrice);
    }
}
