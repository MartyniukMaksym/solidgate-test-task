package backend.requests.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageCustomization {

    private String backUrl;
    private String buttonColor;
    private String buttonFontColor;
    private String fontName;
    private Boolean isCardholderVisible;
    private String orderDescription;
    private String orderTitle;
    private List<String> paymentMethods;
    private String publicName;
    private String termsUrl;
}
