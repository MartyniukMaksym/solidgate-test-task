package backend.requests;

import backend.SendBackendRequest;
import backend.requests.entities.CreatePaymentPageRequestBody;
import backend.responses.entities.CreatePaymentPageResponse;
import globalEntities.Environment;
import globalUtils.SignatureGenerator;
import io.qameta.allure.Step;

public class CreatePaymentPageRequest {

    private final Environment environment;

    public CreatePaymentPageRequest(Environment env) {
        this.environment = env;
    }

    /**
     * Method will send request to create payment page and return response
     *
     * @param requestBody CreatePaymentPageRequestBody
     * @return CreatePaymentPageResponse
     */
    @Step
    public CreatePaymentPageResponse createPaymentPage(CreatePaymentPageRequestBody requestBody) {

        var signature = SignatureGenerator.generateSignature(environment.getPUBLIC_KEY(), requestBody, environment.getSECRET_KEY());

        return SendBackendRequest.builder()
                .url(environment.getPAYMENT_PAGE_URL())
                .requestBody(requestBody)
                .merchant(environment.getPUBLIC_KEY())
                .signature(signature)
                .build()
                .send()
                .extract().body().as(CreatePaymentPageResponse.class);
    }
}
