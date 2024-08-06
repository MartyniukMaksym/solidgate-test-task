package backend.responses.entities.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Chargebacks {

    @JsonProperty("<chargeback_id_value_#1>")
    private ChargebackIdValue chargebackIdValue;
}
