package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Vip;
import supermarket_object.VipManager;

public class VipDelete extends Dialog {

	protected boolean result=false;
	protected Shell shlVip;
	private Vip vip = null;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VipDelete(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open(Vip vip2) {
		createContents();
		vip = vip2;
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
		shlVip.setSize(284, 244);
		shlVip.setText("Vip\u8D26\u6237\u6CE8\u9500");
		
		Label lblNewLabel = new Label(shlVip, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		lblNewLabel.setBounds(62, 47, 39, 17);
		lblNewLabel.setText("\u5BC6\u7801\uFF1A");
		
		text = new Text(shlVip, SWT.PASSWORD);
		text.setBounds(110, 46, 114, 23);
		
		Label lblNewLabel_1 = new Label(shlVip, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(33, 93, 68, 17);
		lblNewLabel_1.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		text_1 = new Text(shlVip, SWT.BORDER);
		text_1.setBounds(110, 92, 114, 23);
		
		Button btnNewButton = new Button(shlVip, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!text.getText().equals("")){
					if(!text_1.getText().equals("")){
						VipManager VManager = new VipManager();
						Vip temp = VManager.query(vip.getVid());
						if(temp.getPassword().equals(text.getText())){
							if(temp.getPhone().equals(text_1.getText())){
								VManager.delete(temp.getVid());
								JOptionPane.showMessageDialog(null, "◊¢œ˙ÕÍ≥…£°", "Ã· æ", JOptionPane.DEFAULT_OPTION);
								result = true; 
								shlVip.close();
							}else{
								JOptionPane.showMessageDialog(null, "µÁª∞¥ÌŒÛ£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "√‹¬Î¥ÌŒÛ£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "«Î ‰»Î¡™œµµÁª∞£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "«Î ‰»Î√‹¬Î£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(166, 153, 80, 27);
		btnNewButton.setText("\u786E\u8BA4");
		
		Button button = new Button(shlVip, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVip.close();
			}
		});
		button.setBounds(33, 153, 80, 27);
		button.setText("\u53D6\u6D88");

	}

}
