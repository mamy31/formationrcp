package com.atos.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
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

import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Text customerLabel;
	private Text rentedObjetLabel;
	private Text startDateLabel;
	private Text endDateLabel;

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group grpInformations = new Group(parent, SWT.NONE);
		grpInformations.setLayout(new GridLayout(2, false));
		grpInformations.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		grpInformations.setText("Informations");

		final Label retendObjectTitle = new Label(grpInformations, SWT.NONE);
		retendObjectTitle.setText("Objet : ");
		rentedObjetLabel = new Text(grpInformations, SWT.READ_ONLY);
		rentedObjetLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));

		Label customerTitle = new Label(grpInformations, SWT.NONE);
		customerTitle.setText("Loué à : ");
		customerLabel = new Text(grpInformations, SWT.READ_ONLY);
		customerLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));

		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));

		Label startDateTitle = new Label(grpDatesDeLocation, SWT.NONE);
		startDateTitle.setText("Du : ");
		startDateLabel = new Text(grpDatesDeLocation, SWT.READ_ONLY);
		startDateLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));

		Label endDateTitle = new Label(grpDatesDeLocation, SWT.NONE);
		endDateTitle.setText("Au : ");
		endDateLabel = new Text(grpDatesDeLocation, SWT.READ_ONLY);
		endDateLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));

		DragSource source = new DragSource(retendObjectTitle, DND.DROP_MOVE
				| DND.DROP_COPY);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		source.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = retendObjectTitle.getText();
				}
			}
		});

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	private void setRental(Rental r) {
		customerLabel.setText(r.getCustomer().getDisplayName());
		rentedObjetLabel.setText(r.getRentedObject().getName());
		startDateLabel.setText(formatDate(r.getStartDate()));
		endDateLabel.setText(formatDate(r.getEndDate()));
	}

	private String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}
	}

}
