package eclihx.core.haxe.model;

/**
 * Haxe file content singleton
 */

public final class HaxeFileContent {
	private static HaxeFileContent _instance = null;

	private HaxeFileContent() {	}

	public static synchronized HaxeFileContent getInstance() {
		if (_instance == null)
			_instance = new HaxeFileContent();
		return _instance;
	}

	/**
	 * "package ;" string flag
	 */
	
	public boolean isDefPack;
	
	/**
	 * Add content to file except package declaration
	 * @param content package declaration
	 * @param fileName file name
	 * @return file content
	 */

	public String createHaxeContent(String content, String fileName) {

		if (isDefPack)
			content += String.format("package ;\n\n");

		content += String.format("class %s {\n\n}", HaxeSourceFile
				.getClassName(fileName));

		return content;
	}
	
}
