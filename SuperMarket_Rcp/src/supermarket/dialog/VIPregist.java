package supermarket.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Vip;
import supermarket_object.VipManager;

public class VIPregist extends Dialog {

	protected Object result;
	protected Shell shlVip;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public VIPregist(Shell parent, int style) {
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
		shlVip.setSize(422, 440);
		shlVip.setText("VIP\u6CE8\u518C");

		text = new Text(shlVip, SWT.BORDER);
		text.setBounds(162, 95, 107, 23);

		final DateTime dateTime = new DateTime(shlVip, SWT.BORDER);
		dateTime.setBounds(162, 297, 107, 24);

		Label label = new Label(shlVip, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label.setBounds(66, 96, 61, 17);
		label.setText("\u767B\u5F55\u8D26\u53F7\uFF1A");

		text_1 = new Text(shlVip, SWT.PASSWORD);
		text_1.setBounds(162, 129, 107, 23);

		Label label_1 = new Label(shlVip, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_1.setBounds(66, 130, 61, 17);
		label_1.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");

		Label label_2 = new Label(shlVip, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_2.setBounds(66, 297, 61, 17);
		label_2.setText("\u51FA\u751F\u65E5\u671F");

		text_2 = new Text(shlVip, SWT.BORDER);
		text_2.setBounds(162, 169, 107, 23);

		Label label_3 = new Label(shlVip, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_3.setBounds(66, 170, 61, 17);
		label_3.setText("\u59D3\u540D\uFF1A");

		text_3 = new Text(shlVip, SWT.BORDER);
		text_3.setBounds(162, 211, 107, 23);

		Label lblEmails = new Label(shlVip, SWT.NONE);
		lblEmails.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		lblEmails.setBounds(66, 212, 61, 17);
		lblEmails.setText("emails\uFF1A");

		Label label_4 = new Label(shlVip, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		label_4.setBounds(66, 257, 61, 17);
		label_4.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");

		text_4 = new Text(shlVip, SWT.BORDER);
		text_4.setBounds(162, 256, 107, 23);

		Button button = new Button(shlVip, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!text.getText().equals("") && text.getText().length() <= 20
						&& text.getText().length() >= 6) {
					if (!text_1.getText().equals("")
							&& text_1.getText().length() <= 20
							&& text_1.getText().length() >= 6) {
						if (!text_2.getText().equals("")) {
							if (!text_3.getText().equals("")) {
								if (!text_4.getText().equals("")
										&& isPhoneNumber(text_4.getText())) {
									VipManager VManager = new VipManager();
									if (!VManager.isExist(text.getText())) {
										Vip vip = new Vip();
										vip.setUsername(text.getText());
										vip.setPassword(text_1.getText());
										vip.setName(text_2.getText());
										vip.setEmails(text_3.getText());
										vip.setPhone(text_4.getText());
										String month = null, day = null;
										if (dateTime.getMonth() + 1 < 10) {
											month = "0"
													+ (dateTime.getMonth() + 1);
										} else {
											month = (dateTime.getMonth() + 1)
													+ "";
										}
										if (dateTime.getDay() < 10) {
											day = "0" + dateTime.getDay();
										} else {
											day = dateTime.getDay() + "";
										}
										String date1 = dateTime.getYear() + "-"
												+ month + "-" + day;
										SimpleDateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date birthday = new Date();
										try {
											birthday = df.parse(date1);
										} catch (ParseException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										vip.setBirthday(birthday);
										VManager.save(vip);
										JOptionPane.showMessageDialog(null,
												"×¢²á³É¹¦£¡", "¹§Ï²",
												JOptionPane.DEFAULT_OPTION);
										shlVip.close();
									} else {
										JOptionPane.showMessageDialog(null,
												"¸ÃÕÊºÅÒÑ´æÔÚ£¡", "´íÎó",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"ÇëÊäÈëµç»°,ÕýÈ·¸ñÊ½£¡", "´íÎó",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"ÇëÊäÈëemails£¡", "´íÎó",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "ÇëÊäÈëÐÕÃû£¡", "´íÎó",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "ÇëÊäÈëµÇÂ¼ÃÜÂë,³¤¶È6-20Î»£¡",
								"´íÎó", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "ÇëÊäÈëµÇÂ¼ÕËºÅ,³¤¶È6-20Î»£¡",
							"´íÎó", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(92, 354, 80, 27);
		button.setText("\u63D0\u4EA4");

		Button button_1 = new Button(shlVip, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_1.setText("");
				text_2.setText("");
				text_3.setText("");
				text_4.setText("");
			}
		});
		button_1.setBounds(260, 354, 80, 27);
		button_1.setText("\u91CD\u7F6E");

		Label lblVip = new Label(shlVip, SWT.NONE);
		lblVip.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 18, SWT.NORMAL));
		lblVip.setBounds(139, 30, 161, 43);
		lblVip.setText("VIP\u7528\u6237\u6CE8\u518C");

		Label lblNewLabel = new Label(shlVip, SWT.NONE);
		lblNewLabel.setBounds(295, 175, 82, 17);
		lblNewLabel.setText("\uFF082-5\u4E2A\u6C49\u5B57\uFF09");

		Label lblNewLabel_1 = new Label(shlVip, SWT.NONE);
		lblNewLabel_1.setBounds(295, 101, 94, 17);
		lblNewLabel_1.setText("6-20\u6570\u5B57\u6216\u5B57\u6BCD");

		Label label_5 = new Label(shlVip, SWT.NONE);
		label_5.setBounds(295, 135, 94, 17);
		label_5.setText("6-20\u6570\u5B57\u6216\u5B57\u6BCD");

		Label lblXxxxxxcom = new Label(shlVip, SWT.NONE);
		lblXxxxxxcom.setBounds(295, 211, 94, 17);
		lblXxxxxxcom.setText("XXX@XXX.com");
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isInteger(char value) {
		try {
			Integer.parseInt(String.valueOf(value));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isPhoneNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!isInteger(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
