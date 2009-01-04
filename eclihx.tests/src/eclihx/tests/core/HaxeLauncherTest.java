package eclihx.tests.core;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Ignore;

import eclihx.core.haxe.HaxeLauncher;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.util.console.parser.core.ParseError;

/**
 * Test for {@link HaxeLauncher}
 */
public class HaxeLauncherTest {
	
	@Before
	protected void setUp() throws Exception {
	}

	/**
	 * Test for {@link HaxeLauncher#run(eclihx.core.haxe.internal.configuration.HaxeConfiguration, org.eclipse.debug.core.ILaunch, String, java.io.File)} 
	 * @throws InvalidConfigurationException error in configuration.
	 * @throws CoreException common exception.
	 * @throws ParseError error in parsing.
	 */
	@Ignore
	public void testRun() throws InvalidConfigurationException, CoreException, ParseError {
		//TODO 7 finish this test
		
		/*
		HaxeLauncher launcher = new HaxeLauncher();
		BuildParamParser parser = new BuildParamParser();
		
		launcher.run(
			parser.parseFile("test.hxml").getMainConfiguration(), 
			null, 
			"haxe.exe",
			new File("src")			
		);		*/
	}

}
