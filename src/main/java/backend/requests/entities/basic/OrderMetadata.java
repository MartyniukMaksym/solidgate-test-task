package backend.requests.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import static globalUtils.ReadFromJsonFile.readValuesFromJson;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderMetadata {

    private String couponCode;
    private String partnerId;

    public static OrderMetadata.OrderMetadataBuilder builderWithDefaults(){

        OrderMetadata orderMetadata;
        orderMetadata = readValuesFromJson("src/main/resources/testJsonsData/defaultPaymentPageValues.json",
                "order.order_metadata", OrderMetadata.class);
        return OrderMetadata.builder()
                .couponCode(orderMetadata.getCouponCode())
                .partnerId(orderMetadata.getPartnerId());
    }
}
