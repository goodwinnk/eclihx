package eclihx.core.haxe;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;

public class HaxeConfigurationManager {

	static public HaxeConfiguration MakeNoOutput(HaxeConfiguration configuration) {
		try {
			configuration.setPlatform(HaxeConfiguration.Platform.NoOutput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return configuration;
	}
}
