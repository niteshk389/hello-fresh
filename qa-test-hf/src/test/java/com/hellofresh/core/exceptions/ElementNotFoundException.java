package com.hellofresh.core.exceptions;

import com.hellofresh.core.utils.Locator;

public class ElementNotFoundException extends RuntimeException {
    private Locator locator;

    public ElementNotFoundException(Locator locator, Throwable cause) {
        super(cause);
        this.locator = locator;
    }
    @Override
    public String getMessage() {
        return locator + " does not contain the element \"" + locator + "\" identified by locator: " + locator;
    }
}
