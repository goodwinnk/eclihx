package eclihx.core.haxe;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.util.OSUtil;
import eclihx.core.util.ProcessUtil;

// TODO 9 Finish 
public class HaxeLauncher {
	public void run(
		HaxeConfiguration configuration, 
		ILaunch launch,
		String compilerPath,
		File outputDirectory) throws CoreException {

		String commandLine;
		try {		
						
			commandLine = 
				OSUtil.quoteCompoundPath(compilerPath) + " " + 
				configuration.printConfiguration();
	
			Process systemProcess = DebugPlugin.exec(
				DebugPlugin.parseArguments(commandLine), outputDirectory);
			
			//DebugPlugin.newProcess(launch, systemProcess, "HaxeProcess");
			ProcessUtil.executeProcess(
					commandLine, outputDirectory, new StringBuilder(), new StringBuilder());
			
			//[F:/Programs/runtime-EclipseApplication/Test/src/Test.hx:9: characters 4-5 : Unexpected }]
						
		} catch (InvalidConfigurationException e) {
			throw new CoreException(
				new Status(
					Status.ERROR, "eclihx.core", 
					"Invalid configuration for launch: " + e.getMessage()));
		}
	}
}
