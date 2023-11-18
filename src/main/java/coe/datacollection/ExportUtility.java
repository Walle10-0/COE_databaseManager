package coe.datacollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class ExportUtility {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String exportToJSON(List<?> dataList) throws Exception {
        return objectMapper.writeValueAsString(dataList);
    }

}
