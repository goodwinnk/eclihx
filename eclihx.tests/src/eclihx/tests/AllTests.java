package eclihx.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	eclihx.core.haxe.model.AllTests.class,
	eclihx.tests.core.AllTests.class,
	eclihx.tests.core.haxe.AllTests.class })
public class AllTests {
}
