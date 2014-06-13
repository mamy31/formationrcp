package com.atos.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;
import com.atos.rental.ui.palettes.Palette;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalAgencyContentProvider extends LabelProvider implements
		ITreeContentProvider, IColorProvider, RentalUIConstants {

	public static Object[] EMPTY_RESULT = new Object[0];

	public class TNode {
		private String label;
		private RentalAgency agency;

		public TNode(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}

		Object[] getChildren() {
			if (label.equals(CUSTOMERS_NODE)) {
				return agency.getCustomers().toArray();
			} else if (label.equals(LOCATIONS_NODE)) {
				return agency.getRentals().toArray();
			} else if (label.equals(OBJECTS_TO_RENT_NODE)) {
				return agency.getObjectsToRent().toArray();
			}
			return EMPTY_RESULT;
		}

		public String getText() {
			return label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TNode other = (TNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalAgencyContentProvider getOuterType() {
			return RentalAgencyContentProvider.this;
		}

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return EMPTY_RESULT;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			Collection<TNode> c = new ArrayList<TNode>();
			RentalAgency agency = (RentalAgency) parentElement;
			c.add(new TNode(CUSTOMERS_NODE, agency));
			c.add(new TNode(LOCATIONS_NODE, agency));
			c.add(new TNode(OBJECTS_TO_RENT_NODE, agency));
			return c.toArray();
		} else if (parentElement instanceof TNode) {
			return ((TNode) parentElement).getChildren();
		}
		return EMPTY_RESULT;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof RentalAgency) {
			return true;
		} else if (element instanceof TNode) {
			return true;
		}
		return false;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getFirstName() + " "
					+ ((Customer) element).getLastName();
		} else if (element instanceof Rental) {
			return getText(((Rental) element).getRentedObject());
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		} else if (element instanceof TNode) {
			return ((TNode) element).getText();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof TNode) {
			if (((TNode) element).getText().equals(CUSTOMERS_NODE)) {
				return RentalUIActivator.getDefault().getImageRegistry()
						.get(IMG_CUSTOMER);
			} else if (((TNode) element).getText().equals(LOCATIONS_NODE)) {
				return RentalUIActivator.getDefault().getImageRegistry()
						.get(IMG_RENTAL);
			} else if (((TNode) element).getText().equals(OBJECTS_TO_RENT_NODE)) {
				return RentalUIActivator.getDefault().getImageRegistry()
						.get(IMG_OBJECT);
			}
		} else if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_AGENCY);
		}
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		IColorProvider preferenceColorProvider = getPreferenceColorProvider();
		if (preferenceColorProvider != null) {
			return preferenceColorProvider.getForeground(element);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		IColorProvider preferenceColorProvider = getPreferenceColorProvider();
		if (preferenceColorProvider != null) {
			return preferenceColorProvider.getBackground(element);
		}
		return null;
	}

	private IColorProvider getPreferenceColorProvider() {
		String pref = RentalUIActivator.getDefault().getPreferenceStore()
				.getString(PREF_PALETTE);
		Map<String, Palette> palettes = RentalUIActivator.getDefault()
				.getPalettes();
		Palette palette = palettes.get(pref);
		if (palette != null) {
			return palette.getColorProvider();
		}
		return null;
	}
}
