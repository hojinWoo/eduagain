package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

public class MainFrame extends Frame {
	
	AccountManager manager;
	
	Label kindAccountL; //��������
	Label numAccountL; 	//���¹�ȣ
	Label nameAccountL;	//�����ָ�
	Label passwordL;	//��й�ȣ
	Label depositL;		//�Աݱݾ�
	Label minusMoneyL;	//����ݾ�
	Label listAccoutnL;	//���¸��
	Label unitMoney;	//���� : ��
	
	Button searchAccountBt, deleteAccountBt, searchNameBt, registerAccountBt, searchAllBt;
	
	TextField numAccounTF, nameAccountTF, passwordTF, depositTF, minusMoneyTF;
	
	TextArea showListTA;
	
	Choice kindAccountCB;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public MainFrame() {
		
	}
	
	public MainFrame(String string) {
		super(string);
		kindAccountL = new Label("��������");
		numAccountL = new Label("���¹�ȣ");
		nameAccountL = new Label("�����ָ�");
		passwordL = new Label("��й�ȣ");
		depositL = new Label("�Աݱݾ�");
		minusMoneyL = new Label("����ݾ�");
		listAccoutnL = new Label("���¸��");
		unitMoney = new Label("(���� : ��)");
		
		searchAccountBt = new Button("��ȸ");
		deleteAccountBt = new Button("����");
		searchNameBt = new Button("�˻�");
		registerAccountBt = new Button("�űԵ��");
		registerAccountBt.setEnabled(false);
		searchAllBt = new Button("��ü��ȸ");
		
		numAccounTF = new TextField("****-****-****");
		nameAccountTF = new TextField();
		passwordTF = new TextField();
		passwordTF.setEchoChar('*');
		depositTF = new TextField();
		minusMoneyTF = new TextField();
		
		showListTA = new TextArea();
		
		kindAccountCB = new Choice();
		kindAccountCB.add("��ü");
		kindAccountCB.add("����ݰ���");
		kindAccountCB.add("���̳ʽ�����");
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
	}
	
	/**
	 * setContents, eventRegist, pack ����
	 */
	public void setAll(){
		setContents();
		eventRegist();
		pack();
	}
	
	private void setContents() {
		setLayout(gridBagLayout);
		
		add(kindAccountL,		0,0,1,1,0,0,0);
		add(kindAccountCB,		1,0,1,1,0,0,0);
		
		add(numAccountL, 		0,1,1,1,0,0,0);
		add(numAccounTF, 		1,1,1,1,0,0,2);
		add(searchAccountBt, 	2,1,1,1,0,0,2);
		add(deleteAccountBt, 	3,1,1,1,0,0,0);
		
		add(nameAccountL,		0,2,1,1,0,0,0);
		add(nameAccountTF,		1,2,1,1,0,0,2);
		add(searchNameBt,		2,2,1,1,0,0,2);
		
		add(passwordL,			0,3,1,1,0,0,0);
		add(passwordTF,			1,3,1,1,0,0,2);
		add(depositL,			2,3,1,1,0,0,0);
		add(depositTF,			3,3,1,1,0,0,2);
		
		add(minusMoneyL,		0,4,1,1,0,0,0);
		add(minusMoneyTF,		1,4,1,1,0,0,2);
		add(registerAccountBt,	2,4,1,1,0,0,0);
		add(searchAllBt	,		3,4,1,1,0,0,0);

		add(listAccoutnL,		0,5,1,1,0,0,0);
		add(unitMoney	,		3,5,1,1,0,0,0);
		
		add(showListTA, 		0,6,4,1,0,0,0);
	}
	
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx, double weighty, int fill) {
		gridBagConstraints.gridx = gridx; 
		gridBagConstraints.gridy = gridy; 
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		
		gridBagConstraints.insets = new Insets(5, 5, 5, 5); //margin �ֱ�
		
		switch (fill) {
		case 1:
			gridBagConstraints.fill = gridBagConstraints.BOTH;
			break;
		case 2:
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
			break;
		case 3:
			gridBagConstraints.fill = gridBagConstraints.VERTICAL;
			break;
		default:
			gridBagConstraints.fill = gridBagConstraints.NONE;
			break;
		}
		
		gridBagLayout.setConstraints(component, gridBagConstraints);
		
		add(component); 
	}
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	private void print(int num) {
		if(num == 1){
			showListTA.setText("-------------------------------------------------------------------\n");
			showListTA.append(String.format("��������\t%-18s%-10s%-15s%-15s\n", "���¹�ȣ", "�����ָ�", "�����ܾ�", "����ݾ�"));
			showListTA.append("===================================================================\n");
		}else if(num == 2){
			showListTA.append("-------------------------------------------------------------------\n");
		}
	}

	private void setBlank() {
		numAccounTF.setText("****-****-****");
		nameAccountTF.setText("");
		passwordTF.setText("");
		depositTF.setText("");
		minusMoneyTF.setText("");
	}
	
	private boolean checkAccountNum(String accountNum){
		if (accountNum.equals("") || accountNum.equals("****-****-****")) {
			showListTA.setText("���¹�ȣ�� �Է����ּ���");
			return false;
		}else if(!(accountNum.matches("\\d{4}-\\d{4}-\\d{4}"))){ //****-****-****���Ŀ� �°� �Է��ϵ��� ����
			showListTA.setText("���¹�ȣ�� ���Ŀ� ���� �ʽ��ϴ�.");
			return false;
		}
		return true;
		
	}
	
	public void eventRegist() {
		
		//���� ó�� �̺�Ʈ
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		
		//���¹�ȣ ��ȸ 
		searchAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){} 
				else {
					Account account = manager.get(accountNum);
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account == null) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account == null || account instanceof MinusAccount) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account == null || !(account instanceof MinusAccount)) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					}
				}
				setBlank();
				
			}
		});
		
		//���¹�ȣ ����
		deleteAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){}
				else {
					Account account = manager.get(accountNum);
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account.equals("")) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("���°� �����Ǿ����ϴ�");

						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account.equals("") || account instanceof MinusAccount) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("���°� �����Ǿ����ϴ�");
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account.equals("") || !(account instanceof MinusAccount)) {
							showListTA.setText("���¹�ȣ�� �������� �ʽ��ϴ�.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("���°� �����Ǿ����ϴ�");
						}
					}
				}
				setBlank();
			}
		});
		
		//�̸� �˻�
		searchNameBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountOwner = nameAccountTF.getText();
				if (accountOwner.equals("")) {
					showListTA.setText("�����ָ��� �Է����ּ���");
				}else {
					List searchList = manager.search(accountOwner);

					if (kindAccountCB.getSelectedIndex() == 0) {
						if (searchList == null) {
							showListTA.setText("�̸��� �������� �ʽ��ϴ�");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (searchList == null) {
							showListTA.setText("�̸��� �������� �ʽ��ϴ�");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (!(object instanceof MinusAccount))
										showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (searchList == null) {
							showListTA.setText("�̸��� �������� �ʽ��ϴ�");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (object instanceof MinusAccount)
										showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					}
				}
				setBlank();
			}
		});
		
		//�ű� ���
		registerAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){}
				String accountName = nameAccountTF.getText();
				if(accountName.equals("")) {
					showListTA.append("�����ָ��� �Է����ּ���\n");
				}
				
				int password = 0;
				long depositMoney = 0;
				long minusMoney = 0;
				try {
					password = Integer.parseInt(passwordTF.getText());
					if (kindAccountCB.getSelectedIndex() == 1) {
						try {
							depositMoney = Long.parseLong(depositTF.getText());
						} catch (NumberFormatException e2) {
							depositMoney = 0; //�Աݱݾ��� ��� ���� ������ ���� (0��)
						}finally {
							boolean isNew = manager.add(new Account(accountNum, accountName, password, depositMoney));
							if(isNew){
								showListTA.setText("����ݰ��°� �����Ǿ����ϴ�.");
							}else{
								showListTA.setText("�ߺ��� ���°� �����մϴ�..");
							}
						}

					} else if (kindAccountCB.getSelectedIndex() == 2) {
						String deposit = depositTF.getText();
						if (deposit.equals("")) {
							deposit = "0";
						}
						try {
							depositMoney = Long.parseLong(deposit);
						} catch (NumberFormatException e2) {
						}
						try {
							minusMoney = Long.parseLong(minusMoneyTF.getText());
							boolean isNew = manager.add(new MinusAccount(accountNum, accountName, password, depositMoney, minusMoney));
							if(isNew){
								showListTA.setText("���̳ʽ����°� �����Ǿ����ϴ�.");
							}else{
								showListTA.setText("�ߺ��� ���¹�ȣ�� �����մϴ�..");
							}
						} catch (NumberFormatException e2) {
							showListTA.setText("����ݾ��� �Է����ּ���");
						}
					}
				} catch (NumberFormatException e2) {
					showListTA.append("��й�ȣ�� �Է����ּ���");
				}

				setBlank();
			}
		});
		
		//��ü��ȸ
		searchAllBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print(1);
				
				//Override�� ��ü�� ����ؼ� ����(0(��ü), 1(�����), 2(���̳���))�� ���� ���ϴ� list�� return�޾Ƽ� ����Ѵ�.
				List searchList = manager.list(kindAccountCB.getSelectedIndex());
				if(!searchList.isEmpty()) {
					for (Object object : searchList) {
						showListTA.append(object+"\n");
					}
				}
				print(2);
				setBlank();
			}
		});
		
		
		//���� ���� ����
		kindAccountCB.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(kindAccountCB.getSelectedIndex() == 0){
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(false);
				}else if(kindAccountCB.getSelectedIndex() == 1) {
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(false);
					registerAccountBt.setEnabled(true);
				}else if(kindAccountCB.getSelectedIndex() == 2){
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(true);
				}
			}
		});
		
		/*���콺�� Ŭ�� �� �� �ʱ�ȭ �ǵ��� ����*/
		numAccounTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				numAccounTF.setText("");
			}
		});
		
		/*��й�ȣ *�� ǥ��, Ű����� ���ڸ� �Է¹޿� �ϱ�*/
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		/*Ű����� ���ڸ� �Է¹޿� �ϱ�*/
		depositTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		/*Ű����� ���ڸ� �Է¹޿� �ϱ�*/
		minusMoneyTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		
	}


	public void setAccountManager(AccountManager accountManager) {
		manager = accountManager;
		
		//�׽�Ʈ�� ���� �̸� �־���� ���� ������
		manager.add(new Account("1111-2222-3333", "��ȣ��", 1111, 100000));
		manager.add(new Account("3333-2222-6666", "������", 1112, 400000));
		manager.add(new Account("4444-7777-1111", "�迬��", 1113, 300000));
		manager.add(new Account("6666-4444-2222", "�����", 1114, 200000));
		manager.add(new Account("4466-2244-1233", "������", 1115, 100000));
		manager.add(new MinusAccount("1231-1234-2222", "������", 4321, 0, 1000000));
		manager.add(new MinusAccount("1231-1234-2241", "�����", 1234, 0, 100000));
	}
}