package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Storage for haXe compiler configuration. This class is used by the parser of
 * haXe compiler parameters.
 */
public final class HaxeConfiguration extends AbstractConfiguration {

	/**
	 * Output platforms.
	 */
	public enum Platform {
		/**
		 * Flash platform.
		 */

		Flash,
		/**
		 * ActionScript platform.
		 */

		ActionScript,

		/**
		 * JavaScript platform.
		 */
		JavaScript,

		/**
		 * Neko platform.
		 */
		Neko,

		/**
		 * PHP platform.
		 */
		PHP,

		/**
		 * No target platform which is equal to no output at all.
		 */
		NoOutput
	}

	/**
	 * Target platform of the compiler.
	 */
	private Platform platform = Platform.NoOutput;

	/**
	 * This flag monitors explicit setting of the target platform and will allow
	 * determine the situation of the multi-platform assignment.
	 */
	private boolean isPlatformExplicitlySet;

	/**
	 * No output for configuration (--no-output). This flag will turn
	 * <code>platform</code> field to NoOutput.
	 */
	private boolean explicitNoOutput;

	// Platform specific configuration storages.
	private final PHPConfiguration phpConfig = new PHPConfiguration();
	private final FlashConfiguration flashConfig = new FlashConfiguration();
	private final ASConfiguration asConfig = new ASConfiguration();
	private final NekoConfiguration nekoConfig = new NekoConfiguration();
	private final JSConfiguration jsConfig = new JSConfiguration();

	// Common haXe configuration options.
	/**
	 * Source directory configuration (-cp).
	 */
	private final LinkedList<String> sourceDirectories = new LinkedList<String>();

	/**
	 * Name of the startup class (-main).
	 */
	private String startupClass;

	/**
	 * haXe libraries (-lib).
	 */
	private final LinkedList<String> libraries = new LinkedList<String>();

	/**
	 * Compilation flags (-D).
	 */
	private final LinkedList<String> compilationFlags = 
		new LinkedList<String>();

	/**
	 * Debug flag (-debug).
	 */
	private boolean debug;

	/**
	 * Flag for displaying code tips (--display).
	 */
	private boolean displayTips;

	/**
	 * The name of the file for the code tip.
	 */
	private String tipFileName;

	/**
	 * Position in the file for code tip.
	 */
	private int tipFilePosition;

	/**
	 * Help flag.
	 */
	private boolean helpMode;

	/**
	 * Verbose mode.
	 */
	private boolean verboseMode;

	/**
	 * Time measure mode state.
	 */
	private boolean timeMesureMode;

	/**
	 * No-inline mode state.
	 */
	private boolean noInlineMode;

	/**
	 * Output file for xml-description.
	 */
	private String outputXmlFile;

	/**
	 * Prompt on error state.
	 */
	private boolean promptOnErrorMode;

	/**
	 * Command which should be done after compilation.
	 */
	private String cmdCommand;

	/**
	 * No traces mode state.
	 */
	private boolean noTracesMode;

	/**
	 * Output file for generated haXe headers from swf file.
	 */
	private String swfFileForHeaders;

	/**
	 * Flash strict mode state.
	 */
	private boolean flashStrictMode;

	/**
	 * "Place objects found on the stage of the SWF lib" mode state.
	 */
	private boolean flashUseStageMode;

	/**
	 * Remap packages directives.
	 */
	private final ArrayList<String> remapString = new ArrayList<String>();

	/**
	 * Resources.
	 */
	private final ArrayList<String> resourceFiles = new ArrayList<String>();

	/**
	 * Collection of the file with the classes excluded from code generation.
	 */
	private final ArrayList<String> excludeFiles = new ArrayList<String>();

	/**
	 * Creates the string representation of the haXe build parameter by the key
	 * and value. Note that both values can't be <code>null</value>.
	 * 
	 * @param key suffix of the parameter.
	 * @param value parameter value
	 * @return string representation of the parameter.
	 */
	public final static String GenerateParameter(String key, String value) {

		if (key == null || value == null) {
			throw new NullPointerException(
					"Parameters of GenerateParameter are not allowed to have " +
					"null values");
		}

		return key + " " + value + " ";
	}

	/**
	 * Return string representation of the configuration flag parameter.
	 * 
	 * @param key suffix of the parameter.
	 * @param exist is parameter exist configuration.
	 * @return string representation of the flag parameter.
	 */
	public final static String GenerateFlagParameter(String key, 
			boolean exist) {

		if (key == null) {
			throw new NullPointerException(
					"Parameters of GenerateParameter are not allowed to have " +
					"null values");
		}

		// Print the key if it should be, print nothing in other case
		return exist ? key + " " : "";
	}

	/**
	 * Default constructor for configuration.
	 */
	public HaxeConfiguration() {
	}

	/**
	 * Get output platform.
	 * 
	 * @return the platform
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Set output platform.
	 * 
	 * @param platform the platform to set. Can't be <code>null</code>.
	 */
	public void setPlatform(Platform platform) {

		if (platform == null) {
			throw new NullPointerException("platform parameter can't be null");
		}

		isPlatformExplicitlySet = true;
		this.platform = platform;
	}

	/**
	 * Enable no-output mode.
	 */
	public void setExplicitNoOutput() {
		explicitNoOutput = true;
	}

	/**
	 * Checks if output is disabled in this configuration.
	 * 
	 * @return state of no-output mode.
	 */
	public boolean isNoOutputMode() {
		return (explicitNoOutput || platform == Platform.NoOutput);
	}

	/**
	 * Get flash configuration.
	 * 
	 * @return Flash target platform options container.
	 */
	public FlashConfiguration getFlashConfig() {
		return flashConfig;
	}

	/**
	 * Get ActionScript configuration.
	 * 
	 * @return the ActionScript configuration container
	 */
	public ASConfiguration getASConfig() {
		return asConfig;
	}

	/**
	 * Get Neko configuration.
	 * 
	 * @return the ActionScript configuration container.
	 */
	public NekoConfiguration getNekoConfig() {
		return nekoConfig;
	}

	/**
	 * Returns the PHP target platform options container.
	 * 
	 * @return the PHP options container.
	 */
	public PHPConfiguration getPHPConfig() {
		return phpConfig;
	}

	/**
	 * Returns the JS target platform options container.
	 * 
	 * @return the JS platform options container.
	 */
	public JSConfiguration getJSConfig() {
		return jsConfig;
	}

	/**
	 * Check if debug mode is enabled.
	 * 
	 * @return state of debug mode.
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * Enables debug mode.
	 */
	public void enableDebug() {
		debug = true;
	}

	/**
	 * Gets the list of haXe libraries.
	 * 
	 * @return the libraries
	 */
	public Collection<String> getLibraries() {
		return libraries;
	}

	/**
	 * Add haXe library to configuration
	 * 
	 * @param library the library to add
	 */
	public void addLibrary(String library) {
		// TODO 6 Add check for validness and uniqueness
		libraries.add(library);
	}

	/**
	 * Add resource file.
	 * 
	 * @param resource Resource file.
	 */
	public void addResource(String resource) {
		// TODO 5 Add validating and parsing.
		resourceFiles.add(resource);
	}

	/**
	 * Add file with the excluded classes
	 * 
	 * @param excludeFile The name of the file with the excluded files.
	 */
	public void addExcludeFile(String excludeFile) {
		excludeFiles.add(excludeFile);
	}

	/**
	 * Checks if configuration has some compilation flag.
	 * 
	 * @param flag Flag to check.
	 * 
	 * @return true if configuration has some compilation flag.
	 */
	public boolean hasCompilationFlags(String flag) {
		return compilationFlags.contains(flag);
	}

	/**
	 * Adds some compilation flag
	 * 
	 * @param compilationFlag the compilationFlag to add
	 */
	public void addCompilationFlag(String compilationFlag) {
		// TODO 6 Add check for validness and uniqueness
		this.compilationFlags.add(compilationFlag);
	}

	/**
	 * Adds new source directory to configuration.
	 * 
	 * @param directory directory to add.
	 */
	public void addSourceDirectory(String directory) {
		sourceDirectories.add(directory);
	}

	/**
	 * Returns collection of source directories.
	 * 
	 * @return collection of source directories.
	 */
	public Collection<String> getSourceDirectories() {
		return sourceDirectories;
	}

	/**
	 * Get the name of the startup class
	 * 
	 * @return the startupClass
	 */
	public String getStartupClass() {
		return startupClass;
	}

	/**
	 * Set the startup class.
	 * 
	 * @param startupClass the name of the startup class.
	 */
	public void setStartupClass(String startupClass) {
		// TODO 6 Check the name of the class
		this.startupClass = startupClass;
	}

	/**
	 * @return the displayTips
	 */
	public boolean isDisplayTips() {
		return displayTips;
	}

	/**
	 * Enables display tips mode.
	 * 
	 * @param fileName Name of the file.
	 * @param position Number of character from the beginning of the file till
	 *        the place where the tips should be get.
	 */
	public void enableTips(String fileName, int position) {
		displayTips = true;

		setExplicitNoOutput();

		tipFileName = fileName;
		tipFilePosition = position;
	}

	/**
	 * Returns help mode status.
	 * 
	 * @return true if help is enabled.
	 */
	public boolean isHelpMode() {
		return helpMode;
	}

	/**
	 * Enables help mode.
	 */
	public void enableHelp() {
		helpMode = true;
	}

	/**
	 * Enables verbose mode.
	 */
	public void enableVerbose() {
		verboseMode = true;
	}

	/**
	 * Enables time measure mode.
	 */
	public void enableTimeMesureMode() {
		timeMesureMode = true;
	}

	/**
	 * Enables no-inline mode.
	 */
	public void enableNoInlineMode() {
		noInlineMode = true;
	}

	/**
	 * Output file for xml-description.
	 * 
	 * @param outputXmlFile the name of the file for xml description.
	 */
	public void setXmlDescriptionFile(String outputXmlFile) {
		this.outputXmlFile = outputXmlFile;
	}

	/**
	 * Output file for xml-description.
	 * 
	 * @return the name of the file for xml description output.
	 */
	public String getXmlDescriptionFile() {
		return outputXmlFile;
	}

	/**
	 * Enables prompt on error mode.
	 */
	public void enablePromptOnErrorMode() {
		promptOnErrorMode = true;
	}

	/**
	 * Disables prompt on error mode.
	 */
	public void disablePromptOnErrorMode() {
		promptOnErrorMode = false;
	}

	/**
	 * Sets command for execution after compilation.
	 * 
	 * @param command to execute
	 */
	public void setCmdCommand(String command) {
		cmdCommand = command;
	}

	/**
	 * Enables no-traces mode.
	 */
	public void enableNoTracesMode() {
		noTracesMode = true;
	}

	/**
	 * Checks if this configuration will not compile traces instructions.
	 * 
	 * @return the state of no-traces mode.
	 */
	public boolean isNoTracesMode() {
		return noTracesMode;
	}

	/**
	 * Sets output file for generated haXe headers from swf file.
	 * 
	 * @param fileForHeader file name.
	 */
	public void setSwfFileForHeaders(String fileForHeader) {
		swfFileForHeaders = fileForHeader;
	}

	/**
	 * Enables flash strict mode.
	 */
	public void enableflashStrictMode() {
		flashStrictMode = true;
	}

	/**
	 * Enables "place objects found on the stage of the SWF lib" mode.
	 */
	public void enableFlashUseStageMode() {
		flashUseStageMode = true;
	}

	/**
	 * Remap packages directives.
	 * 
	 * @param remapDirective remap directive.
	 */
	public void addRemapPackage(String remapDirective) {
		remapString.add(remapDirective);
	}

	/**
	 * Prints the configuration.
	 * 
	 * @throws InvalidConfigurationException Exception with the errors
	 *         descriptions if configuration is invalid.
	 */
	@Override
	public String printConfiguration() throws InvalidConfigurationException {

		validateException();

		StringBuilder outputBuilder = new StringBuilder();

		// Startup class
		outputBuilder
				.append(GenerateParameter(
						HaxePreferencesManager.PARAM_PREFIX_STARTUP_CLASS,
						startupClass));

		// Stored libraries
		for (String library : libraries) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_HAXE_LIB, library));
		}

		// Compilation flags
		for (String compileFlag : compilationFlags) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_COMPILATION_FLAG,
					compileFlag));
		}

		// Print source directories
		for (String sourceDirectory : sourceDirectories) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_SOURCE_DIRECTORY,
					OSUtil.quoteCompoundPath(sourceDirectory)));
		}

		// Debug parameter
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_DEBUG_MODE_FLAG, debug));

		// Display code tips
		if (displayTips) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_CODE_TIPS_FLAG, String
							.format("%s@%s", tipFileName, tipFilePosition)));
		}
		
		// No output
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_NO_OUTPUT_FLAG,
				explicitNoOutput));

		// Verbose mode flag.
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_VERBOSE_MODE_FLAG,
				verboseMode));

		// No-inline flag
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_NO_INLINE_FLAG,
				noInlineMode));

		// Time measure mode
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_TIME_MESURE_FLAG,
				timeMesureMode));

		// Resources
		for (String resourceFile : resourceFiles) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_RESOURCE_FILE,
					resourceFile));
		}

		// Exclude files
		for (String excludeFile : excludeFiles) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_EXCLUDE_FILE,
					excludeFile));
		}

		// Xml description
		if (outputXmlFile != null) {
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_XML_DESCRIPTION_OUTPUT,
					outputXmlFile));
		}

		// Prompt on error.
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_PROMT_ERROR_MODE_FLAG,
				promptOnErrorMode));

		// CMD command
		if (cmdCommand != null) {
			outputBuilder
					.append(GenerateParameter(
							HaxePreferencesManager.PARAM_PREFIX_CMD_COMMAND,
							cmdCommand));
		}

		// No traces mode state
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_NO_TRACES_FLAG,
				noTracesMode));

		// File for generated headers
		if (swfFileForHeaders != null) {
			outputBuilder
					.append(GenerateParameter(
							HaxePreferencesManager.PARAM_PREFIX_GENERATE_HAXE_CLASSES_SWF,
							swfFileForHeaders));
		}

		// Flash strict mode
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_FLASH_STRICT_FLAG,
				flashStrictMode));

		// Flash strict mode
		outputBuilder.append(GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_FLASH_USE_STAGE_FLAG,
				flashUseStageMode));

		// Remap package directives
		for (String directive : remapString) {
			// Flash strict mode
			outputBuilder.append(GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_REMAP_PACKAGE,
					directive));
		}

		
		// Platforms
		if (isPlatformExplicitlySet) {
			outputBuilder.append(getTargetConfiguration().printConfiguration());
		}

		return outputBuilder.toString();
	}

	/**
	 * Performs validation of the configuration and returns description of the
	 * configuration errors.
	 * 
	 * @return Configuration errors.
	 */
	@Override
	protected ArrayList<String> internalValidate() {
		// TODO 9 Add validation of the configuration
		return new ArrayList<String>();
	}
	
	/**
	 * Get the selected target configuration.
	 */
	private IConfiguration getTargetConfiguration() {
		if (platform == Platform.Flash) {
			return flashConfig;
		} else if (platform == Platform.JavaScript) {
			return jsConfig;
		} else if (platform == Platform.ActionScript) {
			return asConfig;
		} else if (platform == Platform.Neko) {
			return nekoConfig;
		}
		
		return null;
	}
}
