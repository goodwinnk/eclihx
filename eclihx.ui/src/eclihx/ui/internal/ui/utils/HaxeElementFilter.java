package eclihx.ui.internal.ui.utils;

class HaxeElementFilter {
	
	public enum ShowElement {
		All,
		Project,
		SourceFolder
	}
    
	ShowElement elementFilter;
	
	public HaxeElementFilter(ShowElement elementFilter) {
		this.elementFilter = elementFilter;		
	}
	
	public boolean isShowWorkspaceChildren() {
		return true;
	}
	
	public boolean isShowProjectsChildren() {
		return elementFilter == ShowElement.SourceFolder ||
		       elementFilter == ShowElement.All;
	}
	
}
