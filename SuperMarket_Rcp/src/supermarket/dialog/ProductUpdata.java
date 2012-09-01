package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

import supermarket_object.Product;
import supermarket_object.ProductManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProductUpdata extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ProductUpdata(Shell parent, int style) {
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
		shell.setSize(271, 269);
		shell.setText("\u5546\u54C1\u7EF4\u62A4");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(126, 125, 94, 23);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(126, 79, 94, 23);

		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(126, 30, 94, 25);
		ProductManager TManager = new ProductManager();
		for (Product student : TManager.queryAll3()) {
			combo.add(student.getName());
		}

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!combo.getText().equals("")) {
					if (!text_2.getText().equals("")) {
						if (!text_1.getText().equals("")) {
							if (isInteger(text_1.getText())
									&& isFloat(text_2.getText())) {
								ProductManager PManager = new ProductManager();
								Product product = PManager.query(combo
										.getText());
								product.setPrice(Float.valueOf(text_2.getText()));
								product.setMargin(Integer.valueOf(text_1
										.getText()));
								System.out.println("价格" + product.getPrice()
										+ "库存" + product.getMargin());
								System.out.println(product.getPid());
								PManager.updata(product.getPid(), product);
								JOptionPane.showMessageDialog(null, "更新成功！");
							} else {
								JOptionPane.showMessageDialog(null, "请输入数字！",
										"提示", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "请输入商品库存！",
									"提示", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "请输入商品价格！", "提示",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择商品！", "提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(34, 175, 80, 27);
		btnNewButton.setText("\u786E\u5B9A");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setBounds(146, 175, 80, 27);
		button.setText("\u53D6\u6D88");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(48, 30, 61, 17);
		label.setText("\u5546\u54C1\u540D\u79F0");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(48, 80, 61, 17);
		label_1.setText("\u5546\u54C1\u5355\u4EF7");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(48, 126, 61, 17);
		lblNewLabel_1.setText("\u5546\u54C1\u5E93\u5B58");
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
