package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Storage for haXe compiler configuration. This class is used by the parser 
 * of haXe compiler parameters.
 */
public class HaxeConfiguration extends AbstractConfiguration {

	/**
	 * Output platforms.
	 */
	public enum Platform {
		Flash, 
		ActionScript,
		JavaScript,
		Neko,
		PHP, // TODO 4 support this platform.
		NoOutput
	}

	/**
	 * Target platform of the compiler.
	 */
	private Platform platform = Platform.NoOutput;
	
	/**
	 * This flag monitors explicit setting of the target platform and will
	 * allow determine the situation of the multi-platform assignment.  
	 */
	private boolean isPlatformExplicitlySet; 
	
	/**
	 * No output for configuration (--no-output).
	 * This flag will turn <code>platform</code> field to NoOutput.
	 */
	private boolean explicitNoOutput;

	//Platform specific configuration storages.
	//private JavaScriptConfiguration jsConfig = null;
	//private PHPConfiguration phpConfig = null;
	private final FlashConfiguration flashConfig = new FlashConfiguration();
	private final ASConfiguration asConfig = new ASConfiguration();
	private final NekoConfiguration nekoConfig = new NekoConfiguration();

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
	 * Help flag
	 */
	private boolean helpMode;

	/**
	 * Creates the string representation of the haXe build parameter by the key
	 * and value. Note that both values can't be <code>null</value>.
	 * 
	 * @param key
	 *            suffix of the parameter.
	 * @param value
	 *            parameter value
	 * @return string representation of the parameter.
	 */
	public final static String GenerateParameter(String key, String value) {

		if (key == null || value == null) {
			throw new NullPointerException(
				"Parameters of GenerateParameter are not allowed to have null" +
				"values");
		}

		return key + " " + value + " ";
	}
	
	/**
	 * Return string representation of the configuration flag parameter.
	 * 
	 * @param key
	 *            suffix of the parameter.
	 * @param exist
	 *            is parameter exist configuration.
	 * @return string representation of the flag parameter.
	 */
	public final static String GenerateFlagParameter(
		String key, boolean exist) {

		if (key == null) {
			throw new NullPointerException(
				"Parameters of GenerateParameter are not allowed to have null" +
				"values");
		}
		
		// Print the key if it should be, print nothing in other case 
		return exist ? key + " ": "";
	}

	/**
	 * Default constructor for configuration.
	 */
	public HaxeConfiguration() {
	}

	/**
	 * Get output platform.
	 * @return the platform
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Set output platform. Note that this method can be called only once.
	 * Changing of the target platform isn't allowed and second call of the
	 * method will cause throwing of the exception.
	 * @param platform
	 *            the platform to set. Can't be <code>null</code>.
	 * @throws InvalidConfigurationException if this method called 
	 * 		   more than once.
	 */
	// TODO 4 Correct method behavior.
	public void setPlatform(Platform platform) 
			throws InvalidConfigurationOperationException {
		
		if (platform == null) {
			throw new NullPointerException("platform parameter can't be null");
		}
		
		// There is an attempt to set target platform more than once.
		if (isPlatformExplicitlySet) {
			throw new InvalidConfigurationOperationException(
				"Multi output targets are not allowed in haXe configuration."
			);
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
	 * Get flash configuration.
	 * @return Flash target platform options container.
	 */
	public FlashConfiguration getFlashConfig() {
		return flashConfig;
	}

	/**
	 * Get ActionScript configuration.
	 * @return the ActionScript configuration container
	 */
	public ASConfiguration getASConfig() {
		return asConfig;
	}

	/**
	 * Get Neko configuration.
	 * @return the ActionScript configuration container
	 */
	public NekoConfiguration getNekoConfig() {
		return nekoConfig;
	}

	/**
	 * Check if debug mode is enabled.
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
	 * @return the libraries
	 */
	public Collection<String> getLibraries() {
		return libraries;
	}

	/**
	 * Add haXe library to configuration
	 * @param library
	 *            the library to add
	 */
	public void addLibrary(String library) {
		// TODO 6 Add check for validness and uniqueness
		libraries.add(library);
	}

	/**
	 * Checks if configuration has some compilation flag
	 * 
	 * @return the compilationFlags
	 */
	public boolean hasCompilationFlags(String flag) {
		return compilationFlags.contains(flag);
	}

	/**
	 * Adds some compilation flag
	 * 
	 * @param compilationFlag
	 *            the compilationFlag to add
	 */
	public void addCompilationFlag(String compilationFlag) {
		// TODO 6 Add check for validness and uniqueness
		this.compilationFlags.add(compilationFlag);
	}
	
	/**
	 * Adds new source directory to configuration.
	 * @param directory
	 * 		directory to add.
	 */
	public void addSourceDirectory(String directory) {
		sourceDirectories.add(directory);
	}
	
	/**
	 * Returns collection of source directories.
	 * @return
	 * 		collection of source directories.
	 */
	public Collection<String> getSourceDirectories() {
		return sourceDirectories;
	}
	
	/**
	 * Get the name of the startup class
	 * @return the startupClass
	 */
	public String getStartupClass() {
		return startupClass;
	}

	/**
	 * Set the startup class.
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
	 */
	public void enableTips() {
		displayTips = true;
	}
	
	/**
	 * Returns help mode status
	 */
	public boolean isHelpMode() {
		return helpMode;
	}	
	
	/**
	 * Enables help mode
	 */
	public void enableHelp() {
		helpMode = true;
	}
	
	
	/**
	 * Prints the configuration.
	 * 
	 * @throws InvalidConfigurationException
	 *             Exception with the errors descriptions if 
	 *             configuration is invalid.
	 */
	@Override
	public String printConfiguration() throws InvalidConfigurationException {
		
		validateException();
		
		StringBuilder outputBuilder = new StringBuilder();
		
		// Startup class
		outputBuilder.append(
			GenerateParameter(
				HaxePreferencesManager.PARAM_PREFIX_STARTUP_CLASS,
				startupClass));
		
		// Store libraries
		for (String library : libraries) {
			outputBuilder.append(
				GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_HAXE_LIB,
					library));
		}
		
		// Store libraries
		for (String compileFlag: compilationFlags) {
			outputBuilder.append(
				GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_COMPILATION_FLAG,
					compileFlag));
		}
		
		// Print source directories
		for (String sourceDirectory: sourceDirectories) {
			outputBuilder.append(
				GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_SOURCE_DIRECTORY,
					OSUtil.quoteCompoundPath(sourceDirectory)));
		}
		
		// Debug parameter
		outputBuilder.append(
			GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_DEBUG_MODE_FLAG, 
				debug));
		
		// Display code tips
		outputBuilder.append(
			GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_CODE_TIPS_FLAG, 
				displayTips));
		
		// No output
		outputBuilder.append(
			GenerateFlagParameter(
				HaxePreferencesManager.PARAM_PREFIX_NO_OUTPUT_FLAG, 
				explicitNoOutput));
		
		if (platform == Platform.Flash) {
			outputBuilder.append(flashConfig.printConfiguration());
		}
		
		return outputBuilder.toString();
	}

	/**
	 * Performs validation of the configuration and returns description of 
	 * the configuration errors.
	 * 
	 * @return Configuration errors.
	 */
	@Override
	protected ArrayList<String> internalValidate() {
		// TODO 9 Add validation of the configuration
		return new ArrayList<String>();
	}
}
