package eclihx.tests.core;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;

import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.util.console.parser.core.ParseError;

public class HaxeLauncherTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testRun() throws InvalidConfigurationException, CoreException, ParseError {
		fail("Not finished yet");
		
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
