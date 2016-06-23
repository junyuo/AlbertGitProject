package albert.practice.lambda;

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
	return dummyData.stream()
		.filter(person -> person.getAge() >= 25 && Gender.MALE.equals(person.getGender()))
		.collect(Collectors.toList());
    }

    private Person findByLambdaEx2(List<Person> dummyData) {
	Optional<Person> p = dummyData.stream()
		.filter(person -> person.getAge() >= 25).findFirst();
	return p.isPresent() ? p.get() : null;
    }

    private List<Person> createDummyData() {
	Person albert = new Person("Albert", Gender.MALE, 25, "albert@xxx.com");
	Person mandy = new Person("Mandy", Gender.FEMALE, 23, "mandy@xxx.com");
	Person curry = new Person("Curry", Gender.MALE, 28, "curry@xxx.com");
	Person ben = new Person("Ben", Gender.MALE, 21, "ben@xxx.com");

	return Lists.newArrayList(albert, mandy, curry, ben);
    }

}
