package supermarket_rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import supermarket.views.interfaceView;
import supermarket.views.mianView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		layout.addView(mianView.ID, 1, 0.66f, layout.getEditorArea());
		layout.addView(interfaceView.ID, 1, 0.34f, layout.getEditorArea());
	}
}
