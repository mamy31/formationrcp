package com.atos.rental.ui.palettes;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;

public class RentalColorPreference extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstants {

	public RentalColorPreference() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		Map<String, Palette> palettes = RentalUIActivator.getDefault()
				.getPalettes();
		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (Palette p : palettes.values()) {
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette couleur :",
				comboValues, getFieldEditorParent()));
	}

}
