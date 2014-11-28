/*******************************************************************************
 * Copyright (c) 2014 TwelveTone LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Steven Spungin <steven@spungin.tv> - initial API and implementation
 *******************************************************************************/
package fm.core;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.swt.widgets.Shell;

/**
 * This is a stub implementation containing e4 LifeCycle annotated methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references
 * this class.
 **/
@SuppressWarnings("restriction")
public class App {

	public static IEventBroker br;
	public static IEclipseContext ctx;
	public static MApplication app;

	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
	}

	@ProcessAdditions
	void processAdditions(IEventBroker br, IEclipseContext ctx,
			MApplication application) {
		App.br = br;
		App.ctx = ctx;
		App.app = application;
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}

	public static class WindowCloseHandler implements IWindowCloseHandler {

		@Override
		public boolean close(MWindow window) {

			if (PreferenceSupplier
					.getBoolean(PreferenceSupplier.MINIMIZE_TO_TRAY)) {

				Shell shell = ((Shell) window.getWidget());
				shell.setMinimized(true);
				return false;
			}

			PreferenceSupplier.set(PreferenceSupplier.START_PERSPECTIVE,
					currentPerspective.toString());
			PreferenceSupplier.save();

			IWorkbench workbench = window.getContext().get(IWorkbench.class);
			workbench.close();
			return true;
		}
	}
}
