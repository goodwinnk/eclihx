package eclihx.tests.ui.internal.ui.editors.hx;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.PartInitException;
import org.junit.Assert;
import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;
import eclihx.core.haxe.model.core.IHaxeWorkspace;
import eclihx.ui.internal.ui.editors.hx.HXEditor;
import eclipse.testframework.text.EditorTestHelper;
import eclipse.testframework.text.TextEditorTest;

public abstract class HXEditorTestBase extends TextEditorTest<HXEditor, IHaxeProject> {
	
	protected HXEditor editor;
	protected IHaxeProject project;
	
    private HXEditor openHaxeEditor(IPath path) throws PartInitException {
        IFile file= ResourcesPlugin.getWorkspace().getRoot().getFile(path);
        Assert.assertTrue(file != null && file.exists());              
        
        return (HXEditor)EditorTestHelper.openInEditor(file);        
    }
    
	@Override
	public HXEditor getEditor() {
		if (editor == null) {
			try {
				getProject().createOutputFolder("out", null);				
				IHaxeSourceFolder sourceFolder = getProject().createSourceFolder("src", null);
				IHaxePackage haxePackage = sourceFolder.createPackage("testing", null);
				IHaxeSourceFile haxeFile = haxePackage.createHaxeFile("Test.hx", null);
				
				editor = openHaxeEditor(haxeFile.getBaseResource().getFullPath());				
			} catch (CoreException e) {
				Assert.assertFalse(false);
			}
		}
		
		return editor;
	}

	@Override
	protected void createProject() throws CoreException {
		IHaxeWorkspace workspace = EclihxCore.getDefault().getHaxeWorkspace(); 
		project = workspace.createHaxeProject("Testing", null);
	}

	@Override
	protected IProject getResourceProject() {
		return project.getProjectBase();
	}

	@Override
	protected IHaxeProject getProject() {
		return project;
	}

	@Override
	public ISourceViewer getEditorSourceViewer() {
		return getEditor().getViewer();
	}
}
