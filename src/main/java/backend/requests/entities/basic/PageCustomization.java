package backend.requests.entities.basic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

import static globalUtils.ReadFromJsonFile.readValuesFromJson;

@Setter
@Getter
@Builder
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

    public static PageCustomization.PageCustomizationBuilder builderWithDefaults() {

        PageCustomization pageCustomization;
        pageCustomization = readValuesFromJson("src/main/resources/testJsonsData/defaultPaymentPageValues.json",
                "page_customization", PageCustomization.class);

        return PageCustomization.builder()
                .backUrl(pageCustomization.getBackUrl())
                .buttonColor(pageCustomization.getButtonColor())
                .buttonFontColor(pageCustomization.getButtonFontColor())
                .fontName(pageCustomization.getFontName())
                .isCardholderVisible(pageCustomization.getIsCardholderVisible())
                .orderDescription(pageCustomization.getOrderDescription())
                .orderTitle(pageCustomization.getOrderTitle())
                .paymentMethods(pageCustomization.getPaymentMethods())
                .publicName(pageCustomization.getPublicName())
                .termsUrl(pageCustomization.getTermsUrl());
    }
}
