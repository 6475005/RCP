package supermarket.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import supermarket.dialog.ProductExport;
import supermarket.dialog.ProductImport;
import supermarket.dialog.ProductUpdata;
import supermarket.dialog.TypeManager;
import supermarket.dialog.offShelves;
import supermarket.dialog.onShelves;
import supermarket.dialog.rePassword;
import supermarket_object.Product;
import supermarket_object.ProductManager;
import supermarket_object.SalesRecord;
import supermarket_object.SalesRecordManager;
import supermarket_object.SalesRecord_Product;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Combo;

public class adminManagerView extends ViewPart {

	public static final String ID = "supermarket.views.adminManagerView"; //$NON-NLS-1$
	private Table table_1;
	private Date startDate;
	private Date endDate;

	public adminManagerView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		{
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IWorkbenchPage page = adminManagerView.this.getViewSite()
							.getPage();
					interfaceView.Timer = true;
					page.hideView(page.findView(ID));
					try {
						page.showView(mianView.ID);
						page.showView(interfaceView.ID);
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			button.setBounds(298, 417, 80, 27);
			button.setText("\u9000\u51FA\u7BA1\u7406");
		}
		{
			final Shell shl6 = this.getViewSite().getShell();
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ProductUpdata dd = new ProductUpdata(shl6, SWT.MAX
							| SWT.MIN);
					adminManagerView.this.getViewSite().getShell()
							.setEnabled(false);
					dd.open();
					adminManagerView.this.getViewSite().getShell()
							.setEnabled(true);
				}
			});
			button.setBounds(43, 23, 80, 27);
			button.setText("\u5546\u54C1\u7EF4\u62A4");
		}
		{
			Button button = new Button(container, SWT.NONE);
			final Shell shl = this.getViewSite().getShell();
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TypeManager dd = new TypeManager(shl, SWT.MAX | SWT.MIN);
					adminManagerView.this.getViewSite().getShell()
							.setEnabled(false);
					dd.open();
					adminManagerView.this.getViewSite().getShell()
							.setEnabled(true);
				}
			});
			button.setBounds(43, 68, 80, 27);
			button.setText("\u79CD\u7C7B\u7EF4\u62A4");
		}

		final TableViewer tableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setHeaderVisible(true);
		table_1.setBounds(152, 137, 332, 243);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(50);
		tblclmnNewColumn.setText("\u7F16\u53F7");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_3.setWidth(50);
		tblclmnNewColumn_3.setText("\u79CD\u7C7B");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_4 = tableViewerColumn_1.getColumn();
		tableColumn_4.setWidth(90);
		tableColumn_4.setText("\u5546\u54C1\u540D\u79F0");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_1.setWidth(90);
		tblclmnNewColumn_1.setText("\u65E5\u671F");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_2.setWidth(48);
		tblclmnNewColumn_2.setText("\u9500\u91CF");

		// 注册内容提供器
		tableViewer.setContentProvider(new IStructuredContentProvider() {
			@SuppressWarnings("rawtypes")
			public Object[] getElements(Object inputElement) {
				return ((List) inputElement).toArray();// 这里传入的数据是一张链表
			}

			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
				// TODO Auto-generated method stub

			}
		});
		// 注册内容显示器
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				SalesRecord_Product person = (SalesRecord_Product) element;
				switch (columnIndex) {
				case 0:
					return String.valueOf(person.getId()); // 第一列显示Pid
				case 1:
					return person.getType(); // 第二列显示种类
				case 2:
					return person.getName(); // 第二列显示商品名称
				case 3:
					return person.getDate().toString(); // 第三列显示日期
				case 4:
					return String.valueOf(person.getCount()); // 第三列显示销量
				}

				return null;
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			@Override
			public void addListener(ILabelProviderListener arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeListener(ILabelProviderListener arg0) {
				// TODO Auto-generated method stub

			}
		});

		final DateTime dateTime = new DateTime(container, SWT.DATE);
		dateTime.setBounds(43, 227, 93, 24);

		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 112, 64, 2);

		Label label_1 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(100, 112, 64, 2);

		final Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(43, 137, 93, 25);
		ProductManager TManager = new ProductManager();
		for (Product student : TManager.queryAll3()) {
			combo.add(student.getName());
		}

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductManager PManager = new ProductManager();
				if (!combo.getText().equals("")) {
					Product p = PManager.query(combo.getText());
					List<SalesRecord_Product> SRPlist = new ArrayList<SalesRecord_Product>();
					Set<SalesRecord> SRset = p.getSalesrecord();
					for (SalesRecord tel : SRset) {
						SalesRecord_Product SRP = new SalesRecord_Product();
						SRP.setName(p.getName());
						SRP.setCount(tel.getSr_map().get(p.getName())
								.intValue());
						SRP.setDate(tel.getDate());
						SRP.setType(p.getType().getName());
						SRP.setId(p.getPid());
						SRPlist.add(SRP);
					}
					for (int i = 0; i < SRPlist.size(); i++) {
						SalesRecord_Product SRP3 = SRPlist.get(i);
						for (int j = i + 1; j < SRPlist.size(); j++) {
							if (SRP3.getName().equals(SRPlist.get(j).getName())
									&& SRP3.getDate().equals(
											SRPlist.get(j).getDate())) {
								SRPlist.remove(j);
								SRPlist.get(i).setCount(
										SRPlist.get(i).getCount()
												+ SRPlist.get(j).getCount());
								j--;
							}
						}
					}
					tableViewer.setInput(SRPlist);
				} else {
					JOptionPane.showMessageDialog(null, "请选择商品！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(43, 176, 80, 27);
		button.setText("\u67E5\u8BE2\u9500\u91CF");

		final DateTime dateTime_1 = new DateTime(container, SWT.BORDER);
		dateTime_1.setBounds(43, 280, 93, 24);

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent e) {
				SalesRecordManager SRManager = new SalesRecordManager();
				String month = null, day = null;
				if (dateTime.getMonth() + 1 < 10) {
					month = "0" + (dateTime.getMonth() + 1);
				} else {
					month = (dateTime.getMonth() + 1) + "";
				}
				if (dateTime.getDay() < 10) {
					day = "0" + dateTime.getDay();
				}
				String date1 = dateTime.getYear() + "-" + month + "-" + day
						+ " 00:00:00";
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					startDate = df.parse(date1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String endmonth = null, endday = null;
				if (dateTime_1.getMonth() + 1 < 10) {
					endmonth = "0" + (dateTime_1.getMonth() + 1);
				} else {
					endmonth = (dateTime_1.getMonth() + 1) + "";
				}
				if (dateTime_1.getDay() + 1 < 10) {
					endday = "0" + dateTime_1.getDay();
				} else {
					endday = String.valueOf(dateTime_1.getDay());
				}
				String enddate = dateTime_1.getYear() + "-" + endmonth + "-"
						+ endday + " 00:00:00";
				SimpleDateFormat sf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					endDate = sf.parse(enddate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<SalesRecord> SRlList = SRManager.queryByDate(startDate,
						endDate);
				List<SalesRecord_Product> SRPList = new ArrayList<SalesRecord_Product>();
				for (SalesRecord student : SRlList) {
					for (Product product : student.getProducts_sr()) {
						SalesRecord_Product SRP = new SalesRecord_Product();
						SRP.setId(product.getPid());
						SRP.setName(product.getName());
						SRP.setCount(student.getSr_map().get(product.getName())
								.intValue());
						SRP.setType(product.getType().getName());
						SRP.setDate(student.getDate());
						SRPList.add(SRP);
					}
				}
				for (int i = 0; i < SRPList.size(); i++) {
					SalesRecord_Product SRP3 = SRPList.get(i);
					for (int j = i + 1; j < SRPList.size(); j++) {
						if (SRP3.getName().equals(SRPList.get(j).getName())
								&& SRP3.getDate().getYear() == SRPList.get(j)
										.getDate().getYear()
								&& SRP3.getDate().getMonth() == SRPList.get(j)
										.getDate().getMonth()
								&& SRP3.getDate().getDay() == SRPList.get(j)
										.getDate().getDay()) {
							SRPList.get(i).setCount(
									SRPList.get(i).getCount()
											+ SRPList.get(j).getCount());
							SRPList.remove(j);
							j--;
						}
					}
				}
				tableViewer.setInput(SRPList);
			}
		});
		btnNewButton.setBounds(43, 320, 80, 27);
		btnNewButton.setText("\u67E5\u8BE2\u9500\u552E\u8BB0\u5F55");

		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void widgetSelected(SelectionEvent e) {
				SalesRecordManager SRManager = new SalesRecordManager();
				String month = null, day = null;
				if (dateTime.getMonth() + 1 < 10) {
					month = "0" + (dateTime.getMonth() + 1);
				} else {
					month = (dateTime.getMonth() + 1) + "";
				}
				if (dateTime.getDay() < 10) {
					day = "0" + dateTime.getDay();
				} else {
					day = dateTime.getDay() + "";
				}
				String date1 = dateTime.getYear() + "-" + month + "-" + day
						+ " 00:00:00";
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					startDate = df.parse(date1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String endmonth = null, endday = null;
				if (dateTime_1.getMonth() + 1 < 10) {
					endmonth = "0" + (dateTime_1.getMonth() + 1);
				} else {
					endmonth = (dateTime_1.getMonth() + 1) + "";
				}
				if (dateTime_1.getDay() < 10) {
					endday = "0" + dateTime_1.getDay();
				} else {
					endday = dateTime_1.getDay() + "";
				}
				String enddate = dateTime_1.getYear() + "-" + endmonth + "-"
						+ endday + " 00:00:00";
				SimpleDateFormat sf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				try {
					endDate = sf.parse(enddate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<SalesRecord> SRlList = SRManager.queryByDate(startDate,
						endDate);
				List<SalesRecord_Product> SRPList = new ArrayList<SalesRecord_Product>();
				List<SalesRecord_Product> SRPList2 = new ArrayList<SalesRecord_Product>();
				List<SalesRecord_Product> SRPList3 = new ArrayList<SalesRecord_Product>();
				for (SalesRecord student : SRlList) {
					for (Product product : student.getProducts_sr()) {
						SalesRecord_Product SRP = new SalesRecord_Product();
						SRP.setId(product.getPid());
						SRP.setName(product.getName());
						SRP.setType(product.getType().getName());
						SRP.setCount(student.getSr_map().get(product.getName())
								.intValue());
						SRP.setDate(student.getDate());
						SRPList.add(SRP);
					}
				}
				for (int i = 0; i < SRPList.size(); i++) {
					SalesRecord_Product SRP3 = SRPList.get(i);
					for (int j = i + 1; j < SRPList.size(); j++) {
						if (SRP3.getName().equals(SRPList.get(j).getName())
								&& SRP3.getDate().getYear() == SRPList.get(j)
										.getDate().getYear()
								&& SRP3.getDate().getMonth() == SRPList.get(j)
										.getDate().getMonth()
								&& SRP3.getDate().getDay() == SRPList.get(j)
										.getDate().getDay()) {
							SRPList.get(i).setCount(
									SRPList.get(i).getCount()
											+ SRPList.get(j).getCount());
							SRPList.remove(j);
							j--;
						}
					}
				}
				SRPList2 = SRPList;
				for (int i = 0; i < SRPList2.size(); i++) {
					SalesRecord_Product SRP3 = SRPList2.get(i);
					for (int j = i + 1; j < SRPList2.size(); j++) {
						if (SRP3.getName().equals(SRPList2.get(j).getName())) {
							SRPList2.get(i).setCount(
									SRPList2.get(i).getCount()
											+ SRPList2.get(j).getCount());
							SRPList2.remove(j);
							j--;
						}
					}
				}
				List<SalesRecord_Product> SRPlist = new ArrayList<SalesRecord_Product>();
				SalesRecord_Product SRP = new SalesRecord_Product();
				SRP.setCount(0);
				for (SalesRecord_Product SRP2 : SRPList2) {
					if (SRP2.getCount() > SRP.getCount()) {
						SRP = SRP2;
					}
				}
				for (SalesRecord_Product SRP2 : SRPList2) {
					if (SRP.getCount() == SRP2.getCount()) {
						SRPlist.add(SRP2);
					}
				}
				for (SalesRecord_Product srp : SRPlist) {
					for (SalesRecord_Product srp2 : SRPList) {
						if (srp.getName().equals(srp2.getName())) {
							SRPList3.add(srp2);
						}
					}
				}

				tableViewer.setInput(SRPList3);
			}
		});
		btnNewButton_1.setBounds(43, 353, 80, 27);
		btnNewButton_1.setText("\u67E5\u8BE2\u9500\u91CF\u6700\u9AD8");

		Label label_5 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(10, 395, 64, 2);

		Label label_6 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_6.setBounds(115, 395, 64, 2);

		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBounds(75, 257, 61, 17);
		label_7.setText("\u81F3");

		Label label_8 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(220, 395, 64, 2);

		Label label_9 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_9.setBounds(319, 395, 64, 2);

		Label label_10 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_10.setBounds(420, 395, 64, 2);

		Button button_3 = new Button(container, SWT.NONE);
		final Shell shl = this.getViewSite().getShell();
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rePassword dd = new rePassword(shl, SWT.MAX | SWT.MIN);
				adminManagerView.this.getViewSite().getShell()
						.setEnabled(false);
				dd.open();
				adminManagerView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		button_3.setBounds(127, 417, 80, 27);
		button_3.setText("\u4FEE\u6539\u5BC6\u7801");

		Label label_14 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_14.setBounds(220, 112, 64, 2);

		Label label_15 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_15.setBounds(314, 112, 64, 2);

		Label label_16 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_16.setBounds(420, 112, 64, 2);

		final Shell shl2 = this.getViewSite().getShell();
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onShelves dd = new onShelves(shl2, SWT.MAX | SWT.MIN);
				adminManagerView.this.getViewSite().getShell()
						.setEnabled(false);
				dd.open();
				adminManagerView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		button_1.setBounds(204, 23, 80, 27);
		button_1.setText("\u5546\u54C1\u4E0A\u67B6");

		final Shell shl5 = this.getViewSite().getShell();
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				offShelves dd = new offShelves(shl5, SWT.MAX | SWT.MIN);
				adminManagerView.this.getViewSite().getShell()
						.setEnabled(false);
				dd.open();
				adminManagerView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		button_2.setBounds(204, 68, 80, 27);
		button_2.setText("\u5546\u54C1\u4E0B\u67B6");
		final Shell shl3 = this.getViewSite().getShell();
		Button btnNewButton_2 = new Button(container, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductImport dd = new ProductImport(shl3, SWT.MAX | SWT.MIN);
				adminManagerView.this.getViewSite().getShell()
						.setEnabled(false);
				dd.open();
				adminManagerView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		btnNewButton_2.setBounds(362, 23, 80, 27);
		btnNewButton_2.setText("\u6DFB\u52A0\u5546\u54C1");

		Button btnNewButton_3 = new Button(container, SWT.NONE);
		final Shell shl4 = this.getViewSite().getShell();
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductExport dd = new ProductExport(shl4, SWT.MAX | SWT.MIN);
				adminManagerView.this.getViewSite().getShell()
						.setEnabled(false);
				dd.open();
				adminManagerView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		btnNewButton_3.setBounds(362, 68, 80, 27);
		btnNewButton_3.setText("\u5220\u9664\u5546\u54C1");

		Label label_2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(53, 209, 64, 2);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
