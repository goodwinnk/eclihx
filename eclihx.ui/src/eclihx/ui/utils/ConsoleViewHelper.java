package eclihx.ui.utils;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

/**
 * Helper for working with IConsoleManager.
 */
public final class ConsoleViewHelper {
	
	/**
	 * Find the appreciate console.
	 * 
	 * @param name the name of the console
	 * @return the console object.
	 */
	public static MessageConsole findConsole(final String name) {
		
		final ConsolePlugin plugin = ConsolePlugin.getDefault();
		final IConsoleManager conMan = plugin.getConsoleManager();
		
		for (IConsole console : conMan.getConsoles()) {
			if (name.equals(console.getName())) {
				return (MessageConsole) console;
			}
		}
		
		// no console found, so create a new one
		final MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
}
