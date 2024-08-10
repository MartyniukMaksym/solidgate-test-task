package backend.requests.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

import static globalUtils.ReadFromJsonFile.readValuesFromJson;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {

    private int amount;
    private String currency;
    private String customerDateOfBirth;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String failUrl;
    private Boolean force3ds;
    private String geoCity;
    private String geoCountry;
    private List<String> googlePayAllowedAuthMethods;
    private String language;
    private String orderDate;
    private String orderDescription;
    private String orderId;
    private String orderItems;
    private OrderMetadata orderMetadata;
    private int orderNumber;
    private String purchaseCountry;
    private int retryAttempt;
    private int settleInterval;
    private String successUrl;
    private String trafficSource;
    private String transactionSource;
    private String type;
    private String website;

    public static Order.OrderBuilder builderWithDefaults() {

        Order order;
        order = readValuesFromJson("src/main/resources/testJsonsData/defaultPaymentPageValues.json",
                "order", Order.class);

        return Order.builder()
                .amount(order.getAmount())
                .currency(order.getCurrency())
                .customerDateOfBirth(order.getCustomerDateOfBirth())
                .customerEmail(order.getCustomerEmail())
                .customerFirstName(order.getCustomerFirstName())
                .customerLastName(order.getCustomerLastName())
                .customerPhone(order.getCustomerPhone())
                .failUrl(order.getFailUrl())
                .force3ds(order.getForce3ds())
                .geoCity(order.getGeoCity())
                .geoCountry(order.getGeoCountry())
                .googlePayAllowedAuthMethods(order.getGooglePayAllowedAuthMethods())
                .language(order.getLanguage())
                .orderDate(order.getOrderDate())
                .orderDescription(order.getOrderDescription())
                .orderId(order.getOrderId())
                .orderItems(order.getOrderItems())
                .orderMetadata(OrderMetadata.builderWithDefaults().build())
                .orderNumber(order.getOrderNumber())
                .purchaseCountry(order.getPurchaseCountry())
                .retryAttempt(order.getRetryAttempt())
                .settleInterval(order.getSettleInterval())
                .successUrl(order.getSuccessUrl())
                .trafficSource(order.getTrafficSource())
                .transactionSource(order.getTransactionSource())
                .type(order.getType())
                .website(order.getWebsite());
    }
}