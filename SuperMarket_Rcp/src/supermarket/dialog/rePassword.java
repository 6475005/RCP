package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.root;
import supermarket_object.rootManager;

public class rePassword extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public rePassword(Shell parent, int style) {
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
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(320, 200);
		shell.setText("\u4FEE\u6539\u5BC6\u7801");

		text = new Text(shell, SWT.PASSWORD);
		text.setBounds(130, 19, 114, 23);

		text_1 = new Text(shell, SWT.PASSWORD);
		text_1.setBounds(130, 71, 114, 23);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!text.getText().equals("")) {
					if (!text_1.getText().equals("")) {
						if (!text.getText().equals(text_1.getText())) {
							JOptionPane.showMessageDialog(null, "¡Ω¥Œ ‰»Î√‹¬Î≤ª“ª÷¬£°",
									"¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
							text.setText("");
							text_1.setText("");
						} else {
							rootManager repas = new rootManager();
							root rt = new root();
							rt.setPassword(text.getText());
							repas.updata(1, rt);
							JOptionPane.showMessageDialog(null, "√‹¬Î–ﬁ∏ƒ≥…π¶£°",
									"πßœ≤", JOptionPane.DEFAULT_OPTION);
							shell.close();
						}
					} else {
						JOptionPane.showMessageDialog(null, "«Î ‰»Î»∑»œ√‹¬Î£°", "¥ÌŒÛ",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "«Î ‰»Î–¬√‹¬Î£°", "¥ÌŒÛ",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(44, 120, 80, 27);
		btnNewButton.setText("\u63D0\u4EA4");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setBounds(175, 120, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		lblNewLabel.setBounds(44, 20, 80, 17);
		lblNewLabel.setText("\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(59, 72, 65, 17);
		lblNewLabel_1.setText("\u518D\u6B21\u8F93\u5165\uFF1A");

	}

}
