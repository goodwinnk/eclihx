package eclihx.core.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

/**
 * Class validates the file.
 */
public class FileNameValidator {
	
	/**
	 * Results for the file name validation process.
	 */
	public enum FileValidateVerdict {
		/**
		 * File is valid.
		 */
		Ok,
		
		/**
		 * File name is null.
		 */
		NullFileName,
		
		/**
		 * File name is empty.
		 */
		Empty,
		
		/**
		 * Invalid extension.
		 */
		InvalidExtension,
		
		/**
		 * For this error code you can check the error message.
		 */
		InvalidWithMessage,		
		
		/**
		 * Invalid file with some reason.
		 */
		InvalidUnknown;
		
		String message = "";

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}		
	};
	
	/**
	 * Result of the file validation. Beside of the verdict variable object of
	 * this class stores a message with explanation of the error. This message
	 * could be asked only for {@link FileValidateVerdict#InvalidWithMessage}
	 * result state. 
	 */
	public static class FileValidationResult {

		/**
		 * Conclusion about file name validity.
		 */
		private final FileValidateVerdict verdict;
		
		/**
		 * Message with the verdict explanation.
		 */
		private final String message;
		
		/**
		 * Creates the result with verdict and a message.
		 * @param verdict the conclusion of the check.
		 * @param message the message with the explanation.
		 */
		public FileValidationResult(FileValidateVerdict verdict, String message) {
			super();
			this.message = message;
			this.verdict = verdict;
		}
		
		/**
		 * Creates the result with verdict and an empty message.
		 * @param verdict the conclusion of the check.
		 */
		public FileValidationResult(FileValidateVerdict verdict) {
			this(verdict, "");
		}
		
		/**
		 * Get the conclusion about the check.
		 * 
		 * @return the check result.
		 */
		public FileValidateVerdict getVerdict() {
			return verdict;
		}
		/**
		 * Message with the explanation.
		 * 
		 * @return the message with the explanation.
		 */
		public String getMessage() {
			return message;
		}
		
	}
	
	/**
	 * Validates file name. Additional you can set expected file extension. 
	 * 
	 * @param fileName the name to validate.
	 * @param extension an expected file extension. If this parameter is null
	 *        there would be no checks for extension.
	 *        
	 * @return the result of validation.
	 */
	public static FileValidationResult validateFileName(
			String fileName, String extension) {
		
		//TODO 6 Make some predefined results
		
		if (fileName == null) {
			return new FileValidationResult(FileValidateVerdict.NullFileName);
		}
		
		if (!Path.ROOT.isValidSegment(fileName)) {
			if (fileName.isEmpty()) {
				return new FileValidationResult(FileValidateVerdict.Empty);
			} else {
				
				IStatus status = 
					ResourcesPlugin.getWorkspace().validateName(
							fileName, IResource.FILE);
			
				if (!status.isOK()) {
					return new FileValidationResult(
							FileValidateVerdict.InvalidWithMessage, 
							status.getMessage());
				}
				
				return new FileValidationResult(
						FileValidateVerdict.InvalidUnknown);
			}
		}
		
		// We should check file name extension.
		if (extension != null) {
			IPath buildPath = new Path(fileName);
			String fileExtension = buildPath.getFileExtension();		
			
			if (fileExtension == null || 
					!buildPath.getFileExtension().equals(extension)) {
				return new FileValidationResult(
						FileValidateVerdict.InvalidExtension);
			}
		}
		
		return new FileValidationResult(FileValidateVerdict.Ok);
		
	}

}
