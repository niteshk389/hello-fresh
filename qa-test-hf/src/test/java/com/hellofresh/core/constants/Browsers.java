package com.hellofresh.core.constants;

/**
 * Enum containing browser names
 */
public enum Browsers {
    CHROME_BROWSER("chrome"), FIREFOX_BROWSER("firefox"), SAFARI_BROWSER("safari");

    private String name;
    Browsers(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static Browsers fromString(String browser) {
        for (Browsers b : Browsers.values()) {
            if (b.getName().equalsIgnoreCase(browser)) {
                return b;
            }
        }
        return null;
    }
}
