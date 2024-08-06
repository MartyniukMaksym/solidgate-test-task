package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Card {

    private String bank;
    private String bin;
    private String brand;
    private String cardExpMonth;
    private Long cardExpYear;
    private String cardHolder;
    private String cardId;
    private CardToken cardToken;
    private String cardType;
    private String country;
    private String number;
}
