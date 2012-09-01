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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import supermarket_object.VipManager;
import org.eclipse.wb.swt.ResourceManager;

public class VIPlogin extends Dialog {

	protected Shell shlVip;
	private String result = "false";
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public VIPlogin(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public String open() {
		createContents();
		shlVip.open();
		shlVip.layout();
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
		shlVip.setSize(327, 263);
		shlVip.setText("VIP\u767B\u5F55");

		text = new Text(shlVip, SWT.BORDER);
		text.setBounds(90, 134, 121, 23);

		text_1 = new Text(shlVip, SWT.PASSWORD);
		text_1.setBounds(90, 178, 121, 23);

		Button btnNewButton_1 = new Button(shlVip, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!text.getText().equals("")) {
					if (!text_1.getText().equals("")) {
						VipManager man = new VipManager();
						if (man.checkLogin(text.getText(), text_1.getText())) {
							result = text.getText();
							shlVip.close();
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
		});
		btnNewButton_1.setBounds(228, 132, 80, 27);
		btnNewButton_1.setText("\u767B\u5F55");

		Button btnNewButton = new Button(shlVip, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVip.close();
			}
		});
		btnNewButton.setBounds(228, 175, 80, 27);
		btnNewButton.setText("\u53D6\u6D88");

		Label label = new Label(shlVip, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label.setBounds(37, 133, 48, 22);
		label.setText("\u8D26\u53F7\uFF1A");

		Label label_1 = new Label(shlVip, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		label_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_1.setBounds(37, 179, 50, 25);
		label_1.setText("\u5BC6\u7801\uFF1A");

		Label lblVip = new Label(shlVip, SWT.NONE);
		lblVip.setImage(ResourceManager.getPluginImage("SuperMarket_Rcp", "icons/vip.jpg"));
		lblVip.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 15, SWT.NORMAL));
		lblVip.setBounds(0, 0, 321, 127);

	}

}
