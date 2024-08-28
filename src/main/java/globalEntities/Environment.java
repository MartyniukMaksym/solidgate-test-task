package globalEntities;

import globalEntities.enums.EnvVariables;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

@Log
@Getter
public class Environment {

    private String PUBLIC_KEY;
    private String SECRET_KEY;
    private final String environmentName;

    private String PAYMENT_PAGE_URL;
    private String ORDER_STATUS_URL;

    public Environment(String env) {
        this.environmentName = env;
    }

    /**
     * Method will setUp all environment variables.
     */
    public void setUpEnvironment() {

        setUpCredentials();

        log.info("Setting up URLs according to environment.");
        switch (environmentName) {
            case "dev":
                PAYMENT_PAGE_URL = "https://payment-page.solidgate.com/api/v1/init";
                ORDER_STATUS_URL = "https://pay.solidgate.com/api/v1/status";
                break;
            case "int":
                PAYMENT_PAGE_URL = "https://payment-page.solidgate.com/api/v1/init";
                ORDER_STATUS_URL = "https://pay.solidgate.com/api/v1/status";
            default:
                throw new IllegalArgumentException("Unsupported environmentName: " + environmentName);
        }
    }

    /**
     * Method to set up user credentials.
     */
    @Step
    private void setUpCredentials() {

        log.info("Getting public key for authentication.");
        PUBLIC_KEY = System.getProperty(EnvVariables.PUBLIC_KEY.name());
        if (StringUtils.isEmpty(PUBLIC_KEY)) {
            String envPK = System.getenv(EnvVariables.PUBLIC_KEY.name());
            if (StringUtils.isEmpty(envPK)) {
                throw new RuntimeException("PUBLIC_KEY is missing in env/properties variables.");
            }
            PUBLIC_KEY = envPK;
        }

        log.info("Getting secret key for authentication.");
        SECRET_KEY = System.getProperty(EnvVariables.SECRET_KEY.name());
        if (StringUtils.isEmpty(SECRET_KEY)) {
            String envSK = System.getenv(EnvVariables.SECRET_KEY.name());
            if (StringUtils.isEmpty(envSK)) {
                throw new RuntimeException("SECRET_KEY is missing in env/properties variables.");
            }
            SECRET_KEY = envSK;
        }
    }
}
