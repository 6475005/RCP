package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

import supermarket_object.Product;
import supermarket_object.ProductManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class offShelves extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public offShelves(Shell parent, int style) {
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
		shell.setSize(228, 209);
		shell.setText("\u5546\u54C1\u4E0B\u67B6");

		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(95, 51, 104, 25);
		ProductManager TManager = new ProductManager();
		for (Product student : TManager.queryAll()) {
			combo.add(student.getName());
		}

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!combo.getText().equals("")) {
					ProductManager PManager = new ProductManager();
					PManager.updataState(combo.getText());
					JOptionPane.showMessageDialog(null, "²Ù×÷³É¹¦!", "¹§Ï²",
							JOptionPane.DEFAULT_OPTION);
					shell.close();
				} else {
					JOptionPane.showMessageDialog(null, "ÇëÑ¡ÔñÉÌÆ·!", "´íÎó",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(69, 105, 80, 27);
		button.setText("\u786E\u5B9A");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label.setBounds(28, 52, 61, 17);
		label.setText("\u5546\u54C1\u540D\u79F0");

	}

}
