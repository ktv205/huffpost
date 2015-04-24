package com.example.krishnateja.huffpostcodingchallenge.models;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class AppPreferences {
    public static final class ServerConstants {
        public static final String SCHEME = "http";
        public static final String AUTHORITY = "52.4.108.84";
        public static final String PATH = "huffpost";
        public static final String FILE="huffpost.php";
        public static final String METHOD = "GET";
        public static final String GETVARIABLE = "tweet";
    }
    public static final class IntentExtras{
        public static final String EXTRA_TWEETS="EXTRA_TWEETS";
        public static final String EXTRA_LINKS="EXTRA_LINKS";
    }
}
