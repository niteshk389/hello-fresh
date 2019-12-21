package com.hellofresh.utils;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "resources/features"
        ,glue={"com/hellofresh/steps"}
)

public class TestRunner {

}
