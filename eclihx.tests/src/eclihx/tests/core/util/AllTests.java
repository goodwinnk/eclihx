package eclihx.tests.core.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	eclihx.tests.core.util.console.parser.core.AllTests.class,
	
	TokenReplacingReaderTest.class,
} )
public class AllTests {

}
