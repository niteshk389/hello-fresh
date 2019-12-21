package com.hellofresh.core.testdata;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Test Data generator class to generate random values to be used as test data
 */
public class TestDataGenerator {

    public static final int RANDOM_STR_LENGTH = 5;

    /**
     * Test Data generator method to generate random emails to be used as test data
     * @return random email
     */
    public static String randomAlphabeticForMail() {
        String randomStr = "hf_challenge_" + RandomStringUtils.randomAlphanumeric(RANDOM_STR_LENGTH) + "@hf" + RandomStringUtils.randomAlphanumeric(RANDOM_STR_LENGTH) +".com";
        return randomStr;
    }

}
