package ru.stqa.pft.addressbook.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:bdd",
        plugin = {"pretty", "html:build/bdd-report/cucumber-report.html"})
public class GroupTests extends AbstractTestNGCucumberTests {
}
