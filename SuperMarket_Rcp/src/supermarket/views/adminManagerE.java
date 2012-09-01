package supermarket.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class adminManagerE extends EditorPart implements IEditorInput {

	public static final String ID = "supermarket.views.adminManagerE"; //$NON-NLS-1$
	private Text text;

	public adminManagerE() {
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IWorkbenchPage page = adminManagerE.this.getEditorSite()
						.getPage();
				/*
				IWorkbenchPart part = adminManagerE.this.getEditorSite()
						.getPart();
				IEditorReference reference = (IEditorReference) page
						.getReference(part);
				page.hideEditor(reference);
				*/
				IEditorPart ipart = (IEditorPart) adminManagerE.this.getEditorSite().getPart();
				page.closeEditor(ipart, true);
				try {
					page.openEditor(new interFaceEditor(), interFaceEditor.ID);
					//page.showView(new interFaceView().ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		button.setBounds(44, 424, 80, 27);
		button.setText("\u9000\u51FA\u7BA1\u7406");

		Button button_1 = new Button(container, SWT.NONE);
		button_1.setBounds(44, 23, 80, 27);
		button_1.setText("\u6DFB\u52A0\u5546\u54C1");

		Button button_2 = new Button(container, SWT.NONE);
		button_2.setBounds(44, 64, 80, 27);
		button_2.setText("\u5220\u9664\u5546\u54C1");

		DateTime dateTime = new DateTime(container, SWT.BORDER);
		dateTime.setBounds(44, 250, 93, 24);

		DateTime dateTime_1 = new DateTime(container, SWT.BORDER);
		dateTime_1.setBounds(44, 303, 93, 24);

		Label label = new Label(container, SWT.NONE);
		label.setBounds(84, 280, 12, 17);
		label.setText("\u81F3");

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(44, 366, 80, 27);
		btnNewButton.setText("\u67E5\u8BE2\u9500\u91CF\u6700\u9AD8");

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_1.setBounds(10, 147, 64, 24);
		label_1.setText("\u5546\u54C1\u540D\u79F0");

		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.setBounds(44, 187, 80, 27);
		btnNewButton_1.setText("\u67E5\u8BE2\u9500\u91CF");

		text = new Text(container, SWT.BORDER);
		text.setText("");
		text.setBounds(84, 148, 73, 23);

		Button button_3 = new Button(container, SWT.NONE);
		button_3.setBounds(44, 333, 80, 27);
		button_3.setText("\u67E5\u8BE2\u9500\u552E\u8BB0\u5F55");

		Label label_2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(10, 111, 64, 2);

		Label label_3 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(100, 111, 64, 2);

		Label label_4 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(10, 231, 64, 2);

		Label label_5 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(100, 231, 64, 2);

		Label label_6 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_6.setBounds(100, 410, 64, 2);

		Label label_7 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setBounds(10, 410, 64, 2);

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		// Initialize the editor part
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "111";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "111";
	}

}
