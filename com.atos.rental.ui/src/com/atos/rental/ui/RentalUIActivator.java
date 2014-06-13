package com.atos.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.atos.rental.ui.palettes.Palette;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements
		RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.atos.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;

	/** The map of possible color providers (read in extensions) */
	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		initPaletteManager();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());
		reg.put(IMG_AGENCY,
				ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_CUSTOMER,
				ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL,
				ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_OBJECT,
				ImageDescriptor.createFromURL(b.getEntry(IMG_OBJECT)));
	}

	private void initPaletteManager() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg
				.getConfigurationElementsFor("com.atos.rental.ui.palette")) {
			if (e.getName().equals("palette")) {
				try {
					String id = e.getAttribute("id");
					String name = e.getAttribute("name");
					IColorProvider colorProvider = (IColorProvider) e
							.createExecutableExtension("class");
					Palette palette = new Palette(id, name, colorProvider);
					paletteManager.put(id, palette);
				} catch (CoreException ce) {
					getLog().log(
							new Status(IStatus.ERROR, e
									.getNamespaceIdentifier(),
									"Unable to create the color provider.", ce));
				}
			}
		}

		// Test
		System.out.println("Palettes dispo:");
		for (Palette palette : paletteManager.values()) {
			System.out.println(palette.toString());
		}
	}

	public Map<String, Palette> getPalettes() {
		return paletteManager;
	}
}
