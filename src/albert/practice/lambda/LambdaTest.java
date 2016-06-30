package albert.practice.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaTest {

    public static void main(String[] args) {
	LambdaTest test = new LambdaTest();
	List<Person> dummyData = test.createDummyData();

	List<Person> result1 = test.findByApacheCommonsEx1(dummyData);
	List<Person> result2 = test.findByLambdaEx1(dummyData);
	log.debug("result1 = " + result1.toString());
	log.debug("result2 = " + result2.toString());

	log.debug(test.findByApacheCommonsEx2(dummyData).toString());
	log.debug(test.findByLambdaEx2(dummyData).toString());

	log.debug("[before sort]");
	test.printCollection(dummyData);
	dummyData.sort((Person p1, Person p2) -> p1.getAge() - p2.getAge());
	log.debug("[sort by age]");
	test.printCollection(dummyData);

	test.printOdds();

	String names = dummyData.stream().map(p -> p.getName()).collect(Collectors.joining(", "));
	log.debug("names = " + names);

	Double averageAge = dummyData.stream().filter(p -> p.getGender().equals(Gender.MALE)).mapToInt(p -> p.getAge())
		.average().getAsDouble();
	log.debug("averageAge = " + averageAge);

	long numOfMale = dummyData.stream().filter(p -> p.getGender().equals(Gender.MALE)).count();
	log.debug("numOfMale = " + numOfMale);
	
	
	test.separatePlayersWithComma();
	test.separatePlayersWithComma_usingJDK8();
    }

    @SuppressWarnings("unchecked")
    private List<Person> findByApacheCommonsEx1(List<Person> dummyData) {
	return (List<Person>) CollectionUtils.select(dummyData, new Predicate() {
	    @Override
	    public boolean evaluate(Object object) {
		Person person = (Person) object;
		Boolean condition = person.getAge() >= 25 && Gender.MALE.equals(person.getGender());
		return condition;
	    }
	});
    }

    @SuppressWarnings("unchecked")
    private Person findByApacheCommonsEx2(List<Person> dummyData) {
	List<Person> result = (List<Person>) CollectionUtils.select(dummyData, new Predicate() {
	    @Override
	    public boolean evaluate(Object object) {
		Person person = (Person) object;
		return person.getAge() >= 25;
	    }
	});

	Person person = null;
	if (!result.isEmpty()) {
	    person = result.get(0);
	}
	return person;
    }

    private List<Person> findByLambdaEx1(List<Person> dummyData) {
	return dummyData.stream().filter(person -> person.getAge() >= 25 && Gender.MALE.equals(person.getGender()))
		.collect(Collectors.toList());
    }

    private Person findByLambdaEx2(List<Person> dummyData) {
	Optional<Person> p = dummyData.stream().filter(person -> person.getAge() >= 25).findFirst();
	return p.isPresent() ? p.get() : null;
    }

    private void printCollection(List<Person> dummyData) {
	dummyData.forEach(person -> {
	    log.debug("person = {}", person);
	});
    }

    private List<Person> createDummyData() {
	Person albert = new Person("Albert", Gender.MALE, 25, "albert@xxx.com");
	Person mandy = new Person("Mandy", Gender.FEMALE, 23, "mandy@xxx.com");
	Person curry = new Person("Curry", Gender.MALE, 28, "curry@xxx.com");
	Person ben = new Person("Ben", Gender.MALE, 21, "ben@xxx.com");

	return Lists.newArrayList(albert, mandy, curry, ben);
    }

    private void printOdds() {
	List<Integer> numbers = new ArrayList<Integer>();
	for (int i = 0; i <= 10; i++) {
	    numbers.add(i);
	}
	List<Integer> oddNumbers = numbers.stream().filter(number -> number % 2 != 0).collect(Collectors.toList());
	log.debug("OddNumbers = " + oddNumbers.toString());
    }

    private List<String> getDummyPlayers() {
	return Arrays.asList("¤ý ¬f ¿Ä", "ªL ´¼ ³Ó", "ªL ªl ¨|", "½± ´¼ ½å", "°ª °ê ¼y");
    }

    private void separatePlayersWithComma(){
	List<String> players =getDummyPlayers();
	String playersWithComma = "";
	for (int i = 0; i < players.size(); i++) {
	    if (i == players.size() - 1) {
		playersWithComma = playersWithComma + players.get(i);
	    } else {
		playersWithComma = playersWithComma + players.get(i) +", "; 
	    }
	}
	log.debug("playersWithComma = " + playersWithComma);
    }
    
    private void separatePlayersWithComma_usingJDK8(){
	List<String> players =getDummyPlayers();
	String playersWithComma = players.stream().collect(Collectors.joining(", "));
	log.debug("playersWithComma = " + playersWithComma);
    }

}
