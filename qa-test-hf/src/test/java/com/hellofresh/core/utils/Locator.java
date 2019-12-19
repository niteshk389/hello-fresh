package com.hellofresh.core.utils;

import org.openqa.selenium.WebElement;

public class Locator {

    private WebElement element;

    private String name;

    private String page;

    public static class Builder {

        private WebElement element; //This is important, so we'll pass it to the constructor.

        private String name;

        private String page;


        public Builder(WebElement element) {

            this.element = element;

        }

        public Builder withName(String name){

            this.name = name;

            return this;  //By returning the builder each time, we can create a fluent interface.

        }

        public Builder inPage(String page){

            this.page = page;

            return this;

        }

        public Locator build(){

            Locator locator = new Locator();  //Since the builder is in the Locator class, we can invoke its private constructor.

            locator.element = this.element;

            locator.name = this.name;

            locator.page = this.page;

            return locator;

        }

    }

    //Fields omitted for brevity.

    private Locator() {

        //Constructor is now private.

    }

    //Getters and setters omitted for brevity.

}