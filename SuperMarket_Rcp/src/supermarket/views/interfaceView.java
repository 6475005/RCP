package supermarket.views;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import supermarket.dialog.VIPlogin;
import supermarket.dialog.VIPregist;
import supermarket.dialog.login;
import supermarket_object.VipManager;

public class interfaceView extends ViewPart {

	public static final String ID = "supermarket.views.interfaceView"; //$NON-NLS-1$
	public static boolean Timer = true;
	public String result = "false";
	private Label lblTime;
	private Label lblNewLabel;

	public interfaceView() {
		time();
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
			final Shell shl = this.getViewSite().getShell();
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					if (mianView.state) {
						login dd = new login(shl, SWT.MAX | SWT.MIN);
						interfaceView.this.getViewSite().getShell()
								.setEnabled(false);
						if (dd.open().toString().equals("true")) {
							IWorkbenchPage page = interfaceView.this
									.getViewSite().getPage();
							Timer = false;
							page.hideView(page.findView(ID));
							page.hideView(page.findView(mianView.ID));
							try {
								page.showView(adminManagerView.ID);
							} catch (PartInitException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						interfaceView.this.getViewSite().getShell()
								.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "请先结算或清空购物车物品!",
								"提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			button.setBounds(46, 417, 80, 27);
			button.setText("\u7BA1\u7406\u767B\u5F55");
		}
		{
			final Shell shl2 = this.getViewSite().getShell();
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (mianView.state) {
						IWorkbenchPage page = interfaceView.this.getViewSite()
								.getPage();
						VIPlogin dd = new VIPlogin(shl2, SWT.MAX | SWT.MIN);
						interfaceView.this.getViewSite().getShell()
								.setEnabled(false);
						result = dd.open();
						if (!result.equals("false")) {
							VipManager man = new VipManager();
							VipView.vip = man.query(result);
							Timer = false;
							page.hideView(page.findView(ID));
							page.hideView(page.findView(mianView.ID));
							try {
								page.showView(VipView.ID);

							} catch (PartInitException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						interfaceView.this.getViewSite().getShell()
								.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "请先结算或清空购物车物品!",
								"提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			button.setBounds(46, 333, 80, 27);
			button.setText("\u4F1A\u5458\u767B\u5F55");
		}
		{
			final Shell shl2 = this.getViewSite().getShell();
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					VIPregist dd = new VIPregist(shl2, SWT.MAX | SWT.MIN);
					interfaceView.this.getViewSite().getShell()
							.setEnabled(false);
					dd.open();
					interfaceView.this.getViewSite().getShell()
							.setEnabled(true);
				}
			});
			button.setBounds(46, 286, 80, 27);
			button.setText("\u4F1A\u5458\u6CE8\u518C");
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(10, 387, 64, 2);
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(100, 387, 64, 2);
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(10, 259, 64, 2);
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(100, 259, 64, 2);
		}
		{
			Label lblXxx = new Label(container, SWT.NONE);
			lblXxx.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			lblXxx.setBounds(46, 10, 80, 21);
			lblXxx.setText("\u81EA\u52A8\u552E\u8D27\u673A");
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(10, 165, 64, 2);
		}
		{
			Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
			label.setBounds(100, 165, 64, 2);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setImage(ResourceManager.getPluginImage("SuperMarket_Rcp",
					"icons/logo.jpg"));
			label.setBounds(10, 37, 154, 122);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			label.setBounds(55, 178, 64, 21);
			label.setText("\u5F53\u524D\u65F6\u95F4");
		}
		{
			lblTime = new Label(container, SWT.NONE);
			lblTime.setBounds(33, 205, 120, 17);
			lblTime.setText("date");
		}

		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(60, 228, 80, 17);
		lblNewLabel.setText("time");

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void time() {
		class MyThread extends Thread {
			public void run() {
				while (Timer) {

					// 异步执行一段代码
					PlatformUI.getWorkbench().getDisplay()
							.asyncExec(new Runnable() {
								public void run() {
									Calendar now = new GregorianCalendar();
									int hour = now.get(Calendar.HOUR_OF_DAY); // 得到小时数
									int minute = now.get(Calendar.MINUTE); // 得到分数
									int second = now.get(Calendar.SECOND); // 得到秒数
									int year = now.get(Calendar.YEAR);
									int month = now.get(Calendar.MONTH) + 1;
									int day = now.get(Calendar.DAY_OF_MONTH);
									int day2 = now.get(Calendar.WEDNESDAY);
									String weekend = "";
									switch (day2) {
									case 0: {
										weekend = "日";
									}
									case 1: {
										weekend = "一";
									}
									case 2: {
										weekend = "二";
									}
									case 3: {
										weekend = "三";
									}
									case 4: {
										weekend = "四";
									}
									case 5: {
										weekend = "五";
									}
									case 6: {
										weekend = "六";
									}
									}
									String date = year + "-" + month + "-"
											+ day + "  星期：" + weekend;
									String timeInfo = "";
									if (hour <= 9)
										timeInfo += "0" + hour + ":"; // 格式化输出
									else
										timeInfo += hour + ":";
									if (minute <= 9)
										timeInfo += "0" + minute + ":";
									else
										timeInfo += minute + ":";
									if (second <= 9)
										timeInfo += "0" + second;
									else
										timeInfo += second;
									lblNewLabel.setText(timeInfo);
									lblTime.setText(date);
								}
							});

					try {
						Thread.sleep(1000);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		MyThread mt = new MyThread();
		mt.start();
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
