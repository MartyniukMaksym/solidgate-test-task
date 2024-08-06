package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillingDetails {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zip;
}
