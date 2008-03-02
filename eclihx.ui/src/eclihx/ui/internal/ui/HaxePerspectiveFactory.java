package eclihx.ui.internal.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;

import eclihx.ui.internal.ui.views.PackageExplorerView;




public class HaxePerspectiveFactory implements IPerspectiveFactory {

	
	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		// Top left
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.25, editorArea);
		topLeft.addView(PackageExplorerView.PACKAGE_EXPLORER_ID);
		topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

		// Bottom left
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float) 0.50, editorArea);
		bottomLeft.addView(IPageLayout.ID_OUTLINE);

		// Bottom right
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, (float) 0.65, editorArea);
		bottomRight.addView(IPageLayout.ID_TASK_LIST);
		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
		
		
	}
}
