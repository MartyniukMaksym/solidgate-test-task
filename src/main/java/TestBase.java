import backend.requests.SendBackendRequest;
import backend.requests.entities.CreatePaymentPageRequestBody;
import backend.requests.entities.OrderStatusRequestBody;
import backend.responses.entities.CreatePaymentPageResponse;
import backend.responses.entities.OrderStatusResponse;
import com.codeborne.selenide.Configuration;
import globalUtils.SignatureGenerator;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeSuite;

import static globalEntities.enums.EnvVariables.PUBLIC_KEY;
import static globalEntities.enums.EnvVariables.SECRET_KEY;

@Log
public class TestBase {
    private static String publicKey;
    private static String secretKey;
    private static final String paymentPageURL = "https://payment-page.solidgate.com/api/v1/init";
    private static final String orderStatusURL = "https://pay.solidgate.com/api/v1/status";

    @Step
    @BeforeSuite
    public void init() {
        setUpBrowserConfig();
        setUpCredentials();
    }

    /**
     * Method to set up user credentials.
     */
    @Step
    public void setUpCredentials() {

        log.info("Getting secrets for authentication.");
        publicKey = System.getProperty(PUBLIC_KEY.name());
        if (StringUtils.isEmpty(publicKey)) {
            String envPK = System.getenv(PUBLIC_KEY.name());
            if (StringUtils.isEmpty(envPK)) {
                throw new RuntimeException("PUBLIC_KEY is missing in env/properties variables.");
            }
            publicKey = envPK;
        }

        secretKey = System.getProperty(SECRET_KEY.name());
        if (StringUtils.isEmpty(secretKey)) {
            String envSK = System.getenv(SECRET_KEY.name());
            if (StringUtils.isEmpty(envSK)) {
                throw new RuntimeException("SECRET_KEY is missing in env/properties variables.");
            }
            secretKey = envSK;
        }
    }

    /**
     * Method to set up browser configuration.
     */
    @Step
    public void setUpBrowserConfig() {

        log.info("Setting up browser configs for testing.");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1400x900";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 7000;
        Configuration.headless = false;
    }

    /**
     * Method will send request to create payment page and return response
     *
     * @param requestBody CreatePaymentPageRequestBody
     * @return CreatePaymentPageResponse
     */
    @Step
    public CreatePaymentPageResponse createPaymentPage(CreatePaymentPageRequestBody requestBody) {

        var signature = SignatureGenerator.generateSignature(publicKey, requestBody, secretKey);

        return SendBackendRequest.builder()
                .url(paymentPageURL)
                .requestBody(requestBody)
                .merchant(publicKey)
                .signature(signature)
                .build()
                .send()
                .extract().body().as(CreatePaymentPageResponse.class);
    }

    /**
     * Method will send request to check order status and return response
     *
     * @param requestBody OrderStatusRequestBody
     * @return OrderStatusResponse
     */
    @Step
    public OrderStatusResponse getOrderStatus(OrderStatusRequestBody requestBody) {

        var signature = SignatureGenerator.generateSignature(publicKey, requestBody, secretKey);

        return SendBackendRequest.builder()
                .url(orderStatusURL)
                .requestBody(requestBody)
                .merchant(publicKey)
                .signature(signature)
                .build()
                .send()
                .extract().body().as(OrderStatusResponse.class);
    }
}