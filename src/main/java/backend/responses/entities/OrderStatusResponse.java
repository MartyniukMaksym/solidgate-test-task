package backend.responses.entities;


import backend.responses.entities.basic.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderStatusResponse {

    private Chargebacks chargebacks;
    private DeviceInfo deviceInfo;
    private Order order;
    private OrderMetadata orderMetadata;
    private String redirectUrl;
    private Routing routing;
    private ThreeDs threeDs;
    private Transaction transaction;
    private Map<String, Transaction> transactions;
    private PayForm payForm;
    private PaymentAdviser paymentAdviser;
}
