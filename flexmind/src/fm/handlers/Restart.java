package fm.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import fm.core.App;

public class Restart {
	@Execute
	public void execute() {
		final Shell shell = App.ctx.get(Shell.class);
		final IWorkbench workbench = App.ctx.get(IWorkbench.class);

		boolean restart = MessageDialog.openQuestion(shell,
				Strings.title("appTitle"), "Перезапустить приложение?");
		if (restart)
			workbench.restart();

	}

}