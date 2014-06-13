package com.atos.rental.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;

public class CustomerPropertyView extends ViewPart implements
		ISelectionListener {

	private Text firstNameValue;
	private Text lastNameValue;

	public CustomerPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		site.getPage().addSelectionListener(this);
		super.init(site);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {

		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		group.setText("Customer details");

		Label lblFirstName = new Label(group, SWT.NONE);
		lblFirstName.setText("First Name :");

		firstNameValue = new Text(group, SWT.READ_ONLY);
		firstNameValue.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));

		Label lblLastName = new Label(group, SWT.NONE);
		lblLastName.setText("Last Name :");

		lastNameValue = new Text(group, SWT.READ_ONLY);
		lastNameValue.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			Customer customer = (Customer) Platform.getAdapterManager()
					.getAdapter(selected, Customer.class);
			setCustomer(customer);
		}
	}

	private void setCustomer(Customer customer) {
		if (customer != null) {
			firstNameValue.setText(customer.getFirstName());
			lastNameValue.setText(customer.getLastName());
		}
	}

}
