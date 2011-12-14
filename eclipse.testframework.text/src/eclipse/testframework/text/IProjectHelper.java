package eclipse.testframework.text;

import org.eclipse.core.runtime.CoreException;

public interface IProjectHelper<T> {
	public T createProject(String projectName, String binFolderName) throws CoreException;
	
}
