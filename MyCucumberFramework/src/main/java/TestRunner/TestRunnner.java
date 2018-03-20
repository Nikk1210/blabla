package TestRunner;

import org.junit.runner.RunWith;

import com.google.common.reflect.ClassPath;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
				(	
				format="pretty",		
				features="Features/",
				glue="stepDefinition/"
//				tags="functional"
				)

public class TestRunnner {

}
