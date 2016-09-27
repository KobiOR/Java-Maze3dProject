package Views.Widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import static org.eclipse.swt.SWT.COLOR_LIST_FOREGROUND;

public class GenerateMazeWindow extends DialogWindow {

	String CurrentmazeName;
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
		/*********************MazeBuildAlgo********************************/
		//radio
		Button[] radioButtons = new Button[3];
		radioButtons[0] = new Button(shell, SWT.RADIO);
		radioButtons[0].setSelection(true);
		radioButtons[0].setText("Growing Tree");
		radioButtons[0].setToolTipText(new String("Growin_Tree"));
		radioButtons[0].setLocation(50,250);
		radioButtons[0].pack();

		radioButtons[1] = new Button(shell, SWT.RADIO);
		radioButtons[1].setText("Simple Builder");
		radioButtons[1].setToolTipText(new String("Simple_Builder"));
		radioButtons[1].setLocation(120,250);
		radioButtons[1].pack();

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
				String algo = null;
				msg.setMessage("Generating maze with height : " + mHeight + " floor height: " + fHeight + " and floor width: "+ fWidth);msg.open();

				for (int i = 0; i < radioButtons.length; i++) {
					if (radioButtons[i].getSelection()){
						algo=radioButtons[i].getToolTipText();break;}

				}
				CurrentmazeName=new String(mazeNameext);
				setChanged();
				notifyObservers("generate_maze "+mazeNameext+" "+ mHeight+ " "+fHeight+ " "+ fWidth +" "+algo);
				shell.close();

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
