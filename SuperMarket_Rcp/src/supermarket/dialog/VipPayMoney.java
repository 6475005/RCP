package supermarket.dialog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Product;
import supermarket_object.ProductInBag;
import supermarket_object.SalesRecord;
import supermarket_object.SalesRecordManager;
import supermarket_object.Vip;
import supermarket_object.VipManager;

public class VipPayMoney extends Dialog {

	protected Shell shlVip;
	protected boolean result = false;
	private Vip vip;
	private float vippaymoney = 0;
	private int paymoney = 0;
	private Label lblMoney;
	private Set<Product> products;
	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public VipPayMoney(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public boolean open(float f, Set<Product> product, List<ProductInBag> PIB,
			Vip vip2) {
		vip = vip2;
		vip.setExperience(vip.getExperience() + Math.round(f));
		vippaymoney = (float) (f * (10 - vip.getLevel()) * 0.1);
		String str = String.valueOf(f) + " * " + "0."
				+ String.valueOf((10 - vip.getLevel())) + "="
				+ String.valueOf(vippaymoney);
		products = product;
		int leve = ((100 + vip.getLevel() * 100) * vip.getLevel()) / 2;
		if (vip.getExperience() > leve) {
			result = true;
		}
		for (ProductInBag p : PIB) {
			map.put(p.getName(), p.getCount());
		}
		createContents();
		shlVip.open();
		shlVip.layout();
		lblMoney.setText(str);
		Display display = getParent().getDisplay();
		while (!shlVip.isDisposed()) {
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
		shlVip = new Shell(getParent(), getStyle());
		shlVip.setSize(241, 332);
		shlVip.setText("Vip\u6295\u5E01\u53E3");

		Label label = new Label(shlVip, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label.setBounds(51, 52, 61, 17);
		label.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");

		lblMoney = new Label(shlVip, SWT.NONE);
		lblMoney.setBounds(118, 52, 107, 17);

		Label label_1 = new Label(shlVip, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_1.setBounds(51, 77, 61, 17);
		label_1.setText("\u6295\u5E01\u91D1\u989D\uFF1A");

		Label lblvip = new Label(shlVip, SWT.NONE);
		lblvip.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		lblvip.setBounds(32, 21, 177, 17);
		lblvip.setText("\u60A8\u662F\uFF1A" + String.valueOf(vip.getLevel())
				+ " \u7EA7VIP,\u4EAB\u53D7 "
				+ String.valueOf((10 - vip.getLevel()))
				+ " \u6298\u4F18\u60E0!");

		final Label lblMoney_1 = new Label(shlVip, SWT.NONE);
		lblMoney_1.setBounds(118, 77, 61, 17);
		lblMoney_1.setText("0.00");

		Button btnNewButton = new Button(shlVip, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 1;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton.setBounds(32, 113, 80, 27);
		btnNewButton.setText("\u58F9\u5706");

		Button btnNewButton_1 = new Button(shlVip, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 5;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton_1.setBounds(121, 113, 80, 27);
		btnNewButton_1.setText("\u4F0D\u5706");

		Button btnNewButton_2 = new Button(shlVip, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 10;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton_2.setBounds(32, 157, 80, 27);
		btnNewButton_2.setText("\u62FE\u5706");

		Button btnNewButton_3 = new Button(shlVip, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 20;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton_3.setBounds(121, 157, 80, 27);
		btnNewButton_3.setText("\u8D30\u62FE\u5706");

		Button btnNewButton_4 = new Button(shlVip, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 50;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton_4.setBounds(32, 202, 80, 27);
		btnNewButton_4.setText("\u4F0D\u62FE\u5706");

		Button btnNewButton_5 = new Button(shlVip, SWT.NONE);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				paymoney = paymoney + 100;
				String str = paymoney + ".00";
				lblMoney_1.setText(str);
			}
		});
		btnNewButton_5.setBounds(121, 202, 80, 27);
		btnNewButton_5.setText("\u58F9\u4F70\u5706");

		Button btnNewButton_6 = new Button(shlVip, SWT.NONE);
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				float a = vippaymoney;
				float b = Float.valueOf(lblMoney_1.getText());
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
					VipManager VManager = new VipManager();
					VManager.updataExperice(vip.getVid(), vip);
					if (result) {
						VManager.updataLeve(vip.getVid(), vip);
						JOptionPane.showMessageDialog(null,
								"VipÉý¼¶ÖÁ£º" + vip.getLevel() + "¼¶!", "¹§Ï²",
								JOptionPane.DEFAULT_OPTION);
					}
					products.clear();
					map.clear();
					lblMoney.setText("0.00");
					lblMoney_1.setText("0.00");
					shlVip.close();
				} else {
					JOptionPane.showMessageDialog(null, "Í¶±Ò½ð¶î²»×ã£¡", "´íÎó",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_6.setBounds(75, 254, 80, 27);
		btnNewButton_6.setText("\u652F\u4ED8");

	}

}
