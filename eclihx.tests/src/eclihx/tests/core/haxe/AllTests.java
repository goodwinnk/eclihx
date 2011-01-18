package eclihx.tests.core.haxe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	eclihx.tests.core.haxe.internal.AllTests.class,
	HaxeCompilerExecutionTest.class })
public class AllTests {
}
