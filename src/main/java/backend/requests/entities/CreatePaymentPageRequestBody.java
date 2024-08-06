package backend.requests.entities;

import backend.requests.entities.basic.Order;
import backend.requests.entities.basic.PageCustomization;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreatePaymentPageRequestBody {

    private Order order;
    private PageCustomization pageCustomization;

    @Step
    public static CreatePaymentPageRequestBody setDefaultValuesFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        CreatePaymentPageRequestBody defaultJson;

        try {
            JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/testJsonsData/defaultPaymentPageValues.json"));

            defaultJson = objectMapper.treeToValue(rootNode, CreatePaymentPageRequestBody.class);

        } catch (IOException e) {
            throw new RuntimeException("Error unable to red json from file.", e);
        }
        return defaultJson;
    }
}
