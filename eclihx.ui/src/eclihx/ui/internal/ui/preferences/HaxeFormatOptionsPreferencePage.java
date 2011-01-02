package eclihx.ui.internal.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclihx.core.haxe.model.CodeFormatter;
import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Preference page for setting up format options.
 */
public class HaxeFormatOptionsPreferencePage  extends PreferencePage 
		implements IWorkbenchPreferencePage {
	
	private Button bracketNewLineCheck;
	private Button insertTabsCheck;
	private Button oneOperatorOnLineCheck;
	private Button indentOnEmptyLinesCheck;
	private Text indentWidthTextField;
	
	private String BRACKET_NEW_LINE = "Should curly brackets be moved to new line";
	private String INSERT_TABS = "Use tabs for indentation";	
	private String ONE_OPERATOR_ON_LINE = "Allow only one operator on the line";
	private String INDENT_ON_EMPTY_LINES = "Make indent on empty lines";		
	private String INDENT_WIDTH = "Number of spaces in indentation";			
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */	
	@Override
	public void init(IWorkbench workbench) {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite top = new Composite(parent, SWT.LEFT);
		Composite middle = new Composite(parent, SWT.LEFT);
		// Sets the layout data for the top composite's 
		// place in its parent's layout.
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		middle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		// Sets the layout for the top composite's 
		// children to populate.
		top.setLayout(new GridLayout());
		bracketNewLineCheck = new Button(top, SWT.CHECK);
		bracketNewLineCheck.setText(BRACKET_NEW_LINE);
		insertTabsCheck = new Button(top, SWT.CHECK);
		insertTabsCheck.setText(INSERT_TABS);
		oneOperatorOnLineCheck = new Button(top, SWT.CHECK);
		oneOperatorOnLineCheck.setText(ONE_OPERATOR_ON_LINE);
		indentOnEmptyLinesCheck = new Button(top, SWT.CHECK);
		indentOnEmptyLinesCheck.setText(INDENT_ON_EMPTY_LINES);
		middle.setLayout(new GridLayout(2,false));

		indentWidthTextField = new Text(middle, SWT.SINGLE);
		indentWidthTextField.setTextLimit(1);
		(new Label(middle, SWT.CHECK)).setText(INDENT_WIDTH);
		
		initializeValues();
		bracketNewLineCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {}
				});
		insertTabsCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {}
				});		
		oneOperatorOnLineCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {}
				});
		indentOnEmptyLinesCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {}
				});
		
		return middle;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {		
		bracketNewLineCheck.setSelection(getPreferenceStore().getDefaultBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_BRACKET_NEW_LINE));
		insertTabsCheck.setSelection(getPreferenceStore().getDefaultBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS));
		oneOperatorOnLineCheck.setSelection(getPreferenceStore().getDefaultBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_ONE_OPERATOR_ON_LINE));
		indentOnEmptyLinesCheck.setSelection(getPreferenceStore().getDefaultBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_ON_EMPTY_LINES));
		indentWidthTextField.setText(getPreferenceStore().getDefaultString(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH));
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {		
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_BRACKET_NEW_LINE, bracketNewLineCheck.getSelection());
		CodeFormatter.setBracketNewLines(PreferenceConstants.getPreferenceStore().getBoolean(
				PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_BRACKET_NEW_LINE));
		store.setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS, insertTabsCheck.getSelection());
		CodeFormatter.setInsertTabs(PreferenceConstants.getPreferenceStore().getBoolean(
				PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS));
		store.setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_ONE_OPERATOR_ON_LINE, oneOperatorOnLineCheck.getSelection());
		CodeFormatter.setOneOperatorOnLine(PreferenceConstants.getPreferenceStore().getBoolean(
				PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_ONE_OPERATOR_ON_LINE));
		store.setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_ON_EMPTY_LINES, indentOnEmptyLinesCheck.getSelection());
		CodeFormatter.setIndentOnEmptyLines(PreferenceConstants.getPreferenceStore().getBoolean(
				PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_ON_EMPTY_LINES));
		store.setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH, indentWidthTextField.getText());
		CodeFormatter.setIndendWidth(PreferenceConstants.getPreferenceStore().getInt(
				PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH));			
				
		return super.performOk();
	}	
		
	private void initializeValues() {
		IPreferenceStore store = getPreferenceStore();
		bracketNewLineCheck.setSelection(store.getBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_BRACKET_NEW_LINE));
		insertTabsCheck.setSelection(store.getBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS));
		oneOperatorOnLineCheck.setSelection(store.getBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_ONE_OPERATOR_ON_LINE));
		indentOnEmptyLinesCheck.setSelection(store.getBoolean(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_ON_EMPTY_LINES));
		indentWidthTextField.setText((store.getString(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH)));
	}	
		
	/**
	 * Gets EclihX UI plug-in preference store
	 */
	@Override
	public IPreferenceStore getPreferenceStore() {		
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}
}