package com.atos.rental.ui.views;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;

public class RentalAgencyView extends ViewPart {

	private TreeViewer agencyViewer;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
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
	
	

}
