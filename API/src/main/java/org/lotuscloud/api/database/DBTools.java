package org.lotuscloud.api.database;

import java.util.regex.Pattern;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class DBTools {

    public static Pattern noCase(String value) {
        return Pattern.compile(value, Pattern.CASE_INSENSITIVE);
    }
}