package com.atos.rental.cmd.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CopyCustomerHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public CopyCustomerHandler() {

	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) currentSelection;
			for (Iterator<?> it = selection.iterator(); it.hasNext();) {
				Object selected = it.next();
				if (selected instanceof Customer) {
					Clipboard clipboard = new Clipboard(Display.getCurrent());
					String textData = ((Customer) selected).getFirstName()
							+ " " + ((Customer) selected).getLastName();
					TextTransfer textTransfer = TextTransfer.getInstance();
					String rtfData = "{\\rtf1\\b\\i " + textData + "}";
					RTFTransfer rtfTransfer = RTFTransfer.getInstance();
					ImageData imageData = RentalUIActivator.getDefault()
							.getImageRegistry()
							.get(RentalUIConstants.IMG_CUSTOMER).getImageData();
					ImageTransfer imageTransfer = ImageTransfer.getInstance();
					Transfer[] transfers = new Transfer[] { textTransfer,
							rtfTransfer, imageTransfer };
					Object[] data = new Object[] { textData, rtfData, imageData };
					clipboard.setContents(data, transfers);
					clipboard.dispose();
				}
			}
		}
		return null;
	}
}
