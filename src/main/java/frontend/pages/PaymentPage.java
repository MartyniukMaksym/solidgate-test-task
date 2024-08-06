package frontend.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log
public class PaymentPage extends BasePage {

    private static final SelenideElement displayedPrice = $x("//div[@data-testid='price_major']");
    private static final SelenideElement cardNumberInput = $x("//input[@name='cardNumber']");
    private static final SelenideElement cardExpiryDateInput = $x("//input[@name='cardExpiryDate']");
    private static final SelenideElement cardCvvInput = $x("//input[@name='cardCvv']");
    private static final SelenideElement cardHolderNameInput = $x("//input[@name='cardHolder']");
    private static final SelenideElement payBtn = $x("//button[@type='submit']");

    /**
     * Method will open payment page
     *
     * @param URL payment page URL
     */
    @Step
    public void openPaymentPage(String URL) {
        log.info("Opening payment page on URL: '" + URL + "'.");
        open(URL);
        waitWhileLoaderDisappears();
        payBtn.should(Condition.appear);
    }

    /**
     * Method will return price displayed on UI
     *
     * @return displayed price
     */
    @Step
    public String getDisplayedPrice() {
        return displayedPrice.getText();
    }

    /**
     * Method will fill up payment data
     *
     * @param cardNumber     card number
     * @param expiryDate     expiry date
     * @param cvv            cvv
     * @param cardHolderName cardholder name
     */
    @Step
    public void fillPaymentData(String cardNumber, String expiryDate, String cvv, String cardHolderName) {

        log.info("Fill up user card data.");
        cardNumberInput.scrollTo().click();
        cardNumberInput.setValue(cardNumber);

        log.info("Fill up user card expiry date.");
        cardExpiryDateInput.scrollTo().click();
        cardExpiryDateInput.setValue(expiryDate);

        log.info("Fill up user card cvv.");
        cardCvvInput.scrollTo().click();
        cardCvvInput.setValue(cvv);

        log.info("Fill up user name.");
        cardHolderNameInput.scrollTo().click();
        cardHolderNameInput.setValue(cardHolderName);
    }

    /**
     * Method will submit payment and wait for appearance of redirected page
     *
     * @param redirectURL expected redirect URL
     */
    @Step
    public void submitPaymentAndWaitForRedirect(String redirectURL) {
        log.info("Submitting payment.");
        payBtn.scrollTo().click();
        waitWhileLoaderDisappears();
        waitForRedirect(redirectURL);
    }

    private void waitForRedirect(String url) {
        log.info("Waiting for redirect to url '" + url + "'");
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Method will close payment page
     */
    @Step
    public void closePaymentPage() {
        log.info("Closing Payment page.");
        WebDriverRunner.closeWebDriver();
    }
}
