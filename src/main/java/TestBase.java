import com.codeborne.selenide.Configuration;
import globalEntities.Environment;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeSuite;

@Log
public class TestBase {

    public static Environment env;

    @Step
    @BeforeSuite
    public void init() {
        String environment = System.getProperty("environment");
        environment = (StringUtils.isEmpty(environment)) ? "dev" : environment;
        env = new Environment(environment);
        env.setUpEnvironment();
        setUpBrowserConfig();
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
}