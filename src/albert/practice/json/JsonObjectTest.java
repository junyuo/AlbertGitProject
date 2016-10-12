package albert.practice.json;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonObjectTest {

    public static void main(String[] args) {
        String errors[] = new String[]{"�q�l�l��a�} ����O�ťզr��", "�b��W�� ����O�ťզr��", "�W�r ����O�ťզr��",
                "�m�� ����O�ťզr��"};

        JsonObjectTest test = new JsonObjectTest();
        String errorJson = test.convertToJSON(errors);

        log.debug("errorJson = " + errorJson);

        test.readJson(errorJson);
    }

    public void readJson(String jsonStr) {
        JSONObject root = new JSONObject(jsonStr);
        JSONArray array = root.getJSONArray("errors");
        for (int i = 0; i < array.length(); i++) {
            String error = array.get(i).toString();
            log.debug("error = " + error);
        }
    }

    public String convertToJSON(String[] errors) {
        JSONObject error = new JSONObject();
        error.put("errors", errors);
        return error.toString();
    }

}
