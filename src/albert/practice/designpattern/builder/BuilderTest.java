package albert.practice.designpattern.builder;

import albert.practice.designpattern.builder.User.UserBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuilderTest {
    public static void main(String[] args) {
        User albert = new UserBuilder()
                .lastName("Kuo")
                .firstName("Albert")
                .phone("1234567890")
                .age(30)
                .email("test@gmail.com")
                .build();
        log.debug(" user1 = " + albert.toString());

        User mandy = new UserBuilder()
                .lastName("Yeh")
                .firstName("Mandy")
                .email("yeh@gmail.com")
                .build();
        log.debug(" user2 = " + mandy.toString());
    }
}
