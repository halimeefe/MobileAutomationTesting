package StepDefinitions;

import Utilities.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static Utilities.DriverManager.getAppiumDriver;

public class Hooks {

    @Before
    public void before(){
        System.out.println("Scenario started");

    }

    @After
    public void after(){
        System.out.println("Scenario finished");
        DriverManager.closeApplication();


    }
}
