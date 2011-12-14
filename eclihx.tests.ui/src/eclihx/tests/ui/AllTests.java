package eclihx.tests.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	PerspectiveTest.class, 
	eclihx.tests.ui.internal.ui.editors.hx.AllTests.class,
	eclihx.tests.ui.internal.ui.views.AllTests.class,
	eclihx.tests.ui.wizards.AllTests.class } )
public class AllTests {

}
