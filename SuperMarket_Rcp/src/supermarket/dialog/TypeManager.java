package supermarket.dialog;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket_object.Type;
import supermarket_object.TypeManager_db;
import org.eclipse.swt.widgets.Combo;

public class TypeManager extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Combo combo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TypeManager(Shell parent, int style) {
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
		shell.setSize(309, 211);
		shell.setText("\u79CD\u7C7B\u7EF4\u62A4");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(158, 53, 100, 23);
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(158, 105, 100, 25);
		TypeManager_db TManager = new TypeManager_db();
		for (Type student : TManager.queryAll()) {
			combo.add(student.getName());
		}
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!text.getText().equals("")){
				TypeManager_db TManager = new TypeManager_db();
				Type type = new Type();
				type.setName(text.getText());
				if(!TManager.isExist(text.getText())){
				TManager.save(type);
				JOptionPane.showMessageDialog(null, "种类添加成功！", "恭喜",
						JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "种类已存在！", "提示",
							JOptionPane.ERROR_MESSAGE);
				}
				}else{
					JOptionPane.showMessageDialog(null, "请输入种类！", "提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(39, 49, 80, 27);
		btnNewButton.setText("\u6DFB\u52A0\u79CD\u7C7B");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TypeManager_db TManager = new TypeManager_db();
				if(!combo.getText().equals("")){
					int Tid = TManager.getTheType(combo.getText()).getTid();
					TManager.delete(Tid);
					JOptionPane.showMessageDialog(null, "种类删除成功！", "恭喜",
							JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "请选择种类！", "提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(39, 103, 80, 27);
		btnNewButton_1.setText("\u5220\u9664\u79CD\u7C7B");
		

	}

}
