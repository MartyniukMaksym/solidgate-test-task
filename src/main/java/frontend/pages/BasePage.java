package frontend.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.java.Log;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@Log
public class BasePage {

    private static final SelenideElement loader = $(".spinner");

    /**
     * Method will wait while loader is displayed on UI.
     */
    @Step
    public static void waitWhileLoaderDisappears() {

        log.info("Waiting while loader disappears.");
        if ($(loader).isDisplayed()) {
            $(loader).shouldBe(Condition.disappear, Duration.ofSeconds(20));
        }
    }
}
