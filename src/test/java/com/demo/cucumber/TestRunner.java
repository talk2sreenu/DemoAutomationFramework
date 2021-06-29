package com.demo.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/Feature"
        ,glue={"src/main/stepDefinition"}
        )
public class TestRunner {

}
