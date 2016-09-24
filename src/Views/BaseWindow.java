package Views;

import Presenters.Presenter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public abstract class BaseWindow extends Observable implements Observer,View {
	protected Display display;
	protected Shell shell;

	protected abstract void initWidgets();
	public void start() throws IOException {
		display = new Display();
		shell = new Shell(display);
		
		initWidgets();
		shell.open();		
		
		// main event loop
		while(!shell.isDisposed()){ // window isn't closed
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}
}
