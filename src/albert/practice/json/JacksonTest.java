package albert.practice.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

//http://www.mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/
//https://stackoverflow.com/questions/28821715/java-lang-classcastexception-java-util-linkedhashmap-cannot-be-cast-to-com-test
@Slf4j
public class JacksonTest {

    public static void main(String[] args) {
	JacksonTest test = new JacksonTest();
	String jsonString = test.convertOjbectToJson();

	List<User> users = test.convertJsonToObject(jsonString);
	for (User user : users) {
	    log.debug("user = " + user.toString());
	}
    }

    public List<User> convertJsonToObject(String jsonString) {
	ObjectMapper mapper = new ObjectMapper();
	List<User> users = new ArrayList<User>();
	try {
	    users = mapper.readValue(jsonString, new TypeReference<List<User>>() {
	    });
	} catch (JsonParseException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return users;
    }

    public String convertOjbectToJson() {
	List<User> users = createUsers();
	String jsonString = "";

	ObjectMapper mapper = new ObjectMapper();

	try {
	    jsonString = mapper.writeValueAsString(users);
	} catch (JsonGenerationException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	log.debug("jsonString = " + jsonString);
	return jsonString;
    }

    private List<User> createUsers() {
	User albert = new User();
	albert.setId("1");
	albert.setLogin("albert");
	albert.setMail("albert@gmail.com");

	User mandy = new User();
	mandy.setId("2");
	mandy.setLogin("mandy");
	mandy.setMail("mandy@gmail.com");

	User verio = new User();
	verio.setId("3");
	verio.setLogin("verio");
	verio.setMail("verio@gmail.com");

	List<User> users = new ArrayList<User>();
	users.add(albert);
	users.add(mandy);
	users.add(verio);

	return users;
    }

}
