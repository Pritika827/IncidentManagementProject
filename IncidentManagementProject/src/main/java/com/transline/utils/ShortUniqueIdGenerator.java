package com.transline.utils;

import java.util.Random;

public class ShortUniqueIdGenerator {
    private static final Random RANDOM = new Random();

    public static String generateShortUniqueId() {
        long currentTime = System.currentTimeMillis(); // Use current time in milliseconds
        int randomInt = RANDOM.nextInt(1000); // Generate a random integer to add variability
        return String.format("%d-%03d", currentTime, randomInt); // Format as "timestamp-random"
    }
}
