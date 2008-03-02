package eclihx.ui.internal.ui.views;

import java.awt.Composite;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.eclipse.ui.views.navigator.ResourceNavigator;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.INavigatorContentService;

public class PackageExplorerView extends CommonNavigator {

	/** the package explorer view id. */
    public static final String PACKAGE_EXPLORER_ID = "eclihx.ui.internal.ui.views.PackageExplorerView"; //$NON-NLS-1$
    
    @Override
    public INavigatorContentService getNavigatorContentService() {
    	// TODO Auto-generated method stub
    	return super.getNavigatorContentService();
    }
}
