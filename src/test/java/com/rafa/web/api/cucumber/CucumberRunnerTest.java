package com.rafa.web.api.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = {"com.rafa.web.api.cucumber.config", "com.rafa.web.api.cucumber.steps"})
public class CucumberRunnerTest {
}
