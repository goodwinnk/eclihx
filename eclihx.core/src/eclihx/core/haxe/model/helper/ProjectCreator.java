package eclihx.core.haxe.model.helper;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;

/**
 * Special class for creating different project haXe-projects.
 */
public class ProjectCreator {
	
	private static String createDefaultHxmlContent(String projectName, 
			String outFolderName, String srcFolderName) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("-cp " + srcFolderName + "\n");
		builder.append("\n");
		
		builder.append("# Uncomment the desirable target\n");
		
		builder.append("# JavaScript target\n");
		builder.append("# -js " + outFolderName + "\\" + projectName + ".js \n");
		builder.append("\n");

		builder.append("# SWF 9 target\n");
		builder.append("# -swf9 " + outFolderName + "\\" + projectName + ".swf \n");
		builder.append("\n");
		
		builder.append("# ActionScript3 target\n");
		builder.append("# -as3 " + outFolderName + "\n");
		builder.append("\n");

		builder.append("# Neko target\n");
		builder.append("# -neko " + outFolderName + "\\" + projectName + ".n \n");
		builder.append("\n");
		
		builder.append("# PHP target\n");
		builder.append("# -php " + outFolderName + "\n");
		builder.append("\n");
		
		builder.append("# C++ target\n");
		builder.append("# -cpp " + outFolderName + "\n");
		builder.append("\n");
				
		builder.append("# Uncomment and place your main class with package\n");
		builder.append("# -main YouClassName\n");		
		
		return builder.toString();
	}
	
	/**
	 * Create a simple project.
	 * @param projectName Name of the created project. Can't be null.
	 * @param buildFileName Default build file name. Can't be null.
	 * @param outFolderName Output folder name. Can't be null.
	 * @param srcFolderName Source folder name. Can't be null.
	 * @param monitor UI progress monitor. Can't be null.
	 * @throws CoreException some project creation exception.
	 */
	public static void createCommonProject(String projectName, 
			String buildFileName, String outFolderName, String srcFolderName, 
			IProgressMonitor monitor) throws CoreException  {
		
		monitor.beginTask("Simple haXe project creation", 4);
		
		monitor.setTaskName("Creation of project: " + projectName);		
		IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace().
				createHaxeProject(projectName, monitor);		
		monitor.worked(1);
		
		monitor.setTaskName("Creation of output folder: " + outFolderName);		
		haxeProject.createOutputFolder(outFolderName, monitor);		
		monitor.worked(1);
		
		monitor.setTaskName("Creation of source folder: " + srcFolderName);		
		haxeProject.createSourceFolder(srcFolderName, monitor);		
		monitor.worked(1);
		
		monitor.setTaskName("Creation of build file: " + buildFileName);		
		haxeProject.createBuildFile(buildFileName, 
				createDefaultHxmlContent(projectName, outFolderName, srcFolderName), 
				monitor);		
		monitor.worked(1);
	}
}
