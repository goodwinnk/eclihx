package eclihx.ui.internal.ui.utils;

class HaxeElementFilter {
	
	public enum ShowElement {
		All,
		Project,
		SourceFolder,
		Packages,
		BuildFiles,
		OutputFolder,
		OutputResources,
		SourceFiles
	}
    
	private final ShowElement elementFilter;
	
	public HaxeElementFilter(ShowElement elementFilter) {
		this.elementFilter = elementFilter;		
	}
	
	public boolean showWorkspaceChildren() {
		return true;
	}
	
	public boolean showProjectsChildren() {
		return elementFilter != ShowElement.Project;
	}
	
	public boolean showSourceFolderChildren() {
		return elementFilter == ShowElement.All ||
			   elementFilter == ShowElement.Packages ||
		       elementFilter == ShowElement.SourceFiles;
	}
	
	public boolean showPackageChildren() {
		return elementFilter == ShowElement.All ||
		       elementFilter == ShowElement.SourceFiles;
	}
	
	public boolean showSourceFolder() {
		return elementFilter == ShowElement.All || 
			   elementFilter == ShowElement.SourceFolder ||
			   elementFilter == ShowElement.SourceFiles ||
			   elementFilter == ShowElement.Packages;
	}
	
	public boolean showBuildFiles() {
		return elementFilter == ShowElement.All || 
			   elementFilter == ShowElement.BuildFiles;
	}
	
	public boolean showOutputFolder() {
		return elementFilter == ShowElement.All || 
			   elementFilter == ShowElement.OutputFolder;
	}
	
	public boolean showSourceFiles() {
		return elementFilter == ShowElement.All || 
			   elementFilter == ShowElement.SourceFiles;
	}
	
	public boolean showOutputChildren() {
		return elementFilter == ShowElement.All || 
		   	   elementFilter == ShowElement.OutputResources;
	}
}
