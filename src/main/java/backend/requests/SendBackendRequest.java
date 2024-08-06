package backend.requests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.java.Log;

import static io.restassured.http.Method.POST;

@AllArgsConstructor
@Builder
@Log
public class SendBackendRequest {

    private String url;
    private Object requestBody;
    private String merchant;
    private String signature;

    @Step
    public ValidatableResponse send() {
        log.info("Sending request to URL: '" + url + "' with body: '" + requestBody.toString() + "");
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(url)
                .header("merchant", merchant)
                .header("signature", signature)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().log().all()
                .request(POST)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
