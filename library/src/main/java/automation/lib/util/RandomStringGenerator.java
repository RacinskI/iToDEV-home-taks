package automation.lib.util;

import com.github.javafaker.Faker;

public class RandomStringGenerator {

    private static final Faker faker = new Faker();

    public static String randomName() {
        return faker.animal().name();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    private RandomStringGenerator() {
    }
}
