package demo2b.MyRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="D:\\Bivas\\SelFrame\\Features\\JenkinsLogin.feature",
		glue={"JenkinsStepDefinition"},  
		
        format= {"pretty","html:test-output","json:json_output/cucumber.json",
        "junit:junit_xml/cucumber.xml"},
        monochrome=true,
        strict=true,
        dryRun=false
		)
public class RunnerJenkins {

}
