package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChargebackIdValue {

    private Long amount;
    private List<ChargebackFlow> chargebackFlow;
    private String currency;
    private String disputeDate;
    private Long id;
    private String reasonCode;
    private String reasonDescription;
    private String reasonGroup;
    private String settlementDate;
    private String status;
    private String type;
}
