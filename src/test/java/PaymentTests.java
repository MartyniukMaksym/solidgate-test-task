import backend.requests.CreatePaymentPageRequest;
import backend.requests.GetOrderStatusRequest;
import backend.requests.entities.CreatePaymentPageRequestBody;
import backend.requests.entities.OrderStatusRequestBody;
import backend.requests.entities.basic.Order;
import frontend.pages.PaymentPage;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static globalEntities.enums.TransactionStatus.SUCCESS;
import static globalUtils.CurrencyHelper.getCurrencySymbolByCurrencyCode;


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
        var testCurrencySymbol = getCurrencySymbolByCurrencyCode(testCurrency);

        log.info("Creating order entity for testing.");
        Order order = Order.builderWithDefaults()
                .orderId(testOrderId)
                .amount(testAmount)
                .currency(testCurrency)
                .build();

        log.info("Creating Payment page request body.");
        var requestBody = CreatePaymentPageRequestBody
                .builderWithDefaults()
                .order(order)
                .build();

        log.info("Getting created payment page response.");
        var paymentPageRequest = new CreatePaymentPageRequest(env);
        var paymentPageResponse = paymentPageRequest.createPaymentPage(requestBody);

        var paymentPageUrl = paymentPageResponse.getUrl();

        paymentPage.openPaymentPage(paymentPageUrl);
        var displayedPrice = paymentPage.getDisplayedPrice();
        paymentPage.fillPaymentData("4067429974719265", "1225", "123", "Test Testovich");
        paymentPage.submitPaymentAndWaitForRedirect(requestBody.getOrder().getSuccessUrl());
        paymentPage.closePaymentPage();

        log.info("Asserting test results.");
        Assert.assertEquals(trimPrice(displayedPrice), testAmount,
                "Amount on UI mismatch expectations.");
        Assert.assertEquals(getCurrencySymbol(displayedPrice), testCurrencySymbol,
                "Currency on UI mismatch expectations.");
    }

    @Test(dependsOnMethods = "cretePaymentPageTest")
    public void checkOrderStatusTest() {

        log.info("Creating order status request body.");
        var orderStatusRequestBody = OrderStatusRequestBody
                .builderWithDefaults()
                .orderId(testOrderId)
                .build();

        log.info("Getting order status response.");
        var orderStatusRequest = new GetOrderStatusRequest(env);
        var orderStatusResponse = orderStatusRequest.getOrderStatus(orderStatusRequestBody);

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

    private String getCurrencySymbol(String rawPrice) {
        return String.valueOf(rawPrice.charAt(0));
    }
}