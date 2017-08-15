package com.twitter.utils

class StringUtils {
    static String getRandomString(int length = 15) {
        int bound = 122 - 97; //char values from 'a' to 'z'
        Random random = new Random()
        StringBuilder builder = new StringBuilder()
        for (int i = 0; i < length; i++) {
            builder.append(Character.toChars(random.nextInt(bound) + 97))
        }
        builder.toString()
    }
}
