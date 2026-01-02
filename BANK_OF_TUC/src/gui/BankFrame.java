package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BankFrame extends JFrame implements ActionListener{
	JMenuItem miLogin,miDashboard,miAccounts,miAbout,miExit;
	JMenu nav;
	
	
	public BankFrame() {
        setTitle("Bank of TUC");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
        
        // --- Menu bar ---
        JMenuBar bar = new JMenuBar();
        nav = new JMenu("Navigate");
        miLogin = new JMenuItem("Login");
        miDashboard = new JMenuItem("Dashboard");
        miAccounts = new JMenuItem("Accounts");
        miExit = new JMenuItem("Exit");
        
        miDashboard.setVisible(false);
        miAccounts.setVisible(false);

        nav.add(miLogin);
        nav.add(miDashboard);
        nav.add(miAccounts);
        nav.addSeparator();
        nav.add(miExit);

        bar.add(nav);
        setJMenuBar(bar);
        
        buildPanels();
  
        AppMediator.setBank(this);
    }
	
	public void enableUserMenu() {
		miDashboard.setVisible(true);
        miAccounts.setVisible(true);
        miLogin.setVisible(false);
	}
	
	public void disableUserMenu() {
		miDashboard.setVisible(false);
        miAccounts.setVisible(false);
        miLogin.setVisible(true);
	}
    
    public void buildPanels() {
    	// --- Cards container (pages) ---
    	CardLayout cardLayout = new CardLayout();
    	JPanel cards = new JPanel(cardLayout);
    	AppMediator.setCardLayout(cardLayout);
    	AppMediator.setCards(cards);

    	// create panels (we'll implement below)
    	LoginPanel loginPanel = new LoginPanel();
    	DashboardPanel dashboardPanel = new DashboardPanel();
    	AccountsPanel accountsPanel = new AccountsPanel();
		AccountMenuPanel accountMenuPanel = new AccountMenuPanel();
		BillPaymentPanel billPaymentPanel = new BillPaymentPanel();
		PayBill payBill = new PayBill();
		PendingBillsPanel pendingBillsPanel = new PendingBillsPanel();
		TransactionHistoryPanel transactionHistoryPanel = new TransactionHistoryPanel();
		AccountDetailsPanel accountDetailsPanel = new AccountDetailsPanel();
		BookAppointment bookAppointment = new BookAppointment();
		NewTransaction newTransaction = new NewTransaction();
		NewTransactionConfirm newTransactionConfirm = new NewTransactionConfirm();
		ManageSecondaryHolders manageSecondaryHolders = new ManageSecondaryHolders();
		SecondaryHolderDetails secondaryHolderDetails = new SecondaryHolderDetails();
		AddSecondaryHolder addSecondaryHolder = new AddSecondaryHolder();
		AddSecondaryHolderConfirm addSecondaryHolderConfirm = new AddSecondaryHolderConfirm();
		ManageStandingOrders manageStandingOrders = new ManageStandingOrders();
		CreateStandingOrder createStandingOrder = new CreateStandingOrder();
		CreateStandingOrderConfirm createStandingOrderConfirm = new CreateStandingOrderConfirm();
		StandingOrderDetails standingOrderDetails = new StandingOrderDetails();
		EditStandingOrder editStandingOrder = new EditStandingOrder();
		PauseStandingOrder pauseStandingOrder = new PauseStandingOrder();

    	// register the panels with names
    	cards.add(loginPanel, "login");
    	cards.add(dashboardPanel, "dashboard");
    	cards.add(accountsPanel, "accounts");
		cards.add(accountMenuPanel, "accountMenu");
		cards.add(billPaymentPanel, "billPaymentPanel");
		cards.add(payBill, "payBill");
		cards.add(pendingBillsPanel, "pendingBillsPanel");
		cards.add(transactionHistoryPanel, "transactionHistoryPanel");
		cards.add(accountDetailsPanel, "accountDetails");
		cards.add(bookAppointment, "bookAppointment");
		cards.add(newTransaction, "newTransaction");
		cards.add(newTransactionConfirm, "newTransactionConfirm");
		cards.add(manageSecondaryHolders, "manageSecHolders");
		cards.add(secondaryHolderDetails, "secHolderDetails");
		cards.add(addSecondaryHolder, "addHolder");
		cards.add(addSecondaryHolderConfirm, "confirmHolder");
		cards.add(manageStandingOrders, "manageStandOrders");
		cards.add(createStandingOrder, "createStandOrder");
		cards.add(createStandingOrderConfirm, "createStandOrderConfirm");
		cards.add(standingOrderDetails, "standOrderDetails");
		cards.add(editStandingOrder, "editStandOrder");
		cards.add(pauseStandingOrder, "pauseStandOrder");

    	add(cards); // add to frame content
    	
    	miLogin.addActionListener(this);
    	miDashboard.addActionListener(this);
    	miAccounts.addActionListener(this);
    	miExit.addActionListener(this);
  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==miLogin)
			AppMediator.getCardLayout().show(AppMediator.getCards(), "login");
		else if (e.getSource()==miDashboard)
			AppMediator.getCardLayout().show(AppMediator.getCards(), "dashboard");
		else if (e.getSource()==miAccounts) 
			AppMediator.getCardLayout().show(AppMediator.getCards(), "accounts");
		else if (e.getSource()==miExit) 
			System.exit(0);
	}
}
