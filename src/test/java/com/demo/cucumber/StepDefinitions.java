package com.demo.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	@Given("application invoked")
	public void application_invoked() {
	    System.out.println("This is from Given Block");
	}
	@When("Application is running")
	public void application_is_running() {
		System.out.println("This is from When Block");
	}
	@Then("validate the output")
	public void validate_the_output() {
		System.out.println("This is from Then Block");
	}
}
