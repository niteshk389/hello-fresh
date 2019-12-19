package com.hellofresh.core.constants;

public enum Browsers {
    CHROME_BROWSER("chrome"), FIREFOX_BROWSER("firefox"), SAFARI_BROWSER("safari");

    private String name;
    Browsers(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
