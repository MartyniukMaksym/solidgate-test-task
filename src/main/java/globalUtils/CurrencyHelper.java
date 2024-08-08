package globalUtils;

import io.qameta.allure.Step;

import java.util.Currency;

public class CurrencyHelper {

    /**
     * Method will return currency symbol by code e.g. € for EUR
     *
     * @param currencyCode currency code
     * @return currency symbol
     */
    @Step
    public static String getCurrencySymbolByCurrencyCode(String currencyCode) {
        return Currency.getInstance(currencyCode).getSymbol();
    }
}
