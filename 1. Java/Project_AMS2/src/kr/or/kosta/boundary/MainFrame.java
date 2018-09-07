package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountDao;
import kr.or.kosta.entity.MinusAccount;

public class MainFrame extends Frame {
	
	AccountDao manager;
	
	Label kindAccountL; //계좌종류
	Label numAccountL; 	//계좌번호
	Label nameAccountL;	//예금주명
	Label passwordL;	//비밀번호
	Label depositL;		//입금금액
	Label minusMoneyL;	//대출금액
	Label listAccoutnL;	//계좌목록
	Label unitMoney;	//단위 : 원
	
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
		kindAccountL = new Label("계좌종류");
		numAccountL = new Label("계좌번호");
		nameAccountL = new Label("예금주명");
		passwordL = new Label("비밀번호");
		depositL = new Label("입금금액");
		minusMoneyL = new Label("대출금액");
		listAccoutnL = new Label("계좌목록");
		unitMoney = new Label("(단위 : 원)");
		
		searchAccountBt = new Button("조회");
		deleteAccountBt = new Button("삭제");
		searchNameBt = new Button("검색");
		registerAccountBt = new Button("신규등록");
		registerAccountBt.setEnabled(false);
		searchAllBt = new Button("전체조회");
		
		numAccounTF = new TextField();
		nameAccountTF = new TextField();
		passwordTF = new TextField();
		passwordTF.setEchoChar('*');
		depositTF = new TextField();
		minusMoneyTF = new TextField();
		
		showListTA = new TextArea();
		
		kindAccountCB = new Choice();
		kindAccountCB.add("전체");
		kindAccountCB.add("입출금계좌");
		kindAccountCB.add("마이너스계좌");
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
	}
	
	public void setContents() {
		setLayout(gridBagLayout);
		
		add(kindAccountL,		0,0,1,1,0,0,0);
		add(kindAccountCB,		1,0,1,1,0,0,0);
		
		add(numAccountL, 		0,1,1,1,0,0,0);
		add(numAccounTF, 		1,1,1,1,0,0,2);
		add(searchAccountBt, 	2,1,1,1,0,0,0);
		add(deleteAccountBt, 	3,1,1,1,0,0,0);
		
		add(nameAccountL,		0,2,1,1,0,0,0);
		add(nameAccountTF,		1,2,1,1,0,0,2);
		add(searchNameBt,		2,2,1,1,0,0,0);
		
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
	
	private void print() {
		showListTA.setText("-------------------------------------------------------------\n");
		showListTA.append("계좌종류\t계좌번호\t\t예금주명\t현재잔액\t대출금액\n");
		showListTA.append("=============================================================\n");
	}

	private void setBlank() {
		numAccounTF.setText("");
		nameAccountTF.setText("");
		passwordTF.setText("");
		depositTF.setText("");
		minusMoneyTF.setText("");
	}
	
	
	public void eventRegist() {
		
		//종료 처리 이벤트
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		
		//계좌번호 조회 
		searchAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numAccounTF.getText().equals("")) {
					showListTA.setText("계좌번호를 입력해주세요");
				} else {

					Account account = manager.get(numAccounTF.getText());
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account == null) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print();
							showListTA.append(account.toString() + "\n");
							showListTA.append("-------------------------------------------------------------\n");
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account == null || account instanceof MinusAccount) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print();
							showListTA.append(account.toString() + "\n");
							showListTA.append("-------------------------------------------------------------\n");
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account == null || !(account instanceof MinusAccount)) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print();
							showListTA.append(account.toString() + "\n");
							showListTA.append("-------------------------------------------------------------\n");
						}
					}
				}
				setBlank();
			}
		});
		
		//계좌번호 삭제
		deleteAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (accountNum.equals("")) {
					showListTA.setText("계좌번호를 입력해주세요");
				}
				else {
					Account account = manager.get(accountNum);
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account.equals("")) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");

						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account.equals("") || account instanceof MinusAccount) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account.equals("") || !(account instanceof MinusAccount)) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");
						}
					}
				}
				setBlank();
			}
		});
		
		//이름 검색
		searchNameBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountOwner = nameAccountTF.getText();
				if (accountOwner.equals("")) {
					showListTA.setText("예금주명을 입력해주세요");
				}else {
					List searchList = manager.search(accountOwner);

					if (kindAccountCB.getSelectedIndex() == 0) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print();
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									showListTA.append(object + "\n");
								}
							}
							showListTA.append("-------------------------------------------------------------\n");
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print();
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (!(object instanceof MinusAccount))
										showListTA.append(object + "\n");
								}
							}
							showListTA.append("-------------------------------------------------------------\n");
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print();
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (object instanceof MinusAccount)
										showListTA.append(object + "\n");
								}
							}
							showListTA.append("-------------------------------------------------------------\n");
						}
					}
				}
				setBlank();
			}
		});
		
		//신규 등록
		registerAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String accountNum = numAccounTF.getText();
				if(accountNum.equals("")) {
					showListTA.setText("계좌번호를 입력해주세요");
				}
				String accountName = nameAccountTF.getText();
				if(accountName.equals("")) {
					showListTA.append(", 예금주명을 입력해주세요");
				}
				
				int password = 0;
				long depositMoney = 0;
				long minusMoney = 0;
				try {
					password = Integer.parseInt(passwordTF.getText());
					if (kindAccountCB.getSelectedIndex() == 1) {
						try {
							depositMoney = Long.parseLong(depositTF.getText());
							manager.add(new Account(accountNum, accountName, password, depositMoney));
							showListTA.setText("입출금계좌가 생성되었습니다.");
						} catch (NumberFormatException e2) {
							showListTA.setText("입금금액을 입력해주세요");
						}

					} else if (kindAccountCB.getSelectedIndex() == 2) {
						String deposit = depositTF.getText();
						if (deposit == null) {
							deposit = "0";
						}
						try {
							depositMoney = Long.parseLong(deposit);
						} catch (NumberFormatException e2) {
						}
						try {
							minusMoney = Long.parseLong(minusMoneyTF.getText());
							manager.add(new MinusAccount(accountNum, accountName, password, depositMoney, minusMoney));
							showListTA.setText("마이너스계좌가 생성되었습니다.");
						} catch (NumberFormatException e2) {
							showListTA.setText("계좌번호를 입력해주세요");
						}
					}
				} catch (NumberFormatException e2) {
					showListTA.append(" 비밀번호를 입력해주세요");
				}

				setBlank();
			}
		});
		
		//전체조회
		searchAllBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print();
				
				//Override된 객체를 사용해서 숫자(0(전체), 1(입출금), 2(마이나스))를 통해 원하는 list를 return받아서 출력한다.
				List searchList = manager.list(kindAccountCB.getSelectedIndex());
				if(!searchList.isEmpty()) {
					for (Object object : searchList) {
						showListTA.append(object+"\n");
					}
				}
				showListTA.append("-------------------------------------------------------------\n");
			}
		});
		
		
		//계좌 종류 선택
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
		
		/*비밀번호 *로 표시, 키보드로 숫자만 입력받에 하기*/
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		/*키보드로 숫자만 입력받에 하기*/
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
		/*키보드로 숫자만 입력받에 하기*/
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


	public void setAccountManager(AccountDao dao) {
		manager = dao;
		
		//테스트를 위해 미리 넣어놓는 예제 데이터
		manager.add(new Account("111-222-333", "우호진", 1111, 100000));
		manager.add(new Account("333-222-666", "박지성", 1112, 400000));
		manager.add(new Account("444-777-111", "김연아", 1113, 300000));
		manager.add(new Account("666-444-222", "손흥민", 1114, 200000));
		manager.add(new Account("446-224-123", "박지성", 1115, 100000));
		manager.add(new MinusAccount("1231-1234-2222", "백종현", 4321, 0, 1000000));
		manager.add(new MinusAccount("1231-1234-2241", "김기정", 1234, 0, 100000));
	}
}
