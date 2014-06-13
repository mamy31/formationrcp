package com.atos.test.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.atos.rental.ui.RentalAgencyView", IPageLayout.LEFT, 0.57f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.atos.rental.ui.CustomerPropertyView", IPageLayout.BOTTOM, 0.35f, "com.atos.rental.ui.RentalAgencyView");
		layout.addView("com.atos.rental.ui.RentalPropertyView", IPageLayout.TOP, 0.5f, "com.atos.rental.ui.CustomerPropertyView");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
