package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {

    private int amount;
    private String currency;
    private String customerEmail;
    private String descriptor;
    private int marketingAmount;
    private String marketingCurrency;
    private String mid;
    private String orderDescription;
    private String orderId;
    private String paymentType;
    private int processingAmount;
    private String processingCurrency;
    private int refundedAmount;
    private String status;
    private String subscriptionId;
    private String trafficSource;
    private Boolean fraudulent;
}
