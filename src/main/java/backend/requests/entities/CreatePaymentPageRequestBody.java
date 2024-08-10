package backend.requests.entities;

import backend.requests.entities.basic.Order;
import backend.requests.entities.basic.PageCustomization;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreatePaymentPageRequestBody {

    private Order order;
    private PageCustomization pageCustomization;

    public static CreatePaymentPageRequestBody.CreatePaymentPageRequestBodyBuilder builderWithDefaults() {
        return CreatePaymentPageRequestBody.builder()
                .pageCustomization(PageCustomization.builderWithDefaults().build())
                .order(Order.builderWithDefaults().build());
    }
}
