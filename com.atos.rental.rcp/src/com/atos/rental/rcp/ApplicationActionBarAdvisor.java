package com.atos.rental.rcp;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction preferences;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	preferences = ActionFactory.PREFERENCES.create(window);
    	register(preferences);    	
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager menuManager = new MenuManager("&Window", IWorkbenchActionConstants.WINDOW_EXT);
    	menuBar.add(menuManager);
    	
    	menuManager.add(preferences);
    }
    
}
