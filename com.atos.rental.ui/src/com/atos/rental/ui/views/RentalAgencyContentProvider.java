package com.atos.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalAgencyContentProvider extends LabelProvider implements
		ITreeContentProvider, IColorProvider, RentalUIConstants {

	public static Object[] EMPTY_RESULT = new Object[0];

	class TNode {
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

		String getText() {
			return label;
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
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		} else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		} else if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof TNode) {
			if (((TNode) element).getText().equals(CUSTOMERS_NODE)) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
			} else if (((TNode) element).getText().equals(LOCATIONS_NODE)) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			} else if (((TNode) element).getText().equals(OBJECTS_TO_RENT_NODE)) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
			}
		}
		return null;
	}
}
