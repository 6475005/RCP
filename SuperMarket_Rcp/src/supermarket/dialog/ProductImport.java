package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Product;
import supermarket_object.ProductManager;
import supermarket_object.Type;
import supermarket_object.TypeManager_db;

public class ProductImport extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ProductImport(Shell parent, int style) {
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
		shell.setSize(302, 300);
		shell.setText("\u8FDB\u8D27\u754C\u9762");

		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(134, 54, 88, 25);
		TypeManager_db TManager = new TypeManager_db();
		for (Type student : TManager.queryAll()) {
			combo.add(student.getName());
		}

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(67, 57, 61, 17);
		label.setText("\u5546\u54C1\u79CD\u7C7B");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(67, 23, 61, 17);
		label_1.setText("\u5546\u54C1\u540D\u79F0");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(134, 20, 88, 23);

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(134, 100, 88, 23);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(134, 140, 88, 23);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(67, 103, 61, 17);
		label_2.setText("\u552E\u4EF7");

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(67, 143, 61, 17);
		label_3.setText("\u8FDB\u8D27\u91CF");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductManager PManager = new ProductManager();
				if (!text.getText().equals("")) {
					if (!combo.getText().equals("")) {
						if (!text_1.getText().equals("")) {
							if (!text_2.getText().equals("")) {
								if (isFloat(text_1.getText())
										&& isInteger(text_2.getText())) {
									if (!PManager.isExist(text.getText())) {
										Product product = new Product();
										TypeManager_db TM_db = new TypeManager_db();
										product.setName(text.getText());
										product.setType(TM_db.getTheType(combo
												.getText()));
										product.setPrice(Float.valueOf(text_1
												.getText()));
										product.setMargin(Integer
												.valueOf(text_2.getText()));
										PManager.save(product);
										JOptionPane.showMessageDialog(null,
												"����Ʒ��ӳɹ���", "��ϲ",
												JOptionPane.DEFAULT_OPTION);
									} else {
										JOptionPane.showMessageDialog(null,
												"����Ʒ�Ѵ��ڣ�", "����",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"�ۼۺͽ�����Ϊ���֣�", "����",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"��������Ʒ��������", "����",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "��������Ʒ�ۼۣ�",
									"����", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "��ѡ����Ʒ���࣡", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��������Ʒ���ƣ�", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(96, 203, 80, 27);
		button.setText("\u63D0\u4EA4");
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
