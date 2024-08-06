package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Transaction {
    private String id;
    private String operation;
    private String status;
    private String descriptor;
    private int amount;
    private String currency;
    private String created_at;
    private String updated_at;
    private int marketing_amount;
    private String marketing_currency;
    private Card card;
    private CardToken card_token;
}
