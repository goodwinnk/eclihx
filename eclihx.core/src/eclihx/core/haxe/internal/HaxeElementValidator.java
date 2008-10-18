package eclihx.core.haxe.internal;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import eclihx.core.EclihxCore;

/**
 * Class validates different internal haXe entities.
 */
public final class HaxeElementValidator {
	
	/**
	 * Just an underscore char and nothing more.
	 */
	private final static char UNDERSCORE_CHAR = '_';
	
	/**
	 * Build file extension.
	 */
	private final static String BUILD_FILE_EXTENSION = "hxml";
	
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
					"A package name must not be null.");
		}
		
		// For the length validations
		int length = packageName.length();
		
		if (length == 0) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A package name must not be empty.");
		}
		
		if (packageName.startsWith(".") || packageName.endsWith(".")) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A package name cannot start or end with a dot.");
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
						"Package names must start with a lowercase letter.");
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
					"A identifier name must not be null.");
		}
		
		if (identifierName.isEmpty()) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A identifier name must not be empty.");
		}
		
		if (!(Character.isLetter(identifierName.charAt(0)) || 
			  identifierName.charAt(0) == UNDERSCORE_CHAR)) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A identifier name must start with a letter.");
		}
		
		for (char ch : identifierName.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == UNDERSCORE_CHAR)) {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						"A identifier name must contain only letters, " +
						"digits and underscores.");
			}
		}		
		
		return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
	}
	
	/**
	 * Method validates the name of haXe build file.
	 * 
	 * @param buildFileName the name of the file.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateBuildFileName(String buildFileName) {
		
		if (buildFileName == null) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"A build file name must not be null.");
		}
		
		if (!Path.ROOT.isValidSegment(buildFileName)) {
			if (buildFileName.isEmpty()) {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						"A build file name must not be empty.");
			} else {
				return new Status(
						IStatus.ERROR, EclihxCore.PLUGIN_ID, 
						"A build file string must be a valid file name.");
			}
		}
		
		IPath buildPath = new Path(buildFileName);
		String fileExtension = buildPath.getFileExtension();		
		
		if (fileExtension == null || 
				!buildPath.getFileExtension().equals(BUILD_FILE_EXTENSION)) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					String.format(
							"A build file name must have '%s' extension.",
							BUILD_FILE_EXTENSION));
		}

		
		IStatus status = 
				ResourcesPlugin.getWorkspace().validateName(
						buildFileName, IResource.FILE);
		
		if (!status.isOK()) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					status.getMessage());
		}
		
		return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
	}
	
}
