package eclihx.core.haxe;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;

public class HaxeConfigurationManager {
	
	
	static public void MakeNoOutput(HaxeConfiguration configuration) {
		configuration.setExplicitNoOutput();
	}
	
	static public void MakeTips(
		HaxeConfiguration configuration, String fileName, int position) {
		
		configuration.setExplicitNoOutput();
		configuration.enableTips(fileName, position);
	}
}
