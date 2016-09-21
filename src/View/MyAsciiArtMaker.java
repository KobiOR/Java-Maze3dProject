package View;

import Presenters.Presenter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.FileReader;


public class MyAsciiArtMaker extends BasicWindow implements View {

    Text asciiText;
    String fileName;
    private Presenter p;
    public MyAsciiArtMaker(String title, int width, int height) {
        super(title, width, height);
    }
    @Override
    public void initWidgets() {


        shell.setLayout(new GridLayout(2, false));

        Button openButton = new Button(shell, SWT.PUSH);
        openButton.setText("open image file");
        openButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));

        openButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                FileDialog fd = new FileDialog(shell, SWT.OPEN);
                fd.setText("open");
                fd.setFilterPath("");
                String[] filterExt = {"*.gif", "*.jpg", "*.png", "*.bmp"};
                fd.setFilterExtensions(filterExt);
                fileName = fd.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
            }
        });


        asciiText = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        asciiText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));

        Button converButton = new Button(shell, SWT.PUSH);
        converButton.setText("convert to ASCII art");
        converButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));

        converButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
            }
        });

        Button changeFont = new Button(shell, SWT.PUSH);
        changeFont.setText("change font");
        changeFont.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));

        changeFont.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                FontDialog fd = new FontDialog(shell);
                fd.setText("select font");
                FontData fdata = fd.open();
                if (fdata != null)
                    asciiText.setFont(new Font(display, fdata));
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
            }
        });
    }
    public void display(String fileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            asciiText.setText(""); // clears the text
            while ((line = in.readLine()) != null)
                asciiText.append(line + "\n");
            in.close();
        } catch (Exception e) {
        }
    }
    @Override
    public void display(int[][] maze3d) {

    }
    public void start() {
        run();
    }
    @Override
    public void setCli(Cli c) {

    }
    @Override
    public int getUserCommand() {
        return 0;
    }
    public void setController(Presenter p) {
        this.p = p;
    }
}
