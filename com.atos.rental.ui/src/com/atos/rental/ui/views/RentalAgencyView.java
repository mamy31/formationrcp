package com.atos.rental.ui.views;

import java.util.Arrays;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;

public class RentalAgencyView extends ViewPart implements RentalUIConstants {

	private TreeViewer agencyViewer;
	private IPropertyChangeListener propertyChangeListener;

	public RentalAgencyView() {

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		propertyChangeListener = new IPropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				int i = agencyViewer.getAutoExpandLevel();
				agencyViewer.refresh();
				agencyViewer.setAutoExpandLevel(i);
			}
		};
		RentalUIActivator.getDefault().getPreferenceStore()
				.addPropertyChangeListener(propertyChangeListener);
		super.init(site);
	}

	@Override
	public void createPartControl(Composite parent) {
		agencyViewer = new TreeViewer(parent);
		agencyViewer.setContentProvider(new RentalAgencyContentProvider());
		agencyViewer.setLabelProvider(new RentalAgencyContentProvider());
		agencyViewer.setInput(Arrays.asList(RentalCoreActivator.getAgency()));
		getSite().setSelectionProvider(agencyViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore()
				.removePropertyChangeListener(propertyChangeListener);
		super.dispose();
	}
}
