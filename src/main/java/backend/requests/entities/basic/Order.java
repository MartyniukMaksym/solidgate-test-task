package backend.requests.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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
    private Long retryAttempt;
    private int settleInterval;
    private String successUrl;
    private String trafficSource;
    private String transactionSource;
    private String type;
    private String website;
}
