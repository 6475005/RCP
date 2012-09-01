package supermarket.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import supermarket.dialog.payMoney;
import supermarket_object.Product;
import supermarket_object.ProductInBag;
import supermarket_object.ProductManager;
import supermarket_object.Type;
import supermarket_object.TypeManager_db;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;

public class mianView extends ViewPart {

	public static final String ID = "supermarket.views.mianView"; //$NON-NLS-1$
	public static boolean state = true;
	private Table table_2;
	private Table table;
	private Product p;
	private float money = 0.0f;
	private List<Product> productsOnShalves = new ArrayList<Product>();
	private Set<Product> products = new HashSet<Product>();
	private Stack<Product> Bag = new Stack<Product>();
	private List<ProductInBag> productsInBag = new ArrayList<ProductInBag>();

	public mianView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		final TableViewer tableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_2 = tableViewer.getTable();
		table_2.setHeaderVisible(true);
		table_2.setBounds(10, 69, 312, 171);

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_10 = tableViewerColumn_2.getColumn();
		tableColumn_10.setWidth(40);
		tableColumn_10.setText("\u7F16\u53F7");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_9 = tableViewerColumn_1.getColumn();
		tableColumn_9.setWidth(60);
		tableColumn_9.setText("\u79CD\u7C7B");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_8 = tableViewerColumn.getColumn();
		tableColumn_8.setWidth(90);
		tableColumn_8.setText("\u5546\u54C1\u540D\u79F0");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_11 = tableViewerColumn_3.getColumn();
		tableColumn_11.setWidth(46);
		tableColumn_11.setText("\u4F59\u91CF");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_1.setWidth(72);
		tblclmnNewColumn_1.setText("\u5355\u4EF7");

		// 注册内容提供器
		tableViewer.setContentProvider(new IStructuredContentProvider() {
			@SuppressWarnings("rawtypes")
			public Object[] getElements(Object inputElement) {
				return ((List) inputElement).toArray();// 这里传入的数据是一张链表
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		});
		// 注册内容显示器
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				Product person = (Product) element;
				switch (columnIndex) {
				case 0:
					return String.valueOf(person.getPid()); // 第一列显示Pid
				case 1:
					return person.getType().getName(); // 第二列显示种类
				case 2:
					return person.getName(); // 第二列显示商品名称
				case 3:
					return String.valueOf(person.getMargin()); // 第三列显示余量
				case 4:
					return String.valueOf(person.getPrice()); // 第三列显示单价
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

		final TableViewer tableViewer_1 = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer_1.getTable();
		table.setHeaderVisible(true);
		table.setBounds(10, 269, 312, 136);

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(
				tableViewer_1, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn_5.getColumn();
		tableColumn.setWidth(40);
		tableColumn.setText("\u7F16\u53F7");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(
				tableViewer_1, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_6.getColumn();
		tableColumn_1.setWidth(90);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(
				tableViewer_1, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_7.getColumn();
		tableColumn_2.setWidth(50);
		tableColumn_2.setText("\u6570\u91CF");

		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(
				tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_9.getColumn();
		tblclmnNewColumn.setWidth(50);
		tblclmnNewColumn.setText("\u5355\u4EF7");

		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(
				tableViewer_1, SWT.NONE);
		TableColumn tableColumn_7 = tableViewerColumn_8.getColumn();
		tableColumn_7.setWidth(78);
		tableColumn_7.setText("\u91D1\u989D");

		// 注册内容提供器
		tableViewer_1.setContentProvider(new IStructuredContentProvider() {
			@SuppressWarnings("rawtypes")
			public Object[] getElements(Object inputElement) {
				return ((List) inputElement).toArray();// 这里传入的数据是一张链表
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		});
		// 注册内容显示器
		tableViewer_1.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				ProductInBag person = (ProductInBag) element;
				switch (columnIndex) {
				case 0:
					return String.valueOf(person.getId()); // 第一列显示Pid
				case 1:
					return person.getName(); // 第二列显示种类
				case 2:
					return String.valueOf(person.getCount()); // 第二列显示数量
				case 3:
					return String.valueOf(person.getPrice()); // 第三列显示单价
				case 4:
					return String.valueOf(person.getCount() * person.getPrice()); // 第三列显示金额
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

		tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent arg0) {
				// TODO Auto-generated method stub
				IStructuredSelection iss = (IStructuredSelection) arg0
						.getSelection();
				// 得到记录的实体对象（要类型转换）
				p = (Product) iss.getFirstElement();
				if (p.getMargin() != 0) {
					for (int i = 0; i < productsOnShalves.size(); i++) {
						if (productsOnShalves.get(i).equals(p)) {
							productsOnShalves.get(i).setMargin(
									productsOnShalves.get(i).getMargin() - 1);
							break;
						}
					}
					ProductManager PManager = new ProductManager();
					PManager.updata(p.getPid(), p);
					Bag.push(p);
					ProductInBag PIBag = new ProductInBag();
					PIBag.setId(p.getPid());
					PIBag.setName(p.getName());
					PIBag.setCount(0);
					PIBag.setPrice(p.getPrice());
					boolean isExsit = false;
					if (productsInBag.size() == 0) {
						productsInBag.add(PIBag);
					}
					for (int i = 0; i < productsInBag.size(); i++) {
						ProductInBag SRP3 = productsInBag.get(i);
						if (SRP3.getName().equals(PIBag.getName())) {
							productsInBag.get(i).setCount(
									productsInBag.get(i).getCount() + 1);
							isExsit = true;
							break;
						}
					}
					if (!isExsit) {
						PIBag.setCount(1);
						productsInBag.add(PIBag);
					}
					tableViewer_1.setInput(productsInBag);
					tableViewer.refresh();
					products.add(p);
					money = money + p.getPrice();
					state = false;
				} else {
					JOptionPane.showMessageDialog(null, "该商品已售完！");
				}
			}
		});

		Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 15, 48, 17);
		label.setText("\u79CD\u7C7B\u5217\u8868");

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(10, 246, 61, 17);
		label_1.setText("\u8D2D\u7269\u8F66");

		final Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(61, 12, 88, 25);
		TypeManager_db TManager = new TypeManager_db();
		for (Type student : TManager.queryAll()) {
			combo.add(student.getName());
		}

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!combo.getText().equals("")) {
					ProductManager PManager = new ProductManager();
					List<Product> List = PManager.query3(combo.getText());
					productsOnShalves = List;
					tableViewer.setInput(productsOnShalves);
				} else {
					JOptionPane.showMessageDialog(null, "请选择种类！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(155, 10, 80, 27);
		button.setText("\u786E\u5B9A");

		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductManager PManager = new ProductManager();
				productsOnShalves = PManager.queryAll();
				tableViewer.setInput(productsOnShalves);
			}
		});
		button_1.setBounds(241, 10, 80, 27);
		button_1.setText("\u67E5\u770B\u5168\u90E8");

		Button btnNewButton_1 = new Button(container, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!productsInBag.isEmpty()) {
					ProductManager PManager = new ProductManager();
					List<Product> ALL = PManager.queryAll();
					for (ProductInBag PIB : productsInBag) {
						if (PIB.getName().equals(Bag.peek().getName())) {
							if (PIB.getCount() == 1) {
								productsInBag.remove(PIB);
								products.remove(Bag.peek());
								if (money > 0) {
									money = money - Bag.peek().getPrice();
								}
								break;
							} else {
								PIB.setCount(PIB.getCount() - 1);
								money = money - Bag.peek().getPrice();
							}
						}
					}
					for (int i = 0; i < ALL.size(); i++) {
						if (ALL.get(i).getName().equals(Bag.peek().getName())) {
							ALL.get(i).setMargin(ALL.get(i).getMargin() + 1);
							PManager.updata(Bag.peek().getPid(), ALL.get(i));
							break;
						}
					}
					for (int i = 0; i < productsOnShalves.size(); i++) {
						if (productsOnShalves.get(i).getName()
								.equals(Bag.peek().getName())) {
							productsOnShalves.get(i).setMargin(
									productsOnShalves.get(i).getMargin() + 1);
						}
					}
					Bag.pop();
					tableViewer_1.refresh();
					tableViewer.refresh();
					if (productsInBag.isEmpty()) {
						state = true;
					}
				} else {
					state = true;
					JOptionPane.showMessageDialog(null, "购物车内无物品！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(130, 417, 80, 27);
		btnNewButton_1.setText("\u64A4\u9500");

		Button btnNewButton_2 = new Button(container, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProductManager PManager = new ProductManager();
				for (int i = 0; i < productsInBag.size(); i++) {
					Product pro = PManager.query(productsInBag.get(i).getId());
					pro.setMargin(pro.getMargin()
							+ productsInBag.get(i).getCount());
					PManager.updata(pro.getPid(), pro);
					for (int j = 0; j < productsOnShalves.size(); j++) {
						if (productsOnShalves.get(j).getPid() == pro.getPid()) {
							productsOnShalves.get(j).setMargin(pro.getMargin());
						}
					}
				}
				productsInBag.clear();
				state = true;
				Bag.clear();
				tableViewer_1.refresh();
				tableViewer.refresh();
				products.clear();
				money = 0f;
			}
		});
		btnNewButton_2.setBounds(242, 417, 80, 27);
		btnNewButton_2.setText("\u6E05\u7A7A");
		final Shell shl = this.getViewSite().getShell();
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				payMoney dd = new payMoney(shl, SWT.MAX | SWT.MIN);
				mianView.this.getViewSite().getShell().setEnabled(false);
				if (money <= 0) {
					JOptionPane.showMessageDialog(null, "购物车内无物品！", "提示",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (dd.open(money, products, productsInBag)) {
						Bag.clear();
						money = 0.0f;
						products.clear();
						productsInBag.clear();
						state = true;
						tableViewer_1.refresh();
					}
				}
				mianView.this.getViewSite().getShell().setEnabled(true);
			}
		});
		button_2.setBounds(10, 417, 80, 27);
		button_2.setText("\u7ED3\u7B97");

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(10, 46, 61, 17);
		label_2.setText("\u5546\u54C1\u5217\u8868");

		ProductManager PManager = new ProductManager();
		productsOnShalves = PManager.queryAll();
		tableViewer.setInput(productsOnShalves);

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
