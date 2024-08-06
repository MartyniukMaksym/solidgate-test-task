package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChargebackFlow {

    private Long amount;
    private String arnCode;
    private String currency;
    private String date;
    private String deadlineDate;
    private Long disputeAmount;
    private Long id;
    private String settlementDate;
    private String status;
    private String type;
    private String updatedDate;
}
