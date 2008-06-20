package eclihx.core.haxe.internal.configuration;

import java.util.LinkedList;

import eclihx.core.haxe.internal.NekoConfiguration;

public class HaxeConfiguration {
	
	enum Platform {
		Flash,
		AS,
		Neko,
		Cross // No platform no output
	}

	private Platform platform = Platform.Cross;
	
	private FlashConfiguration flashConfig = null;
	private ASConfiguration asConfig = null;
	private NekoConfiguration nekoConfig = null;
	
	private boolean debug;
	
	private LinkedList<String> libraries;
	private LinkedList<String> compilationFlags;
	
	
	public HaxeConfiguration() {}


	/**
	 * @return the platform
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(Platform platform) throws InvalidConfiguration{
		if (this.platform != Platform.Cross) {
			throw new InvalidConfiguration("Multiple targets");
		} else {
			this.platform = platform;
		}
	}


	/**
	 * @return the flashConfig
	 */
	public FlashConfiguration getFlashConfig() {
		if (flashConfig == null) {
			flashConfig = new FlashConfiguration();
		}
		return flashConfig;
	}


	/**
	 * @return the asConfig
	 */
	public ASConfiguration getASConfig() {
		if (asConfig == null) {
			asConfig = new ASConfiguration();
		}
		return asConfig;
	}


	/**
	 * @return the nekoConfig
	 */
	public NekoConfiguration getNekoConfig() {
		if (nekoConfig == null) {
			nekoConfig = new NekoConfiguration();
		}
		return nekoConfig;
	}

	
	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}


	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}


	/**
	 * @return the libraries
	 */
	public LinkedList<String> getLibraries() {
		return libraries;
	}


	/**
	 * @param library the library to add
	 */
	public void addLibrary(String library) {
		if (libraries == null) {
			libraries = new LinkedList<String>();
		}
		
		libraries.add(library);
	}


	/**
	 * Checks if configuration has some compilation flag
	 * @return the compilationFlags
	 */
	public boolean hasCompilationFlags(String flag) {
		return compilationFlags == null ? false : compilationFlags.contains(flag);
	}


	/**
	 * Adds some compilation flag
	 * @param compilationFlag the compilationFlag to add
	 */
	public void addCompilationFlag(String compilationFlag) {
		if (compilationFlags == null) {
			compilationFlags = new LinkedList<String>();
		}
		
		this.compilationFlags.add(compilationFlag);
	}
	
	/**
	 * @param header
	 * @throws InvalidConfiguration 
	 * @see eclihx.core.haxe.internal.configuration.FlashConfiguration#setHeader(java.lang.String)
	 */
	public void setFlashHeader(String header) throws InvalidConfiguration {
		this.setPlatform(Platform.Flash);
		flashConfig.setHeader(header);
	}


	/**
	 * @param outFile
	 * @throws InvalidConfiguration 
	 * @see eclihx.core.haxe.internal.configuration.FlashConfiguration#setOutFile(java.lang.String)
	 */
	public void setFlashOutFile(String outFile) throws InvalidConfiguration {
		this.setPlatform(Platform.Flash);
		flashConfig.setOutFile(outFile);
	}


	/**
	 * @param version
	 * @throws InvalidConfiguration 
	 * @see eclihx.core.haxe.internal.configuration.FlashConfiguration#setVersion(int)
	 */
	public void setFlashVersion(int version) throws InvalidConfiguration {
		this.setPlatform(Platform.Flash);
		flashConfig.setVersion(version);
	}	
}
