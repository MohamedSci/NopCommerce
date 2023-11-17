package Runner;

import io.cucumber.testng.*;
import tests.*;

@CucumberOptions(features="src/test/java/features"
        ,glue= {"Steps"}
        ,plugin= {"pretty","html:target/cucumber-html-report"})
public class BDDRunner extends TestBase
{


}