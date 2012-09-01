package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import supermarket_object.Vip;
import supermarket_object.VipManager;

public class VipRePassword extends Dialog {

	protected Object result;
	protected Shell shlVip;
	private Vip vid = null;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VipRePassword(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(Vip vip) {
		createContents();
		vid = vip;
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
		shlVip.setSize(317, 256);
		shlVip.setText("Vip\u4FEE\u6539\u5BC6\u7801");
		
		Label label = new Label(shlVip, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		label.setBounds(54, 51, 91, 17);
		label.setText("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		
		text = new Text(shlVip, SWT.BORDER);
		text.setBounds(151, 50, 101, 23);
		
		Label label_1 = new Label(shlVip, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		label_1.setBounds(54, 100, 61, 17);
		label_1.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		
		text_1 = new Text(shlVip, SWT.BORDER);
		text_1.setBounds(151, 99, 101, 23);
		
		Button button = new Button(shlVip, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
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
							VipManager repas = new VipManager();
							Vip rt = new Vip();
							rt.setPassword(text.getText());
							repas.updata(vid.getVid(), rt);
							JOptionPane.showMessageDialog(null, "√‹¬Î–ﬁ∏ƒ≥…π¶£°",
									"πßœ≤", JOptionPane.DEFAULT_OPTION);
							vid = null;
							shlVip.close();
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
		button.setBounds(172, 163, 80, 27);
		button.setText("\u63D0\u4EA4");
		
		Button button_1 = new Button(shlVip, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVip.close();
			}
		});
		button_1.setBounds(54, 163, 80, 27);
		button_1.setText("\u53D6\u6D88");

	}
}
