package eclihx.tests.core.haxe;

import org.junit.Test;

import eclihx.core.CorePreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.haxe.HaxeCompilerExecution;
import eclihx.core.haxe.HaxeCompilerExecution.UnknownVersionException;

public class HaxeCompilerExecutionTest {

	@Test
	public void testGetVersion() throws UnknownVersionException {
		HaxeCompilerExecution.getVersion(EclihxCore.getDefault().getPluginPreferences().getString(
				CorePreferenceInitializer.HAXE_COMPILER_PATH));
	}
}
