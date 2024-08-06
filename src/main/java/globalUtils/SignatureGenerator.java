package globalUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import io.qameta.allure.Step;
import lombok.extern.java.Log;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Log
public class SignatureGenerator {

    /**
     * Method will generate signature for the API calls
     *
     * @param publicKey  user public key
     * @param jsonObject request object
     * @param secretKey  user secret key
     * @return signature
     */
    @Step
    public static String generateSignature(String publicKey, Object jsonObject, String secretKey) {

        log.info("generating request signature.");
        var jsonString = convertObjectToString(jsonObject);
        String text = publicKey + jsonString + publicKey;
        byte[] hashedBytes = Hashing.hmacSha512(secretKey.getBytes())
                .hashString(text, StandardCharsets.UTF_8).toString().getBytes();
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    /**
     * Method will convert Object to String
     *
     * @param object provided Object
     * @return provided Object converted to String
     */
    private static String convertObjectToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON string.", e);
        }
    }
}
