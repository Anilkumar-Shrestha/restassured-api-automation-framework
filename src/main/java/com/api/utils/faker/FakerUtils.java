package com.api.utils.faker;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakerUtils {

    private static final Faker faker = new Faker();

    public static int generateNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String generateFirstname() {
        return faker.name().firstName();
    }

    public static String generateLastname() {
        return faker.name().lastName();
    }


}
