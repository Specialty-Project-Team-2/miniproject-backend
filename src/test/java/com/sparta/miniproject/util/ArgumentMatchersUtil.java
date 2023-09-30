package com.sparta.miniproject.util;

import static org.mockito.ArgumentMatchers.matches;

public class ArgumentMatchersUtil {

    public static String except(String word) {
        return matches(String.format("^(?!%s$).*", word));
    }
}
