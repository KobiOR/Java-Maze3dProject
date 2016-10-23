package Views.Widget;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Observable;

public abstract class DialogWindow extends Observable{

	protected Shell shell;
	protected abstract void initWidgets();
	public void start(Display display) {
		shell = new Shell(display);
		initWidgets();
		shell.open();
	}


}
