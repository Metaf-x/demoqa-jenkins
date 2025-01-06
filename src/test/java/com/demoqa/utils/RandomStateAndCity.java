package com.demoqa.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RandomStateAndCity {
    private static final Map<String, String[]> STATE_TO_CITIES_MAP = new HashMap<>();

    static {
        initializeStateToCitiesMap();
    }

    private final Faker faker;

    public RandomStateAndCity() {
        this.faker = new Faker();
    }

    private static void initializeStateToCitiesMap() {
        STATE_TO_CITIES_MAP.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        STATE_TO_CITIES_MAP.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        STATE_TO_CITIES_MAP.put("Haryana", new String[]{"Karnal", "Panipat"});
        STATE_TO_CITIES_MAP.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    private String[] getStatesAsArray() {
        Set<String> keySet = STATE_TO_CITIES_MAP.keySet();
        return keySet.toArray(new String[0]);
    }

    public String getRandomState() {
        return faker.options().option(getStatesAsArray());
    }

    public String getRandomCity(String state) {
        String[] cities = STATE_TO_CITIES_MAP.get(state);
        return faker.options().option(cities);
    }
}
