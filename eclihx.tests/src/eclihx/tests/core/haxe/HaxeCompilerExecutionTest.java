package eclihx.tests.core.haxe;

import org.junit.Assert;
import org.junit.Test;

import eclihx.core.haxe.HaxeCompilerExecution;
import eclihx.core.haxe.HaxeCompilerExecution.UnknownVersionException;
import eclihx.core.haxe.HaxeCompilerResolver;

public class HaxeCompilerExecutionTest {

	@Test
	public void testGetVersion() {
		try {
			HaxeCompilerExecution.getVersion(HaxeCompilerResolver.getDefaultGlobalCompiler());
		} catch (UnknownVersionException e) {
			Assert.fail();
		}
		
	}
}
