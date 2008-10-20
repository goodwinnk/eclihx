package eclihx.core.haxe.internal;

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
	 * Build file extension.
	 */
	private final static String BUILD_FILE_EXTENSION = "hxml";
	
	/**
	 * haXe file extension.
	 */
	private final static String HAXE_FILE_EXTENSION = "hx";
	
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
	 * 
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
		
		final FileValidationResult validateResult = 
			FileNameValidator.validateFileName(
					buildFileName, BUILD_FILE_EXTENSION);
		
		String errorMessage = "";
		
		switch (validateResult.getVerdict()) {
			case Ok:
				return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
			case NullFileName:
				errorMessage = "A build file name must not be null.";
				break;
			case Empty:
				errorMessage = "A build file name must not be empty.";
				break;
			case InvalidExtension:
				errorMessage = String.format(
						"A build file name must have '%s' extension.",
						BUILD_FILE_EXTENSION);
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
		return new Status(IStatus.ERROR, EclihxCore.PLUGIN_ID, errorMessage);
	}
	
	/**
	 * Method validates the name of haXe file.
	 * 
	 * @param haxeFileName the name of the file.
	 * @return OK status if name is valid and error status with the error 
	 *         message if name is invalid. 
	 */
	public static IStatus validateHaxeFileName(String haxeFileName) {
		
		final FileValidationResult validateResult = 
			FileNameValidator.validateFileName(
					haxeFileName, HAXE_FILE_EXTENSION);
		
		String errorMessage = "";
		
		switch (validateResult.getVerdict()) {
			case Ok:
				return new Status(Status.OK, EclihxCore.PLUGIN_ID, "");
			case NullFileName:
				errorMessage = "A haXe file name must not be null.";
				break;
			case Empty:
				errorMessage = "A haXe file name must not be empty.";
				break;
			case InvalidExtension:
				errorMessage = String.format(
						"A haXe file name must have '%s' extension.",
						HAXE_FILE_EXTENSION);
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
		return new Status(IStatus.ERROR, EclihxCore.PLUGIN_ID, errorMessage);
	}
	
}
