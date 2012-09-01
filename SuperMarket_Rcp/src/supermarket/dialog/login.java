package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import supermarket_object.rootManager;
import org.eclipse.wb.swt.ResourceManager;

public class login extends Dialog {

	protected Object result = false;
	protected Shell shlJ;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public login(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlJ.open();
		shlJ.layout();
		Display display = getParent().getDisplay();
		while (!shlJ.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlJ = new Shell(getParent(), getStyle());
		shlJ.setSize(320, 250);
		shlJ.setText("\u767B\u5F55");

		text = new Text(shlJ, SWT.BORDER);
		text.setBounds(78, 125, 130, 23);

		text_1 = new Text(shlJ, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(78, 160, 130, 23);

		Button btnNewButton = new Button(shlJ, SWT.NONE);
		btnNewButton.setBounds(214, 123, 80, 27);
		btnNewButton.setText("\u767B\u5F55");
		btnNewButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (!text.getText().equals("")) {
					if (!text_1.getText().equals("")) {
						rootManager man = new rootManager();
						if (man.checkLogin(text.getText(), text_1.getText())) {
							result = true;
							shlJ.close();
						} else {
							JOptionPane.showMessageDialog(null, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°",
									"¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
							text.setText("");
							text_1.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "«Î ‰»Î√‹¬Î£°", "¥ÌŒÛ",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "«Î ‰»Î’À∫≈£°", "¥ÌŒÛ",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		Button btnNewButton_1 = new Button(shlJ, SWT.NONE);
		btnNewButton_1.setBounds(214, 158, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		btnNewButton_1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				shlJ.close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		Label label = new Label(shlJ, SWT.NONE);
		label.setImage(ResourceManager.getPluginImage("SuperMarket_Rcp", "icons/admin.jpg"));
		label.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 22, SWT.NORMAL));
		label.setBounds(0, 0, 314, 222);

	}

}
