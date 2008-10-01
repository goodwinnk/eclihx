package eclihx.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * EclihX Logger Class.
 */
public final class EclihxLogger implements IPluginLogger {

	/**
	 * Plug-in identification number.
	 */
	private final String pluginID;
	
	/**
	 * Plug-in this logger should work for.
	 */
	private final Plugin plugin;
	
	/**
	 * Constructor.
	 * @param pluginID id of the plug-in
	 * @param plugin Plug-in this logger should work for.
	 */
	public EclihxLogger(Plugin plugin, String pluginID) {
		this.pluginID = pluginID;
		this.plugin = plugin;
	}
	
	/**
	 * Log message with info status.
	 * @param message message to log.
	 */
	public void logInfo(String message) {
		log(IStatus.INFO, IStatus.OK, message, null);
	}

	/**
	 * Log exception with error status.
	 * @param exception exception to log. 
	 */
	public void logError(Throwable exception) {
		logError("Unexpected Exception", exception);
	}

	/**
	 * Log exception with the message.
	 * @param message message to log.
	 * @param exception exception to log.
	 */
	public void logError(String message, Throwable exception) {
		log(IStatus.ERROR, IStatus.ERROR, message, exception);
	}
	
	/**
	 * Log error message.
	 * @param message message to log.
	 */
	public void logError(String message) {
		logError(message, null);
	}

	/**
	 * Full log wrapper.
	 * 
	 * @param severity severity of the log message.
	 * @param code status of the message.
	 * @param message message to log.
	 * @param exception exception to log.
	 */
	public void log(int severity, int code, String message,
			Throwable exception) {
		log(createStatus(severity, code, message, exception));
	}

	/**
	 * Method generates status for the EclihX plug-ins.	
	 * @param severity severity of the log message.
	 * @param code status of the message.
	 * @param message message to log.
	 * @param exception exception to log.
	 * @return Generated status.
	 */
	public IStatus createStatus(int severity, int code, String message,
			Throwable exception) {
		return new Status(
				severity, pluginID, code, message, exception);
	}

	/**
	 * Log the status.
	 * @param status status to log.
	 */
	public void log(IStatus status) {
		plugin.getLog().log(status);
	}
}
