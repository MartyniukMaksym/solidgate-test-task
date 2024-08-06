package backend.responses.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CascadeStep {

    private Long cascadeNumber;
    private String mid;
    private String midDescriptor;
    private String routeId;
}
