package eclihx.core.haxe.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eclihx.core.EclihxCore;

/**
 * Class validates different internal haXe entities.
 */
public final class HaxeElementValidator {
	
	private final static char UNDERSCORE_CHAR = '_';
	private final static char DOT_CHAR = '.';
	
	/**
	 * Method validates the haXe package name.
	 * @param packageName the name to validate.
	 * @return status of the validation. Error status has the assigned reason of
	 *         the error.
	 */
	public static IStatus validatePackageName(String packageName) {
		
		if (packageName == null) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A package name must not be null");
		}
		
		// For the length validations
		int length = packageName.length();
		
		if (length == 0) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A package name must not be empty");
		}
		
		if (packageName.startsWith(".") || packageName.endsWith(".")) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A package name cannot start or end with a dot");
		}
		

		String[] packagesParts = packageName.split("\\.");
		
		for (String packagePart : packagesParts) {
			
			IStatus identifierStatus = validateIdentifier(packagePart);
			
			if (!identifierStatus.isOK()) {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						String.format(
								"Error in \"%s\" identifier. %s", 
								packagePart, identifierStatus.getMessage()));
			}
		
			
			if (Character.isUpperCase(packagePart.charAt(0))) {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						"Package names must start with a lowercase letter");
			}
		}
				
		return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
	}
	
	/**
	 * Method validates the name of haXe identifier.
	 * @param identifierName the haXe identifier name.
	 * @return status of the validation. Error status has the assigned reason of
	 *         the error.
	 */
	public static IStatus validateIdentifier(String identifierName) {
		
		if (identifierName == null) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A identifier name must not be null");
		}
		
		if (identifierName.length() == 0) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A identifier name must not be empty");
		}
		
		if (!(Character.isLetter(identifierName.charAt(0)) || 
			  identifierName.charAt(0) == UNDERSCORE_CHAR)) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A identifier name must start with a letter");
		}
		
		for (char ch : identifierName.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == UNDERSCORE_CHAR)) {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						"A identifier name must contain only letters, " +
						"digits and underscores");
			}
		}		
		
		return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
	}
	
}
