package albert.practice.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

//http://www.studytrails.com/java/json/java-org-json.jsp
@Slf4j
public class JsonObjectTest {

    public static void main(String[] args) {
        String errors[] = new String[]{"電子郵件地址 不能是空白字元", "帳戶名稱 不能是空白字元", "名字 不能是空白字元",
                "姓氏 不能是空白字元"};

        JsonObjectTest test = new JsonObjectTest();
        String errorJson = test.convertToJSON(errors);

        log.debug("errorJson = " + errorJson);

        test.readJson(errorJson);

        String userJSON = test.buildUserJson();
        log.debug("user JSON = " + userJSON);

        List<User> users = test.parseUserJson(userJSON);
        users.forEach(u -> log.debug(u.toString()));
    }

    public String buildUserJson() {
        List<User> users = getUsers();
        JSONObject dataset = new JSONObject();
        users.stream().forEach(u -> addToDataset(u, dataset));

        return dataset.toString();
    }

    private static void addToDataset(User user, JSONObject dataset) {
        JSONObject userObj = new JSONObject();
        userObj.put("id", user.getId());
        userObj.put("name", user.getName());

        // use the accumulate function to add to an existing value. The value
        // will now be converted to a list
        dataset.accumulate("users", userObj);
    }

    public List<User> parseUserJson(String json) {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray userArray = (JSONArray) jsonObj.get("users");

        List<User> users = new ArrayList<>();

        userArray.forEach(u -> addToList(u, users));

        return users;
    }

    private void addToList(Object user, List<User> users) {
        JSONObject userObj = (JSONObject) user;
        Integer id = (Integer) userObj.get("id");
        String name = (String) userObj.get("name");
        users.add(new User(id, name));
    }

    public String convertToJSON(String[] errors) {
        JSONObject error = new JSONObject();
        error.put("errors", errors);
        return error.toString();
    }

    public void readJson(String jsonStr) {
        JSONObject root = new JSONObject(jsonStr);
        JSONArray array = root.getJSONArray("errors");
        for (int i = 0; i < array.length(); i++) {
            String error = array.get(i).toString();
            log.debug("error = " + error);
        }
    }

    public List<User> getUsers() {
        User albert = new User(1, "Albert");
        User mandy = new User(2, "Mandy");
        return Arrays.asList(albert, mandy);
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class User {
        private Integer id;
        private String name;
    }

}
