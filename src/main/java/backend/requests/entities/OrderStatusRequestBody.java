package backend.requests.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderStatusRequestBody {

    private String orderId;

    public static OrderStatusRequestBody.OrderStatusRequestBodyBuilder builderWithDefaults() {
      return   OrderStatusRequestBody.builder()
              .orderId("defaultOrderId");
    }
}
