package com.atos.rental.ui.palettes;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;
import com.atos.rental.ui.views.RentalAgencyContentProvider.TNode;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultColorProvider implements IColorProvider, RentalUIConstants {

	public DefaultColorProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return getPreferenceColor(PREF_CUSTOMER_COLOR);
		} else if (element instanceof RentalObject) {
			return getPreferenceColor(PREF_OBJECT_COLOR);
		} else if (element instanceof Rental) {
			return getPreferenceColor(PREF_RENTAL_COLOR);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof TNode) {
			if (((TNode) element).getText().equals(CUSTOMERS_NODE)) {
				return getPreferenceColor(PREF_CUSTOMER_COLOR);
			} else if (((TNode) element).getText().equals(LOCATIONS_NODE)) {
				return getPreferenceColor(PREF_RENTAL_COLOR);
			} else if (((TNode) element).getText().equals(OBJECTS_TO_RENT_NODE)) {
				return getPreferenceColor(PREF_OBJECT_COLOR);
			}
		}
		return null;
	}

	private Color getPreferenceColor(String idPreferenceColor) {
		String rgbKey = RentalUIActivator.getDefault().getPreferenceStore()
				.getString(idPreferenceColor);
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if (col == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}

}
