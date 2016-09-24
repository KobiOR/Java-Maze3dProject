package Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class GenerateMazeWindow extends DialogWindow {
	
	@Override
	protected void initWidgets() {
		shell.setText("Generate maze window");
		shell.setSize(350, 400);
		shell.setLayout(new GridLayout(2, false));

		/*********************MazeNameField********************************/
		Label mazeName = new Label(shell, SWT.NONE);
		mazeName.setText("Maze name:");
		Text mazeNameText = new Text(shell, SWT.BORDER);
		mazeNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		/*********************MazeHeighteField********************************/
		Label mHeight = new Label(shell, SWT.NONE);
		mHeight.setText("Maze height:");
		Text mHeightText = new Text(shell, SWT.BORDER);
		mHeightText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		/*********************MazeFloorHeightField********************************/
		Label fHeight = new Label(shell, SWT.NONE);
		fHeight.setText("Maze floor height:");
		Text fHeightText = new Text(shell, SWT.BORDER);
		fHeightText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		/*********************MazeFloorWidthField********************************/
		Label fWidth = new Label(shell, SWT.NONE);
		fWidth.setText("Maze floor width:");
		Text fWidthText = new Text(shell, SWT.BORDER);
		fWidthText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Generate maze");
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Title");
				int mHeight = Integer.parseInt(mHeightText.getText());
				int fHeight = Integer.parseInt(fHeightText.getText());
				int fWidth = Integer.parseInt(fWidthText.getText());
				String mazeNameext=mazeNameText.getText();
				msg.setMessage("Generating maze with height : " + mHeight + " floor height: " + fHeight + " and floor width: "+ fWidth);
				setChanged();notifyObservers("Generate_maze "+mazeNameext+ mHeight+ " "+fHeight+ " "+ fWidth);
				msg.open();
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
