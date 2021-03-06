package eclipse.testframework.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Event;
import org.eclipse.text.tests.Accessor;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public abstract class TextEditorTest<TEditor extends AbstractDecoratedTextEditor, TProject>
		extends ProjectTest<TProject> {

	public static final String CARET = "<caret>";

	protected static class TextInfo {
		private final int offset;
		private final String text;

		public TextInfo(int offset, String text) {
			this.offset = offset;
			this.text = text;
		}

		public int getOffset() {
			return offset;
		}

		public String getText() {
			return text;
		}
	}

	private Accessor fAccessor;

	protected StyledText fTextWidget;
	protected IDocument fDocument;

	public abstract TEditor getEditor();

	public abstract ISourceViewer getEditorSourceViewer();

	@Before
	public void setUp() throws Exception {
		super.setUp();

		setUpEditor();
	}

	protected void setUpEditor() {
		fTextWidget = getEditorSourceViewer().getTextWidget();
		fAccessor = new Accessor(fTextWidget, StyledText.class);
		fDocument = getEditor().getDocumentProvider().getDocument(
				getEditor().getEditorInput());
		fDocument.set("");
	}

	@After
	public void tearDown() throws Exception {
		EditorTestHelper.closeEditor(getEditor());

		super.tearDown();
	}

	protected void setText(String text) {
		fDocument.set(text);
	}

	protected String getText() {
		return fDocument.get();
	}

	protected void configureFromText(String text) {
		TextInfo info = analyzeConfigText(text);
		
		setText(info.getText());
		setCaret(info.getOffset());
		
		DisplayHelper.sleep(EditorTestHelper.getActiveDisplay(), 200);
	}

	protected void assertByText(String text) {
		TextInfo expectedInfo = analyzeConfigText(text);

		Assert.assertEquals(expectedInfo.getText(), getText());
		Assert.assertEquals(expectedInfo.getOffset(), getCaret());
	}

	protected static TextInfo analyzeConfigText(String text) {
		int caretIndex = text.indexOf(CARET);
		if (caretIndex != -1) {
			return new TextInfo(caretIndex, text.replaceAll(CARET, ""));
		}

		return new TextInfo(0, text);
	}

	/**
	 * Type characters into the styled text.
	 * 
	 * @param characters
	 *            the characters to type
	 */
	protected void type(CharSequence characters) {
		for (int i = 0; i < characters.length(); i++)
			type(characters.charAt(i), 0, 0);
	}

	/**
	 * Type a character into the styled text.
	 * 
	 * @param character
	 *            the character to type
	 */
	protected void type(char character) {
		type(character, 0, 0);
	}

	/**
	 * Ensure there is a linked mode and type a character into the styled text.
	 * 
	 * @param character
	 *            the character to type
	 * @param nested
	 *            whether the linked mode is expected to be nested or not
	 * @param expectedExitFlags
	 *            the expected exit flags for the current linked mode after
	 *            typing the character, -1 for no exit
	 */
	protected void linkedType(char character, boolean nested,
			int expectedExitFlags) {
		final int[] exitFlags = { -1 };
		assertModel(nested).addLinkingListener(new ILinkedModeListener() {
			public void left(LinkedModeModel model, int flags) {
				exitFlags[0] = flags;
			}

			public void resume(LinkedModeModel model, int flags) {
			}

			public void suspend(LinkedModeModel model) {
			}
		});
		type(character, 0, 0);
		Assert.assertEquals(expectedExitFlags, exitFlags[0]);
	}

	private LinkedModeModel assertModel(boolean nested) {
		LinkedModeModel model = LinkedModeModel.getModel(fDocument, 0); // offset
																		// does
																		// not
																		// matter
		Assert.assertNotNull(model);
		Assert.assertEquals(nested, model.isNested());
		return model;
	}

	/**
	 * Type a character into the styled text.
	 * 
	 * @param character
	 *            the character to type
	 * @param keyCode
	 *            the key code
	 * @param stateMask
	 *            the state mask
	 */
	private void type(char character, int keyCode, int stateMask) {
		Event event = new Event();
		event.character = character;
		event.keyCode = keyCode;
		event.stateMask = stateMask;
		fAccessor.invoke("handleKeyDown", new Object[] { event });

		DisplayHelper.sleep(EditorTestHelper.getActiveDisplay(), 200);
	}

	protected int getCaret() {
		return ((ITextSelection) getEditor().getSelectionProvider()
				.getSelection()).getOffset();
	}

	protected void setCaret(int offset) {
		getEditor().getSelectionProvider().setSelection(
				new TextSelection(offset, 0));
		int newOffset = ((ITextSelection) getEditor().getSelectionProvider()
				.getSelection()).getOffset();
		Assert.assertEquals(offset, newOffset);
	}
}
