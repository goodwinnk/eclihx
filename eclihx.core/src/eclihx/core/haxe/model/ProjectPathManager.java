package eclihx.core.haxe.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IProjectPathManager;

/**
 * Class for storing paths of the project
 */
public class ProjectPathManager implements IProjectPathManager {
	
	private static final String fileName = ".paths";

	private final IProject fProject;
	
	public IFolder outputFolder;
	
	public List<IFolder> sourceFolders = new LinkedList<IFolder>();
	//public List<IFile> buildFiles = new LinkedList<IFile>();
	public List<IFolder> libFolders = new LinkedList<IFolder>();
	public List<IFile> libFiles = new LinkedList<IFile>();
	
	private ProjectPathManager(IProject project) {
		fProject = project;
	}
	
	private ProjectPathManager(IProject project, ProjectPathsSerializer pathsStore) {
		fProject = project;
		
		outputFolder = project.getFolder(pathsStore.outputFolder);
		
		sourceFolders.clear();
		if (pathsStore.sourceFolders != null) {
			for (String sourceFolder : pathsStore.sourceFolders) {
				sourceFolders.add(project.getFolder(sourceFolder));
			}
		}
		
		/*
		buildFiles.clear();
		if (pathsStore.buildFiles != null) {
			for (String buildFile : pathsStore.buildFiles) {
				buildFiles.add(project.getFile(buildFile));
			}
		}
		*/
		
		libFolders.clear();
		if (pathsStore.libFolders != null) {
			for (String libFolder : pathsStore.libFolders) {
				libFolders.add(project.getFolder(libFolder));
			}
		}
		
		libFiles.clear();
		if (pathsStore.libFiles != null) {
			for (String libFile : pathsStore.libFiles) {
				libFiles.add(project.getFile(libFile));
			}
		}
	}
	
	private ProjectPathsSerializer createSerializer() {
		ProjectPathsSerializer pathsStore = new ProjectPathsSerializer();
		
		pathsStore.outputFolder = outputFolder.getProjectRelativePath().toOSString();
		
		int arrayIndex;
		
		pathsStore.sourceFolders = new String[sourceFolders.size()];
		arrayIndex = 0;
		for (IFolder folder : sourceFolders) {
			pathsStore.sourceFolders[arrayIndex] = folder.getProjectRelativePath().toOSString();
			arrayIndex++;
		}
		
		/*
		pathsStore.buildFiles = new String[buildFiles.size()];
		arrayIndex = 0;
		for (IFile file : buildFiles) {
			pathsStore.buildFiles[arrayIndex] = file.getProjectRelativePath().toOSString();
			arrayIndex++;
		}
		*/
		
		pathsStore.libFiles = new String[libFiles.size()];
		arrayIndex = 0;
		for (IFile file : libFiles) {
			pathsStore.libFiles[arrayIndex] = file.getProjectRelativePath().toOSString();
			arrayIndex++;
		}
		
		pathsStore.libFolders = new String[libFolders.size()];
		arrayIndex = 0;
		for (IFolder folder : libFolders) {
			pathsStore.libFolders[arrayIndex] = folder.getProjectRelativePath().toOSString();
			arrayIndex++;
		}
		
		return pathsStore;
	}
	
	
	public void store() {
		try {
			JAXBContext context = JAXBContext.newInstance(ProjectPathsSerializer.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML
		    IFile file = fProject.getFile(fileName);
		    
		    ByteArrayOutputStream os = new ByteArrayOutputStream();
		    
		    marshaller.marshal(createSerializer(), os);
		    
		    InputStream stream = new ByteArrayInputStream(os.toByteArray());
		    
		    if (file.exists()) {
		    	file.setContents(stream, IFile.FORCE, null);
		    } else {
		    	file.create(stream, true, null);
		    }
			
		
		} catch (JAXBException e) {
			EclihxCore.getLogHelper().logError(e);
		
		} catch (CoreException e) {
			EclihxCore.getLogHelper().logError(e);
		}
		
	}
	
	static public IProjectPathManager create(IProject project) {
		IFile file = project.getFile(fileName);
		ProjectPathManager pathManager = null;
		
		if (file.exists()) {
			// Try to load stored data
			InputStream is;
			
			try {
				JAXBContext context = JAXBContext.newInstance(ProjectPathsSerializer.class);
			    Unmarshaller unmarshaller = context.createUnmarshaller();
			    
			    is = file.getContents(true);
			    
				ProjectPathsSerializer pps = (ProjectPathsSerializer)unmarshaller.unmarshal(is);
			    
			    return (new ProjectPathManager(project, pps));
			} catch (JAXBException e) {
				EclihxCore.getLogHelper().logError(e);
			} catch (CoreException e) {
				EclihxCore.getLogHelper().logError(e);
			}
		}
		
		if (pathManager == null) {
			// If loading failed we create manager with default values;
			pathManager = new ProjectPathManager(project);
		}
		
		return pathManager;
	}
	
	
	public void addLibFolder(IFolder folder) {
		assert(folder.getProject().equals(fProject));
		
		libFolders.add(folder);
	}

	public void addSourceFolder(IFolder folder) {
		assert(folder.getProject().equals(fProject));
		
		sourceFolders.add(folder);
		
		// For now allow only one source folder
		assert(sourceFolders.size() == 1); // TODO 4 think of creating several source folders in project  
	}

	public List<IFolder> getLibFolders() {
		return libFolders;
	}

	public IFolder getOutputFolder() {
		return outputFolder;
	}

	public List<IFolder> getSourceFolders() {
		return sourceFolders;
	}

	public void setLibFolders(IFolder[] folders) {
		libFolders.clear();
		for (IFolder folder : folders) {
			addLibFolder(folder);
		}
	}

	public void setOutputFolder(IFolder folder) {
		outputFolder = folder;		
	}

	public void setSourceFolders(IFolder[] folders) {
		sourceFolders.clear();
		for (IFolder folder : folders) {
			addSourceFolder(folder);
		}
	}

	/*
	public void addBuildFile(IFile file) {
		assert(file.getProject().equals(fProject));
		
		buildFiles.add(file);
	}
	*/

	/*
	public List<IFile> getBuildFiles() {
		return buildFiles;
	}
	
	public void setBuildFiles(IFile[] files) {
		buildFiles.clear();
		for (IFile file : files) {
			addBuildFile(file);
		}		
	}*/
}
