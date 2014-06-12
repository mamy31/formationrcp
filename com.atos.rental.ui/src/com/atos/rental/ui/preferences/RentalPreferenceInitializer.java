package com.atos.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;

/**
 * Class used to initialize default preference values.
 */
public class RentalPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE).getRGB()));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN).getRGB()));
		store.setDefault(PREF_OBJECT_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_RED).getRGB()));
	}
}
