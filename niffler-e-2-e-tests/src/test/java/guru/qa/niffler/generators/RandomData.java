package guru.qa.niffler.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static String getUserName() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String getPassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getCategory() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
