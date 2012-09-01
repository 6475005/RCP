package supermarket.dialog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import supermarket_object.Product;
import supermarket_object.ProductInBag;
import supermarket_object.SalesRecord;
import supermarket_object.SalesRecordManager;

public class payMoney extends Dialog {

	protected boolean result=false;
	protected Shell shell;
	private int paymoney = 0;
	private Label label_2;
	private Set<Product> products;
	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public payMoney(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public boolean open(float f, Set<Product> product, List<ProductInBag> PIB) {
		createContents();
		shell.open();
		shell.layout();
		label_2.setText(String.valueOf(f));
		products = product;
		for (ProductInBag p : PIB) {
			map.put(p.getName(), p.getCount());
		}
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
		shell.setSize(243, 300);
		shell.setText("\u6295\u5E01\u53E3");

		label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(148, 23, 61, 17);

		final Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(148, 59, 61, 17);
		label_3.setText("0.00");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 1;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button.setBounds(26, 96, 80, 27);
		button.setText("\u58F9\u5706");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 5;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button_1.setBounds(129, 96, 80, 27);
		button_1.setText("\u4F0D\u5706");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 10;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button_2.setBounds(26, 141, 80, 27);
		button_2.setText("\u62FE\u5706");

		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 20;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button_3.setBounds(129, 141, 80, 27);
		button_3.setText("\u8D30\u62FE\u5706");

		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 50;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button_4.setBounds(26, 189, 80, 27);
		button_4.setText("\u4F0D\u62FE\u5706");

		Button button_5 = new Button(shell, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 100;
				String str = paymoney + ".00";
				label_3.setText(str);
			}
		});
		button_5.setBounds(129, 189, 80, 27);
		button_5.setText("\u58F9\u4F70\u5706");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label.setBounds(65, 21, 61, 17);
		label.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_1.setBounds(65, 57, 61, 17);
		label_1.setText("\u6295\u5E01\u91D1\u989D\uFF1A");

		Button button_6 = new Button(shell, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				float a = Float.valueOf(label_2.getText());
				float b = Float.valueOf(label_3.getText());
				if (a <= b) {
					SalesRecordManager SRManager = new SalesRecordManager();
					SalesRecord SRecord = new SalesRecord();
					Date date = new Date();
					SRecord.setDate(date);
					SRecord.setProducts_sr(products);
					SRecord.setSr_map(map);
					SRManager.save(SRecord);
					JOptionPane.showMessageDialog(null,
							"Ö§¸¶Íê±Ï£¬ÕÒÁã£º" + String.valueOf(b - a) + "Ôª£¡", "¹§Ï²",
							JOptionPane.DEFAULT_OPTION);
					result = true;
					products.clear();
					map.clear();
					label_2.setText("0.00");
					shell.close();
				} else {
					JOptionPane.showMessageDialog(null, "Í¶±Ò½ð¶î²»×ã£¡", "´íÎó",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_6.setBounds(76, 235, 80, 27);
		button_6.setText("\u652F\u4ED8");

	}

}
