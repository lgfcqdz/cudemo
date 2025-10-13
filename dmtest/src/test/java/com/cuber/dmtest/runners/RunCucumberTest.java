package com.cuber.dmtest.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.cuber.dmtest.stepdefinitions",
                "com.cuber.dmtest.hooks",
                "com.cuber.dmtest.config"  // 确保包含配置类所在的包
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class RunCucumberTest {
}
