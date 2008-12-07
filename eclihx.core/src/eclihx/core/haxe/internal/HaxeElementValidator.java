package eclihx.core.haxe.internal;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eclihx.core.EclihxCore;
import eclihx.core.util.FileNameValidator;
import eclihx.core.util.FileNameValidator.FileValidateVerdict;
import eclihx.core.util.FileNameValidator.FileValidationResult;

/**
 * Class validates different internal haXe entities.
 */
public final class HaxeElementValidator {
	
	/**
	 * Just an underscore char and nothing more.
	 */
	private final static char UNDERSCORE_CHAR = '_';
	
	/**
	 * OK status for all checks.
	 */
	private final static IStatus OK_STATUS = 
			new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
	
	/**
	 * Method simplify creation of the error status.
	 * @param errorMessage the message which should be written to status.
	 */
	private static IStatus createErrorStatus(String errorMessage) {
		return new Status(
				IStatus.ERROR, EclihxCore.PLUGIN_ID, 
				errorMessage);
	}
	
	
	/**
	 * Method validates the haXe package name.
	 * @param packageName the name to validate.
	 * @return status of the validation. Error status has the assigned reason of
	 *         the error.
	 */
	public static IStatus validatePackageName(String packageName) {
		
		if (packageName == null) {
			return createErrorStatus("A package name must not be null.");
		}
		
		// For the length validations
		int length = packageName.length();
		
		if (length == 0) {
			return createErrorStatus("A package name must not be empty.");
		}
		
		if (packageName.startsWith(".") || packageName.endsWith(".")) {
			return createErrorStatus(
					"A package name cannot start or end with a dot.");
		}
		

		String[] packagesParts = packageName.split("\\.");
		
		for (String packagePart : packagesParts) {
			
			IStatus identifierStatus = validateIdentifier(packagePart);
			
			if (!identifierStatus.isOK()) {
				return createErrorStatus(
						String.format(
								"Error in \"%s\" identifier. %s", 
								packagePart, identifierStatus.getMessage()));
			}
		
			
			if (Character.isUpperCase(packagePart.charAt(0))) {
				return createErrorStatus(
						"Package names must start with a lowercase letter.");
			}
		}
		
		return OK_STATUS;
	}
	
	/**
	 * Method validates the name of haXe identifier.
	 * 
	 * @param identifierName the haXe identifier name.
	 * @return status of the validation. Error status has the assigned reason of
	 *         the error.
	 */
	public static IStatus validateIdentifier(String identifierName) {
		
		if (identifierName == null) {
			return createErrorStatus("A identifier name must not be null.");
		}
		
		if (identifierName.isEmpty()) {
			return createErrorStatus("A identifier name must not be empty.");
		}
		
		if (!(Character.isLetter(identifierName.charAt(0)) || 
			  identifierName.charAt(0) == UNDERSCORE_CHAR)) {
			return createErrorStatus(
					"A identifier name must start with a letter.");
		}
		
		for (char ch : identifierName.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == UNDERSCORE_CHAR)) {
				return createErrorStatus(
						"A identifier name must contain only letters, " +
						"digits and underscores.");
			}
		}		
		
		return OK_STATUS;
	}
	
	/**
	 * Method validates the name of haXe build file.
	 * 
	 * @param buildFileName the name of the file.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateBuildFileName(String buildFileName) {
		
		final FileValidationResult validateResult = 
			FileNameValidator.validateFileName(
					buildFileName, HaxePreferencesManager.BUILD_FILE_EXTENSION);
		
		String errorMessage = "";
		
		switch (validateResult.getVerdict()) {
			case Ok:
				return OK_STATUS;
			case NullFileName:
				errorMessage = "A build file name must not be null.";
				break;
			case Empty:
				errorMessage = "A build file name must not be empty.";
				break;
			case InvalidExtension:
				errorMessage = String.format(
						"A build file name must have '%s' extension.",
						HaxePreferencesManager.BUILD_FILE_EXTENSION);
				break;
			case InvalidWithMessage:
				errorMessage = validateResult.getMessage();
				break;
			case InvalidUnknown:
				errorMessage = 
					"A name must be a valid file name.";
		}
		
		assert(validateResult.getVerdict() != FileValidateVerdict.Ok);
		
		// If file name is valid
		return createErrorStatus(errorMessage);
	}
	
	/**
	 * Method validates the name of haXe file.
	 * 
	 * @param haxeFileName the name of the file.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateHaxeFileName(String haxeFileName) {
		
		//FIXME 4 Is it allowed to make files with the first number char.
		
		final FileValidationResult validateResult = 
			FileNameValidator.validateFileName(
					haxeFileName, HaxePreferencesManager.HAXE_FILE_EXTENSION);
		
		String errorMessage = "";
		
		switch (validateResult.getVerdict()) {
			case Ok:
				return OK_STATUS;
			case NullFileName:
				errorMessage = "A haXe file name must not be null.";
				break;
			case Empty:
				errorMessage = "A haXe file name must not be empty.";
				break;
			case InvalidExtension:
				errorMessage = String.format(
						"A haXe file name must have '%s' extension.",
						HaxePreferencesManager.HAXE_FILE_EXTENSION);
				break;
			case InvalidWithMessage:
				errorMessage = validateResult.getMessage();
				break;
			case InvalidUnknown:
				errorMessage = 
					"A name must be a valid file name.";
		}
		
		assert(validateResult.getVerdict() != FileValidateVerdict.Ok);
		
		// If file name is valid
		return createErrorStatus(errorMessage);
	}
	
	/**
	 * Method validates the name of haXe output folder.
	 * 
	 * @param outputFolderName the name of the folder.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateHaxeOutputFolderName(
			String outputFolderName) {
		
		if (outputFolderName == null) {
			return createErrorStatus("A output folder name must not be null.");
		}
		
		if (outputFolderName.isEmpty()) {
			return createErrorStatus("A output folder name must not be empty.");
		}
		
		
		IStatus validateStatus = ResourcesPlugin.getWorkspace().validateName(
				outputFolderName, IResource.FOLDER);
		
		if (!validateStatus.isOK()) {
			return createErrorStatus(
					"Error in output folder name: " + 
					validateStatus.getMessage());
		}
		
		return OK_STATUS;		
	}
	
	/**
	 * Method validates the name of haXe source folder.
	 * 
	 * @param sourceFolderName the name of the folder.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateHaxeSourceFolderName(
			String sourceFolderName) {
		
		if (sourceFolderName == null) {
			return createErrorStatus("A source folder name must not be null.");
		}
		
		if (sourceFolderName.isEmpty()) {
			return createErrorStatus("A source folder name must not be empty.");
		}
		
		
		IStatus validateStatus = ResourcesPlugin.getWorkspace().validateName(
				sourceFolderName, IResource.FOLDER);
		
		if (!validateStatus.isOK()) {
			return createErrorStatus(
					"Error in source folder name: " + 
					validateStatus.getMessage());
		}
		
		return OK_STATUS;
	}
	
}
