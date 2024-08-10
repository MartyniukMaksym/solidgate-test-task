package globalUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadFromJsonFile {

    /**
     * Method will read data from the json file
     *
     * @param filePath     Path to json file
     * @param jsonTreePath Path inside json
     * @param clazz        Class for Object mapping
     * @return mapped Object
     */
    public static <T> T readValuesFromJson(String filePath, String jsonTreePath, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T result;

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            String[] pathSegments = jsonTreePath.split("\\.");
            JsonNode currentNode = rootNode;
            for (String segment : pathSegments) {
                currentNode = currentNode.path(segment);
            }
            result = objectMapper.treeToValue(currentNode, clazz);

        } catch (IOException e) {
            throw new RuntimeException("Error unable to read JSON from file.", e);
        }

        return result;
    }
}
