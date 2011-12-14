package eclihx.tests.ui.internal.ui.editors.hx;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.PartInitException;
import org.junit.Assert;
import org.junit.Test;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;
import eclihx.core.haxe.model.core.IHaxeWorkspace;
import eclihx.ui.internal.ui.editors.hx.HXEditor;
import eclipse.testframework.text.EditorTestHelper;
import eclipse.testframework.text.TextEditorTest;

public class HXEditorTest extends TextEditorTest<HXEditor, IHaxeProject> {
	
	private HXEditor editor;
	private IHaxeProject project;
	
//	protected void setUp() throws Exception {
//        IPreferenceStore store= JavaPlugin.getDefault().getPreferenceStore();
//        store.setValue(PreferenceConstants.EDITOR_CLOSE_BRACKETS, true);
//	}
//	
//	private void setUpProject(String sourceLevel) throws CoreException, JavaModelException {
//	        fProject= JavaProjectHelper.createJavaProject(getName(), "bin");
//	        fProject.setOption(JavaCore.COMPILER_SOURCE, sourceLevel);
//	        JavaProjectHelper.addSourceContainer(fProject, SRC);
//	        IPackageFragment fragment= fProject.findPackageFragment(new Path(SEP + getName() + SEP + SRC));
//	        fragment.createCompilationUnit(CU_NAME, CU_CONTENTS, true, new NullProgressMonitor());
//	}
//	
//	private void setUpEditor() {
//	        fEditor= openJavaEditor(new Path(SEP + getName() + SEP + SRC + SEP + CU_NAME));
//	        assertNotNull(fEditor);
//	        fTextWidget= fEditor.getViewer().getTextWidget();
//	        assertNotNull(fTextWidget);
//	        fAccessor= new Accessor(fTextWidget, StyledText.class);
//	        fDocument= fEditor.getDocumentProvider().getDocument(fEditor.getEditorInput());
//	        assertNotNull(fDocument);
//	        assertEquals(CU_CONTENTS, fDocument.get());
//	}
	
    private HXEditor openHaxeEditor(IPath path) throws PartInitException {
        IFile file= ResourcesPlugin.getWorkspace().getRoot().getFile(path);
        Assert.assertTrue(file != null && file.exists());              
        
        return (HXEditor)EditorTestHelper.openInEditor(file);
        
    }
	
	@Test
	public void autoBracketInserter() throws BadLocationException {
		setCaret(0);
		type("((((");

		Assert.assertEquals("(((())))", fDocument.get(0, 8));		
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
