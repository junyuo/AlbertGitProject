package albert.practice.designpattern.builder;

import java.util.regex.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
// http://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
// The user object does not have any setter method, so it's state can not be changed once it has been
// built. This provides the desired immutability.
public class User {

    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String phone;
    private final String email;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private Integer age;
        private String phone;
        private String email;

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            User user = new User(this);

            // Do some basic validations to check
            if (!isEmailValid(user.getEmail())) {
                throw new RuntimeException(user.getEmail() + " is invalid Email format!");
            }

            return user;
        }

        public Boolean isEmailValid(String email) {
            Boolean isValid = Boolean.FALSE;
            Pattern EMAIL_PATTERN = Pattern.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
            if (EMAIL_PATTERN.matcher(email).matches()) {
                isValid = Boolean.TRUE;
            }
            return isValid;
        }

    }
}
