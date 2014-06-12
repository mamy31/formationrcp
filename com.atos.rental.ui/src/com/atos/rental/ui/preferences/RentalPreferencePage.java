package com.atos.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;

public class RentalPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstants {

	public RentalPreferencePage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	public RentalPreferencePage(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public RentalPreferencePage(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public RentalPreferencePage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, PREF_CUSTOMER_COLOR,
				getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, PREF_RENTAL_COLOR,
				getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_OBJECT_COLOR, PREF_OBJECT_COLOR,
				getFieldEditorParent()));
	}

}
