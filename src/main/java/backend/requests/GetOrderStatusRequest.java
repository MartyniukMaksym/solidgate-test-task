package backend.requests;

import backend.SendBackendRequest;
import backend.requests.entities.OrderStatusRequestBody;
import backend.responses.entities.OrderStatusResponse;
import globalEntities.Environment;
import globalUtils.SignatureGenerator;
import io.qameta.allure.Step;

public class GetOrderStatusRequest {
    private final Environment environment;

    public GetOrderStatusRequest(Environment env) {
        this.environment = env;
    }

    /**
     * Method will send request to check order status and return response
     *
     * @param requestBody OrderStatusRequestBody
     * @return OrderStatusResponse
     */
    @Step
    public OrderStatusResponse getOrderStatus(OrderStatusRequestBody requestBody) {

        var signature = SignatureGenerator.generateSignature(environment.getPUBLIC_KEY(), requestBody, environment.getSECRET_KEY());

        return SendBackendRequest.builder()
                .url(environment.getORDER_STATUS_URL())
                .requestBody(requestBody)
                .merchant(environment.getPUBLIC_KEY())
                .signature(signature)
                .build()
                .send()
                .extract().body().as(OrderStatusResponse.class);
    }
}