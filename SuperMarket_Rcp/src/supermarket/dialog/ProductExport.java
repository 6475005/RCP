package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Product;
import supermarket_object.ProductManager;
import org.eclipse.swt.widgets.Combo;

public class ProductExport extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ProductExport(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
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
		shell.setSize(250, 180);
		shell.setText("\u5220\u9664\u5546\u54C1");
		
		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(95, 46, 109, 25);
		ProductManager TManager = new ProductManager();
		for (Product student : TManager.queryAll3()) {
			combo.add(student.getName());
		}
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductManager PManager = new ProductManager();
				if(!combo.getText().equals("")){
					PManager.delete(combo.getText());
					JOptionPane.showMessageDialog(null, "…Ã∆∑…æ≥˝≥…π¶£°", "πßœ≤",
							JOptionPane.DEFAULT_OPTION);
					combo.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "«Î—°‘Ò…Ã∆∑£°", "¥ÌŒÛ",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(76, 93, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		label.setBounds(33, 46, 61, 17);
		label.setText("\u5546\u54C1\u540D\u79F0");

	}
}
